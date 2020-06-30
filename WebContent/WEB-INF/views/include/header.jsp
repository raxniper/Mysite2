<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div id="header">
	<h1>
		<a href="http://localhost:8088/Mysite2/main">MySite</a>
	</h1>

	<c:choose>
		<c:when test="${empty authUser}">
			<ul>
				<li><a
					href="http://localhost:8088/Mysite2/user?action=loginForm">로그인</a></li>
				<li><a
					href="http://localhost:8088/Mysite2/user?action=joinForm">회원가입</a></li>
			</ul>
		</c:when>

		<c:otherwise>
			<!--  로그인 성공했을때(세션값이 있으면) -->
			<ul>
				<li>${sessionScope.authUser.name }님안녕하세요^^</li>
				<li><a href="http://localhost:8088/Mysite2/user?action=logout">로그아웃</a></li>
				<li><a
					href="http://localhost:8088/Mysite2/user?action=modifyFoem">회원정보수정</a></li>
			</ul>
		</c:otherwise>
	</c:choose>
</div>