<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All About K-POP! DD - Live</title>
<link rel="stylesheet" type="text/css" href="resources/styles/live.css">
<link rel="stylesheet" type="text/css" href="resources/js/broadcast/css/main.css">
</head>
<body>
    <!-- Header -->
    <%@include file="../header.jsp" %>
	<!-- Slide Caresel  -->
    <%@include file="/WEB-INF/views/live/liveroom-slide.jsp" %>
    
    <!-- Tab Menu -->
    <div id="main" class="container nav-tab-menu">
        <!-- Tab Menu -->
    	<div class="col-lg-12 offset-lg-4 "> 
            <nav class="nav">
               <ul class="nav nav-tabs">
                  <li class="nav-item" id="nav-link-home"><a class="nav-link" href="live-home.do">&nbsp;&nbsp;홈&nbsp;&nbsp;</a></li>
                  <li class="nav-item" id="nav-link-schedule"><a class="nav-link" href="live-schedule.do">일정표</a></li>
                  <li class="nav-item" id="nav-link-onair"><a class="nav-link" href="live-onair.do">온에어</a></li>
                  <!-- <li class="nav-item" id="nav-link-channel"><a class="nav-link" href="live-channel.do">채널보기</a></li> -->
               </ul>
            </nav>
         </div>
    </div>

   	<!-- Title & Banner -->
   	<div class="container"> 
         <div class="live-banner" style="background-image: url('resources/images/banner-live-onair.png')"></div>
         
        
        <!-- Add Events Button for ADMIN -->
	    <div id="area-addEvent">
        	<sec:authorize access="hasAnyRole('ROLE_ARTIST', 'ROLE_ADMIN')">
	        	<button type="button" class="btn btn-danger toggle-start-live" data-toggle="modal" data-target="#liveInfoModal">방 송 시 작</button>
        	</sec:authorize>
        	<button type="button" class="btn btn-secondary" id="channel-reload">새 로 고 침</button>
	    </div> 
    </div>
    
   	<!-- Onair Channel list - waiting room -->
	<div class="container">
		<div class="channel-list">
	       <ul id="channel-item"></ul>
	    </div>
	</div>

	<!-- The Modal -->
	<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_ARTIST')">
	<div class="modal-area container">
	  <div class="modal fade" id="liveInfoModal">
	    <div class="modal-dialog modal-sm modal-dialog-centered">
	      <div class="modal-content">
	      
	        <!-- Modal Header -->
	        <div class="modal-header">
	          <h4 class="modal-title">라이브 방송 시작하기</h4>
	          <button type="button" class="close" data-dismiss="modal">&times;</button>
	        </div>
	        
	        <!-- Modal body -->
	        <div class="modal-body">
	          <form action="start-onair.do" id="liveinfo-form" method="post">
	          	<table style="width: 100%;">
	          		<tr><td>방송진행자</td></tr>
	          		<tr><td><input type="text" class="form-control" name="live_caster"  value="<sec:authentication property="principal.member_id"/>" readonly="readonly"></td></tr>
 					<tr><td></td></tr>
	          		<tr><td>방 송 제 목</td></tr>
					<tr>
						<td>
							<input type="text" class="form-control" name="live_title">
						</td>
					</tr>
					<tr>
						<td>
							<input type="hidden" name="startingTime" value="">
							<input type="hidden" name="room_number" value=""> 
						</td>
					</tr>
					<tr><td></td></tr>
	          	</table>
		        <!-- Modal footer -->
		        <div class="modal-footer">
			      <button class="btn btn-danger" id="btn-startlive">시 작</button>
		          <button class="btn btn-secondary" data-dismiss="modal" type="button">Close</button>
		        </div>
	          </form>
	        </div>
	        
	      </div>
	    </div>
	  </div>
	</div>
	</sec:authorize>

    
    <!-- Footer -->
    <%@include file="/WEB-INF/views/footer.jsp" %>
    <!-- 윈도우  -->
    <!-- <script src="https://192.168.10.169:5571/socket.io/socket.io.js"></script> -->
    <!-- 맥북  -->
    <script src="https://localhost:5571/socket.io/socket.io.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="resources/js/broadcast/js/IoConnectException.js"></script>
    <script src="resources/js/broadcast/js/main.js"></script>
    <script src="resources/js/utils/live.js"></script>
</body>
</html>




