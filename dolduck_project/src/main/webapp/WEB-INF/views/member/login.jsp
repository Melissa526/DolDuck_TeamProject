<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="resources/js/login.js"></script>
</head>
<body>
<%@include file="../header.jsp" %>

<div class="container login">
	<div class="row">
		<div class="col-lg-6 offset-lg-3 login_table">
		<h1 class="text-center login_text">DOL-Duck 로그인</h1>

<span style="color:red;">${errMsg }</span>
<form action="${pageContext.request.contextPath}/home.do" method="post">
<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token}">
		<table>
	
			<tr>
				<th>ID</th>
				<td><input class="form-control login" type="text" id="id" name="id" ></td>
			</tr>
			<tr>
				<th>PW</th>
				<td><input class="form-control login" type="password" id="pw" name="passwd"></td>
			</tr>
			<tr>
				<td colspan="1"><input class="login_button" type="submit" value="로그인" ></td>
			</tr>
			<tr>
				<td colspan="1"><input class="join_button" type="button" value="회원가입" onclick="location.href='${pageContext.request.contextPath}/join.do'"></td>
			</tr>
		
		</table>
	</form>
	</div>
	</div>
</div>
</body>
</html>