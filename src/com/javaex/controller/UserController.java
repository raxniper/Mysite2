package com.javaex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.UserDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.UserVo;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action");

		if ("joinForm".equals(action)) {

			WebUtil.forword(request, response, "/WEB-INF/views/user/joinForm.jsp");

		} else if ("join".equals(action)) {
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");

			UserDao dao = new UserDao();
			UserVo vo = new UserVo(id, password, name, gender);

			dao.Insert(vo);

			WebUtil.forword(request, response, "/WEB-INF/views/user/joinOk.jsp");
		} else if ("loginForm".equals(action)) {
			WebUtil.forword(request, response, "/WEB-INF/views/user/loginForm.jsp");
		} else if ("login".equals(action)) {
			String id = request.getParameter("id");
			String password = request.getParameter("password");

			UserDao dao = new UserDao();
			UserVo authrVo = dao.getUser(id, password);

			if (authrVo == null) {// 세션에 값이 없으면
				WebUtil.redirect(request, response, "/Mysite2/user?action=loginForm&result=fail");// resulfail은
			} else {// 세션에 값이 있으면
				// 로그인성공
				// 세션영역에 값을 주기
				HttpSession session = request.getSession();// 요청한 홈페이지에 세션값 얻기
				session.setAttribute("authUser", authrVo);// 세션 넘버하고네임값

				WebUtil.redirect(request, response, "http://localhost:8088/Mysite2/main");
			}
		} else if ("logout".equals(action)) {
			HttpSession session = request.getSession();
			session.removeAttribute("authUser");// 세션 "authUser"값 지우기
			session.invalidate();// 세션값지우기2 세트로사용

			WebUtil.redirect(request, response, "http://localhost:8088/Mysite2/main");
		} else if ("modifyFoem".equals(action)) {
			UserDao dao = new UserDao();
			HttpSession session = request.getSession();// 요청한 홈페이지에 세션값 얻기
			
			UserVo vo1 = (UserVo) session.getAttribute("authUser"); // authUser 세션 가져오기
			
			UserVo vo = dao.getUser(vo1.getNo());//모든셀렉트를 vo에 저장
			
			request.setAttribute("userVo", vo);//모든셀렉트 가지고있는 어트리뷰트
			WebUtil.forword(request, response, "/WEB-INF/views/user/modifyForm.jsp");
		}

		else if ("modif".equals(action)) {
			UserDao dao = new UserDao();
			HttpSession session = request.getSession();// 요청한 홈페이지에 세션값 얻기
		
			int no = ((UserVo)session.getAttribute("authUser")).getNo(); // authUser 세션 가져오기
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String gender = request.getParameter("gender");

			UserVo vo = new UserVo(no, password, name, gender);
			dao.Update(vo);

			//세션값 업데이트(방법1)
			//session.setAttribute("authUser", name);
			
			//세션값 업데이트(방법2) 이름값만 업데이트하기.
			UserVo sVo = (UserVo)session.getAttribute("authUser");
			sVo.setName(name);
			WebUtil.redirect(request, response, "http://localhost:8088/Mysite2/main");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
