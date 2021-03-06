<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%-- <meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>  --%>
<title>All About K-POP! DD - 실시간 음원차트 순위</title>
<!-- Bootstdap 4 -->
<link rel="stylesheet" type="text/css" href="resources/styles/bootstrap-4.1.2/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="resources/styles/chart.css">
<link rel="stylesheet" type="text/css" href="resources/styles/main_styles.css">
</head>
<body>
   
   <!-- Header -->
    <%@include file="/WEB-INF/views/header.jsp" %>
   
   <div class="container ">
	    <!-- Navigator Icon -->
	    <div class="div-channel">
	      <!-- Melon -->
			<div class="div-button">
				<div class="button-channel" id="melon">
					<input type="hidden" class="site" value="melon" >
				</div>
				<div class="label-channel">Melon</div>
			</div>
	      <!-- Bugs-->
			<div class="div-button"> 
				<div class="button-channel" id="bugs">
					<input type="hidden" class="site" value="bugs">
				</div>
				<div class="label-channel">Bugs</div>
			</div>
	      <!--  Genie 뮤직  -->
			<div class="div-button">
				<div class="button-channel" id="genie">
					<input type="hidden" class="site" value="genie">
				</div>
				 <div class="label-channel">Genie뮤직</div>
			</div>
		</div>
	</div>
	<div class="container">
		<!-- Chart Area -->
      <div class="center musicchart-board">   
         <p>검색시간 : <label class="label-search"></label><p>
         <table class="table ">
            <thead>
               <tr>
                  <th scope="col">Rank</th>
                  <th scope="col">Thumbs</th>
                  <th scope="col">Song title</th>
                  <th scope="col">Singer</th>
                  <th scope="col">Album</th>
                  <th scope="col">etc.</th> 
               </tr>
            </thead>
            <tbody></tbody>
         </table>
         <button class="button-pagination">더 보기</button>   
      </div>
   </div>
   
<!-- jQuery library -->
<script src="//ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.min.js" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="resources/styles/bootstrap-4.1.2/bootstrap.min.js"></script>
<script src="resources/js/utils/chart.js" type="text/javascript"></script>
</body>
</html>