<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="description" content="Mixtape template project">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="resources/styles/bootstrap-4.1.2/bootstrap.min.css">
	<link href="resources/plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="resources/plugins/OwlCarousel2-2.2.1/owl.carousel.css">
	<link rel="stylesheet" type="text/css" href="resources/plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
	<link rel="stylesheet" type="text/css" href="resources/plugins/OwlCarousel2-2.2.1/animate.css">
	<link rel="stylesheet" type="text/css" href="resources/styles/main_styles.css">
	<link rel="stylesheet" type="text/css" href="resources/styles/responsive.css">

</head>
<title>Header</title>
</head>

<body>
	<!-- Header -->
	<header class="header">
		<section class="header_content d-flex flex-row align-items-center justify-content-center">
			<div class="logo">
				<a href="home.do">DOL-Duck</a>
			</div>
			<div class="log_reg">
				<ul class="d-flex flex-row align-items-start justify-content-start">
					<sec:authorize access="isAnonymous()">
						<li><a href="${pageContext.request.contextPath}/login.do">Login</a></li>
					</sec:authorize>
					<sec:authorize access="isAuthenticated()">
						<li><a href="logout.do">Logout</a></li>
					</sec:authorize>
					<li><a href="${pageContext.request.contextPath}/join.do">Register</a></li>
				</ul>
			</div>

			<nav class="main_nav">
				<ul class="d-flex flex-row align-items-start justify-content-start">
					<li class="active"><a href="home.do">HOME</a></li>
					<li><a href="youtube.do">YouTube</a></li>
					<li><a href="vote.do">Vote</a></li>
					<li><a href="chart.do">Music Chart</a></li>
					<li><a href="#">LIVE</a></li>
					<li><a href="#">Board</a></li>
					<li class="dropdown"><a href="heartShop.do" class="dropdown-toggle"
							data-toggle="dropdown">Market</a>
						<ul class="dropdown-menu" role="menu">
							<li class="dropdown-item:hover"><a href="heartShop.do">heartShop</a></li>
							<li class="dropdown-item:hover"><a href="Goodies.do">Goodies</a></li>
						</ul>
					</li>
					<li class="form-group has-search">
						<span class="fa fa-search form-control-feedback"></span>
						<input type="text" class="form-control" placeholder="Search">
					</li>
				</ul>
			</nav>
			<div class="hamburger ml-auto">
				<div class="d-flex flex-column align-items-end justify-content-between">
					<div></div>
					<div></div>
					<div></div>
				</div>
			</div>
		</section>
	</header>
	<!-- Menu -->

	<section class="menu">
		<div>
			<div class="menu_overlay"></div>
			<div class="menu_container d-flex flex-column align-items-start justify-content-center">
				<div class="menu_log_reg">
					<ul class="d-flex flex-row align-items-start justify-content-start">
						<li><a href="loginform.do">Login</a></li>
						<li><a href="#">Register</a></li>
					</ul>
				</div>
				<nav class="menu_nav">
					<ul class="d-flex flex-column align-items-start justify-content-start">
						<li><a href="home.do">HOME</a></li>
						<li><a href="youtube.do">You Tube</a></li>
						<li><a href="vote.do">Vote</a></li>
						<li><a href="chart.do">Music Chart</a></li>
						<li><a href="#">LIVE</a></li>
						<li><a href="#">Board</a></li>
						<li><a href="heartShop.do">heartShop</a></li>
						<li><a href="Goodies.do">Goodies</a></li>
					</ul>
				</nav>
			</div>
		</div>
	</section>
</body>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="resources/js/home.js"></script>
<script src="resources/js/jquery-3.2.1.min.js"></script>
<script src="resources/styles/bootstrap-4.1.2/popper.js"></script>
<script src="resources/styles/bootstrap-4.1.2/bootstrap.min.js"></script>
<script src="resources/plugins/greensock/TweenMax.min.js"></script>
<script src="resources/plugins/greensock/TimelineMax.min.js"></script>
<script src="resources/plugins/scrollmagic/ScrollMagic.min.js"></script>
<script src="resources/plugins/greensock/animation.gsap.min.js"></script>
<script src="resources/plugins/greensock/ScrollToPlugin.min.js"></script>
<script src="resources/plugins/OwlCarousel2-2.2.1/owl.carousel.js"></script>
<script src="resources/plugins/easing/easing.js"></script>
<script src="resources/plugins/progressbar/progressbar.min.js"></script>
<script src="resources/plugins/parallax-js-master/parallax.min.js"></script>
<script src="resources/plugins/jPlayer/jquery.jplayer.min.js"></script>
<script src="resources/plugins/jPlayer/jplayer.playlist.min.js"></script>
<script src="resources/js/custom.js"></script>
<script src="https://d3js.org/d3.v4.min.js"></script>
<script src="https://kit.fontawesome.com/c1455fa856.js"></script>

</html>