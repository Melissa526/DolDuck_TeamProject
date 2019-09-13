<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All About K-POP! DD - Live</title>
<link rel="stylesheet" type="text/css" href="resources/styles/live.css">
</head>
<body>
    <!-- Header -->
    <%@include file="../header.jsp" %>
    <!-- Slide Caresel  -->
    <%@include file="/WEB-INF/views/live/liveroom-slide.jsp" %>

    <!-- Tab Menu -->
    <div id="main" class="container nav-tab-menu">
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
    	<div class="live-banner" style="background-image: url('resources/images/banner-live-channel.png')"></div>
    </div>
    
    <!--  -->
    <div class="container">
    	<div class="channel-list"> 
   	 		
    	</div>
    </div>
    
    
    <!-- Footer -->
    <%@include file="/WEB-INF/views/footer.jsp" %>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="resources/js/utils/live.js"></script>
</body>
</html>