<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>

<body>
	<%@include file="../header.jsp" %>

	<div class="container market">


		<h1> Market </h1>
		<ul class="nav nav-tabs market_ul">
			<li class="nav-item">
				<a class="nav-link market_link" href="heartShop.do">heartShop</a>
			</li>
			<li class="nav-item">
				<a class="nav-link market_link" href="Goodies.do">Goodies</a>
			</li>

		</ul>
		<div class="col-md-10 market_border">
			<div class="heartShop">
				<div class="heartShop-item-title"><span>하트존</span></div>
				<ul class="">
					<li class="heartShop-item">
						<div class="vote-area-img"><img class="heart_size" src="resources/images/heart.png"></div>
						<div class="vote-area-subject">하트 10개</div>
						<div class="vote-area-button">
							<span class="btn btn-buy-item">1,000원</span>
						</div>
					</li>
					<li class="heartShop-item">
						<div class="vote-area-img"><img class="heart_size" src="resources/images/heart.png"></div>
						<div class="vote-area-subject">하트 20+1개</div>
						<div class="vote-area-button">
							<span class="btn btn-buy-item">2,000원</span>
						</div>
					</li>
					<li class="heartShop-item">
						<div class="vote-area-img"><img class="heart_size" src="resources/images/heart.png"></div>
						<div class="vote-area-subject">하트 50+5개</div>
						<div class="vote-area-button">
							<span class="btn btn-buy-item">5,000원</span>
						</div>
					</li>
					<li class="heartShop-item">
						<div class="vote-area-img"><img class="heart_size" src="resources/images/heart.png"></div>
						<div class="vote-area-subject">하트 100+10개</div>
						<div class="vote-area-button">
							<span class="btn btn-buy-item">10,000원</span>
						</div>
					</li>
				</ul>
				<div class="heartShop-item-title"><span>투표권</span></div>
				<ul>
					<li class="heartShop-item">
						<div class="vote-area-img"><img
								src="https://www.bestidol.co.kr/assets/image/icon/store/store-item-vote.png"></div>
						<div class="vote-area-subject">투표권 1개</div>
						<div class="vote-area-button">
							<span class="btn btn-buy-item"><i class="fas fa-heart"></i>&nbsp;5</span>
						</div>
					</li>
					<li class="heartShop-item">
						<div class="vote-area-img"><img
								src="https://www.bestidol.co.kr/assets/image/icon/store/store-item-vote.png"></div>
						<div class="vote-area-subject">투표권 11개</div>
						<div class="vote-area-button">
							<span class="btn btn-buy-item"><i class="fas fa-heart"></i>&nbsp;50</span>
						</div>
					</li>
					<li class="heartShop-item">
						<div class="vote-area-img"><img
								src="https://www.bestidol.co.kr/assets/image/icon/store/store-item-vote.png"></div>
						<div class="vote-area-subject">투표권 55개</div>
						<div class="vote-area-button">
							<span class="btn btn-buy-item"><i class="fas fa-heart"></i>&nbsp;250</span>
						</div>
					</li>
					<li class="heartShop-item">
						<div class="vote-area-img"><img
								src="https://www.bestidol.co.kr/assets/image/icon/store/store-item-vote.png"></div>
						<div class="vote-area-subject">투표권 120개</div>
						<div class="vote-area-button">
							<span class="btn btn-buy-item"><i class="fas fa-heart"></i>&nbsp;500</span>
						</div>
					</li>
				</ul>
			</div>
		</div>


	</div>
	<%@include file="../footer.jsp" %>
</body>

</html>