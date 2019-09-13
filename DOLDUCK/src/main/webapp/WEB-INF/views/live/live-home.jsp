<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All About K-POP! DD - Live</title>
<link rel="stylesheet" type="text/css" href="resources/styles/live.css">
<style>
	div.video-container{
        border : 1px solid #ddd;
        display: inline-block;
        width: 230px;	
        height: 250px; 
        margin: 15px 25px;
    }
    div.video-item-thumb{
    	width: 100%;
    	margin:0;
    	padding: 0;
    	cursor: pointer;
    }
    div.video-item-thumb img{
    	width: 100%;
    	height: 150px;
    }
    div.video-item-info{
    	margin-left: 10px;
    }
    a.video-title{
        margin: 6px;
        margin-left: 10px;	
        font-size: 17px;
        text-decoration: none;
        border-bottom: none;
        cursor: pointer;
    }
    a.video-title:hover{ background-color: #c0aded; }
    p.video-info{
        font-size: 12px;
        color: #444;
        display: inline-block;
    }

</style>
</head>
<body>
    <!-- Header -->
    <%@include file="/WEB-INF/views/header.jsp" %>
    <!-- Slide Caresel  -->

    <%@include file="/WEB-INF/views/live/liveroom-slide.jsp" %>
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
         
         <!-- Title & Banner -->
	   	<div class="container"> 
	         <div class="live-banner" style="background-image: url('resources/images/banner-live-home.png')"></div>
	    </div>
	    
	     <!-- Home - 동영상 목록 -->
         <div class="center live-content">
         	<c:choose>
         		<c:when test="${empty list}">
         			<div><h4>동영상이 없습니다!</h4></div>
         		</c:when>
         		<c:otherwise>
         			<c:forEach items="${list}" var="item">
         				<div class="video-container">
					        <div class="video-item-thumb">
						        <img src="${item.thumb}">
						       	<input type="hidden" name="video_seq" value="${item.seq}">
							    <p class="video-title">${item.title}</p>
						    </div>
					        <div class="video-item-info">
					        	<p class="video-info">좋아요</p>&nbsp;
					        	<p class="video-info">재생수</p><br>
					        	<p class="video-info">${item.date}</p>
					        </div>
					    </div>
         			</c:forEach>
         		</c:otherwise>
         	</c:choose>
        </div>

    </div>

    <!-- Footer -->
    <%@include file="/WEB-INF/views/footer.jsp" %>
    
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="resources/js/utils/live.js"></script>
    
</body>
</html>