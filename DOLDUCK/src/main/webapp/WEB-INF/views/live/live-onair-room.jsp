<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>D.Live OnAir - Live</title>
    <link rel="stylesheet" type="text/css" href="resources/styles/broadcast.css">
    <link rel="stylesheet" type="text/css" href="resources/styles/bootstrap-4.1.2/bootstrap.min.css">
	<link href="resources/plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="resources/plugins/OwlCarousel2-2.2.1/owl.carousel.css">
	<link rel="stylesheet" type="text/css" href="resources/plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
	<link rel="stylesheet" type="text/css" href="resources/plugins/OwlCarousel2-2.2.1/animate.css">
	<link rel="stylesheet" type="text/css" href="resources/styles/main_styles.css">
	<link rel="stylesheet" type="text/css" href="resources/styles/responsive.css">
</head>
<body>
    <!-- header -->
    <%@include file="../header.jsp"%>

    <!-- content body -->
    <div class="container">
        <!-- Channel Logo's Title -->
        <div class="channel-description">
            <div id="channel-icon"></div>
            <div id="channel-name-area"><a id="channel-name" href="#">Channel Name</a></div>
            <div id="channel-follow">
                <span><i class="fal fa-star"></i></span>
                <!-- Status in following:  <i class="fas fa-star"></i> -->
                <span>팔로우</span></div>
        </div>

        <!-- Control Box -->
        <!-- # If Role of user is 'ADMIN' or 'Artist', SHOW THIS -->
        <!-- DON'T SHOW for user in 'user' -->
        <div class="channel-controlbox">
                <button id="startButton">START</button>
                <!-- <button id="recButton">REC</button> -->
                <button id="stopButton">STOP</button>
        </div>
        <!-- live-booth -->
        <div class="center">
            <div class="live-booth">
                <!-- Video  -->
                <div class="channel-video-area">
                    <video id="video" width="925" height="550" autoplay></video>
                </div> 
                <!-- Chatroom -->
                <div class="chatroom">
                    <ul id="messages"></ul>
                    <form action="">
                        <input type="text" id="msg" autocomplete="off">
                        <button class="btn-send">SEND</button>
                    </form>
                </div>
                <!-- Live Stream Title -->
                <div class="channel-onair-info">
                    <div><h2 id="onair-title">Live Stream Title on this</h2></div>
                    <div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- footer -->
    <%@include file="../footer.jsp"%>

    <script src="/socket.io/socket.io.js"></script>
    <script src="./js/caster.js"></script>
</body>
</html>