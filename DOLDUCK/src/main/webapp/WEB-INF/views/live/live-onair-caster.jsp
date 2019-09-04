<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>D.Live OnAir</title>
<link href="resources/plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="resources/styles/live.css">
<link rel="stylesheet" type="text/css" href="resources/styles/broadcast.css">
<!-- <link rel="stylesheet" href="resources/js/broadcast/css/broadcast.css">-->
</head>
<body>
    <!-- Header -->
    <%@include file="../header.jsp" %>
    
    <!-- Tab Menu -->
    <div id="main" class="container nav-tab-menu" style="margin-top:8em;">
        <!-- Tab Menu -->
    	<div class="col-lg-12 offset-lg-4 "> 
            <nav class="nav">
               <ul class="nav nav-tabs">
                  <li class="nav-item" id="nav-link-home"><a class="nav-link" href="live-home.do">&nbsp;&nbsp;홈&nbsp;&nbsp;</a></li>
                  <li class="nav-item" id="nav-link-schedule"><a class="nav-link" href="live-schedule.do">일정표</a></li>
                  <li class="nav-item" id="nav-link-onair"><a class="nav-link" href="live-onair.do">온에어</a></li>
                  <li class="nav-item" id="nav-link-channel"><a class="nav-link" href="live-channel.do">채널보기</a></li>
               </ul>
            </nav>
         </div>
    </div>

   	<!-- content body -->
    <div class="container stream-box">
        <div class="row" style="margin-bottom:1em;">
            <div class="col-lg-6" style="position: relative;">
                <div class="channel-description form-inline">
                	<c:choose>
                		<c:when test="${caster.member_img eq '이미지'}">
		                    <div id="channel-icon" style="background-image: url('${pageContext.request.contextPath }/resources/profileImage/profile.jpg');"></div>
                		</c:when>
                		<c:otherwise>
                			<div id="channel-icon" style="background-image: url('${caster.member_img}');"></div>
                		</c:otherwise>
                	</c:choose>
                    <div id="channel-name-area" style="margin-left:1em;"><a id="channel-name" href="#" >${caster.member_id}</a></div>
                    <div id="channel-follow" style="margin-left: 7em;">
                        <span><i class="far fa-star" style="margin:0;"></i></span>
                        <!-- Status in following:  <i class="fas fa-star"></i> -->
                        <span>팔로우</span></div>
                </div>
            </div>
        </div>
        <!-- Channel Logo's Title -->


        <!-- Control Box -->
        <!-- # If Role of user is 'ADMIN' or 'Artist', SHOW THIS -->
        <!-- DON'T SHOW for user in 'user' -->
        <div class="channel-controlbox">
                <button type="button" class="btn btn-secondary" id="stopButton">STOP</button>
        </div>

        <div class="form-inline">
            <!-- live-booth -->
            <div class="live-booth">
                <!-- Video  -->
                <div class="channel-video-area">
                    <video class="video" id="localVideo" autoplay></video>
                </div> 
                <!-- Live Stream Title -->
                <div class="channel-onair-info">
                    <div id="info-title">
                        <span id="onair-title">${live.broadcast_title}</span>
                        <p><span>방송시작시간: </span><span>${live.broadcast_date}</span></p>
                    </div>
                    <!-- Number of visitors -->
                    <div id="info-numOf-visitor">
                        <span><h4 id="numoof-visitor"></h4></span>
                        <span>명 접속중</span>
                    </div>
                    
                </div>
            </div>
            <!-- Chatroom -->
            <div class="pull-right chatbox">
                <div class="chatroom-top">
                    <h4>채팅</h4>
                    <h5>실시간 채팅</h5>    
                </div>
                <div class="chatroom">
                        <div id="msgDiv">
                            <ul id="messages"></ul>
                        </div>
                </div>
                <div class="chatroom-form">
                    <form action="">
                        <input type="text" id="msg" autocomplete="off" onKeyDown="onChatSubmit()">
                </form>
                </div>
            </div>
        </div>
    </div>

    <!-- footer -->
    <!-- <%@include file="../footer.jsp"%> -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    <script src="https://kit.fontawesome.com/c1455fa856.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script src="https://localhost:5571/socket.io/socket.io.js"></script>
    <script src="https://webrtc.github.io/adapter/adapter-latest.js"></script>
    <script id="casterInfo" src="resources/js/broadcast/js/caster.js" room="${room_number}"></script>
</body>
</html>