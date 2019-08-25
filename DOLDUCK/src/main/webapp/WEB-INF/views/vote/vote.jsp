<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<title>All About K-POP! DD - Star</title>
<!-- Bootstdap 4 -->
<link rel="stylesheet" type="text/css" href="resources/styles/bootstrap-4.1.2/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="resources/styles/chart.css">
<link rel="stylesheet" type="text/css" href="resources/styles/main_styles.css">
<link rel="stylesheet" type="text/css" href="resources/styles/vote.css">

<!-- jQuery library -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="resources/styles/bootstrap-4.1.2/bootstrap.min.js"></script>
<script src="resources/js/vote.js?ver=1" type="text/javascript"></script>

</head>
<body>
	<!-- Header -->
	 <%@include file="../header.jsp" %>
	<div class="container vote_board">
		<div class="center">
			<div class="div-star">
				<ul class="vote-button">
					<li class="girl-group">여자 그룹</li>
					<li class="girl-star">여자 아이돌</li>
					<li class="new-star">신인 스타</li>
					<li class="star-star">별별 스타</li>
					<li class="boy-group">남자 그룹</li>
					<li class="boy-star">남자 아이돌</li>
				</ul>
			</div>
			<div class="votechart-board">
				<p>검색시간 : <label class="label-test"></label>
				<table class="table">
					<thead>
						<tr id="">
							<th scope="col">Star</th>
							<th scope="col">Img</th>
							<th scope="col">투표하기</th>
						</tr>
					</thead>
				<tbody></tbody>
				</table>
				<button class="abc">더 보기</button>	
			</div>
		</div>
	</div>
	
</body>
</html>