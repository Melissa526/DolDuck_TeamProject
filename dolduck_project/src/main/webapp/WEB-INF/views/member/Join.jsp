<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="resources/js/register.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<title>Insert title here</title>
</head>
<body>
<%@include file="../header.jsp" %>
<div class="container join">
	<div class="row">
		<div class="col-lg-6 offset-lg-3 join_table">
			<h1 class="text-center">회원 가입</h1>
			<form method="post" action="${pageContext.request.contextPath}/register.do">
              <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }">			
					
					
					<table class="join_form">
						<tr>
							<th>이름</th>
						</tr>
						<tr>
							<td>
								<input class="form-control" type="text" class="text" name="user_name" placeholder="이름" required />
							</td>
						</tr>
						<tr>
						</tr>
						<tr>
							<th>아이디</th>
						</tr>
						<tr>
							<td>
								<div id="id_confirm">
									<input class="form-control" type="text" class="text" name="user_id" placeholder="아이디" required />
									<input type="button" class="idChk" id="btn_idcheck" value="중복확인" onclick="id_check()"/>
								</div>
								<p class="result">
									<span class="msg">아이디를 확인해 주십시오.</span>
								</p>
								
							</td>
							
						</tr>
						<tr>
						</tr>
						<tr>
							<th>비밀번호</th>
						</tr>
						<tr>	
							
							<td>
								<input class="form-control" type="password" class="text" name="user_pw" placeholder="비밀번호" onchange="pass_check()" required />
								<div id="pass_check"></div>
								<input class="form-control" type="password" class="text" name="user_pw02" placeholder="비밀번호 확인" onchange="pass_confirm()" required />
								<div id="pass_confirm"></div>
							</td>
						</tr>
						<tr>
							<th>전화번호</th>
						</tr>
						<tr>
							<td>
								<input class="form-control" type="text" class="text" name="user_phone" placeholder="전화번호" required />
							</td>
						</tr>
						<tr>
							<th>이메일</th>
						</tr>
						<tr>
							<td>
								<input class="form-control" type="email" class="text" name="user_email" placeholder="이메일" required/>
								<input type="button" id="btn_sendemail" value="인증번호발송" onclick="sendEmail()"/><p></p>
							</td>
						</tr>
						<tr>
							<td>
								<input class="form-control" type="text" class="text" name="ranNum" placeholder="이메일 인증번호" required/>
								<input type="button" id="ranChk" value="인증번호확인" onclick="ranNumChk()"/>
								<div id="confirm"></div>
								<div id="emailChkConfirm"></div>
							</td>
						</tr>
						<tr>
							<th>주소</th>
						</tr>
						<tr>
							<td>
								<input class="form-control" type="text" name="user_addr" id="addr" placeholder="주소" readonly="readonly"> 
								<input type="button" id="set_addr" class="e1" onclick="setAddr()" value="주소입력"> 
								<input class="form-control" type="text" id="detail_addr" name="detail_addr" placeholder="상세주소" > 
								<input type="hidden" id="zonecode" name="zonecode" value="" />
							</td>
						</tr>
						<tr>
							<td colspan="2" >
								<input id="joinup" type="submit" value="회원가입"/>
							</td>
						</tr>
					</table>
				</form>

		</div>
	</div>
</div>
<%@include file="../footer.jsp" %>
</body>
</html>