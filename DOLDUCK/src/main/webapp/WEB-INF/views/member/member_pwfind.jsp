﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Login</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="resources/js/find/pwfind.js"></script>
</head>
<body>
<%@include file="../header.jsp"%>

<div class="container login">
	<div class="row">
		<div class="col-lg-6 offset-lg-3 login_table">
			<h1 class="text-center login_text">비밀번호 찾기</h1>
			<form action="${pageContext.request.contextPath}/pwfind.do" method="post">
				<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token}">
				<table>
					<tr>
						<th>ID</th>
						<td><input class="form-control login" type="text" id="id" name="id"></td>
						<td rowspan="2"><input class="login_button" type="button" value="비밀번호 찾기" onclick="sendEmail()" style="height:91px;margin-top:0;margin-left:5px;"></td>
					</tr>
					<tr>
						<th>EMAIL</th>
						<td><input class="form-control login" type="email" id="email" name="email"></td>
					</tr>
					<tr>
						<td colspan="2"><input class="login_button" type="button" value="로그인" onclick="location.href='${pageContext.request.contextPath}/login.do'" style="margin: 19px 4px 0 0;">&emsp;&emsp;</td>
						<td colspan="1"><input class="login_button" type="button" value="아이디 찾기" onclick="location.href='${pageContext.request.contextPath}/idfind.do'" style="margin:-2px 0 0 7px;"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div class="modal fade" id="checkModal" tableindex="-1" role="dialog" aria-hidden="true"></div>
</div>
</body>
</html>