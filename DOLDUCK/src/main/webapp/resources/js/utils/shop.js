
$(function(){
	
	var kindOf, amount, price
	
	var login_status
	
	$(document).on('click', '.btn-buy-item', function(){
		kindOf = $(this).children('input[name=kindof]').val()
		amount = $(this).children('input[name=item-amount]').val()
		price = $(this).children('input[name=item-price]').val()
		
		if(kindOf == 'heart'){
			$('#selected-item').html(`<img class="heart_size"src="resources/images/heart.png" style="width:25px; margin:0;">`)
			$('.modal-body #total-price').html(`<b>${price}</b>&nbsp; 원`)
		}else if(kindOf == 'vote'){
			$('#selected-item').html(`<img src="https://www.bestidol.co.kr/assets/image/icon/store/store-item-vote.png"> 투표권 `)
			$('.modal-body #total-price').html(`<img class="heart_size"src="resources/images/heart.png" style="width:25px; margin:0;">&nbsp;<b>${price}</b>`)
		}
		$('#total-amount').text(amount)
		$('.modal-body #total-price b').text(`${price}`)
		$('.modal').modal('show')
		
	}).on('click', '.btn-purchase', function(){

		login_status = checkLoginStatus()
		
		if(login_status == 'true'){
			//Purchase Process
			if(kindOf == 'heart'){
				var IMP = window.IMP; 
				IMP.init('imp70049115'); 
				IMP.request_pay({
					amount : price,
					buyer_name : '돌덕',
					name : `[돌덕]${kindOf}`,
				}, function(response) {						//결제 후 호출되는 callback함수
					if ( response.success ) { 				// Payment Successed
						console.log(response);
						ajaxPurchase('buy-heart.do', amount, price)
						$('.modal').hide()
						location.href = 'heartShop.do'
					} else {								// Payment Failed
						Swal.fire({
							type : 'error', 
							title: 'Error',
							text : '결제실패 : ' + response.error_msg
						});
					}
					
				})
			}else if(kindOf == 'vote'){
				
				ajaxPurchase('buy-vote.do', amount, price)
				$('.modal').hide()
				location.href = 'heartShop.do'
			}
		
		}else if(login_status == 'false'){
			//alert('로그인이 필요한 서비스입니다!')
			Swal.fire({
				type : 'error',
				title : '알 림',
				text : '로그인이 필요한 서비스입니다!\n'
			}).then(function(){
				location.href = 'login.do'
			})
		}
	})
	
})

function checkLoginStatus(){
	var result 
	
	$.ajax({
		type : 'post',
		url : 'login-status.do',
		async : false,
		success : function(res){
			result = res
			console.log('로그인 상태 확인 결과!!', res)
		},
		error: function(err){
			 console.log('ajax 통신에러')
		}
	})
	
	return result
}

//Insert and Update at DataBase of User
function ajaxPurchase(comm, amount, price){
	$.ajax({
		type : 'post',
		url : comm,
		data: {
			'price' : parseInt(price),
			'amount' : parseInt(amount)
		},
		success : function(msg){
			console.log(msg)
			if(msg == 'succeed'){
				Swal.fire({
					type : 'success',
					titlte :'투표권 구매성공!',
					text : `투표권 ${amount}개 구매에 성공하셨습니다!`
				})
			}
			$('.modal').modal('hide')
			
		},error : function(err){
			console.log(err)
			Swal.fire({
				type : 'error',
				title : 'Error',
				text : '네트워크오류. 다시 시도해주세요 '
			})
		}
	})
}
