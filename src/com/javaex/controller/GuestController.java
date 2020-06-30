package com.javaex.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestbookDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestbookVo;

@WebServlet("/gb")
public class GuestController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// UTF-8 변환
		request.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("action");

		// 메인화면(리스트)
		if ("addList".equals(action)) {
			GuestbookDao dao = new GuestbookDao();
			List<GuestbookVo> gList = dao.getPersonList();

			// 리스트 응답해주기
			request.setAttribute("guestList", gList);

			// 포워드
			
			WebUtil.forword(request, response, "/WEB-INF/views/guestbook/addList.jsp");

		}
		// 등록(insert)
		else if ("add".equals(action)) {
			String name = request.getParameter("name");
			String pw = request.getParameter("pw");
			String content = request.getParameter("content");

			GuestbookDao dao = new GuestbookDao();
			GuestbookVo vo = new GuestbookVo(name, pw, content);

			dao.personInsert(vo);

			WebUtil.redirect(request, response, "/Mysite2/gb?action=addList");
		}
		// 번호 저장
		else if ("deleteForm".equals(action)) {
			WebUtil.forword(request, response, "/WEB-INF/views/guestbook/deleteForm.jsp");
		}

		// 삭제일때
		else if ("delete".equals(action)) {
			int no = Integer.parseInt(request.getParameter("no"));
			String pw = request.getParameter("pw");

			GuestbookDao dao = new GuestbookDao();
			List<GuestbookVo> pList = dao.getPersonList();

			
			for (int i = 0; i < pList.size(); i++) {
				if (no == pList.get(i).getNo() && pw.equals(pList.get(i).getPw())) {
					dao.personDelete(no, pw);
					WebUtil.redirect(request, response, "/Mysite2/gb?action=addList");
				} else if (no == pList.get(i).getNo() && !pw.equals(pList.get(i).getPw())) {
					
					//서블릿 안에 스크립트 사용(팝업창)
					response.setContentType("text/html; charset=utf-8");
					PrintWriter out = response.getWriter();

					out.println("<script language='javascript'>");

					out.println("alert('비밀번호가 틀렸습니다.');");
					out.println("document.location.href = \"/Mysite2/gb?action=addList\"");
					out.println("</script>");
					out.flush();
				}
			}

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
