function login(){
		
		var idVal = $('#id').val();
		var pwVal = $('#pw').val();
		
		if(idVal == null || idVal == ""){
			alert('아이디를 확인해주세요')
		}else if(pwVal==null || pwVal == ""){
			alert('패스워드를 입력해주세요')
		}else{
			
			$.ajax({
				type : "post",
				url : "loginDo",	
				data : "id=" + idVal + "&pw=" + pwVal,
				success : function(data){
					if(data.loginChk == true){
						location.href="/home.do";
					}else{
						alert('아이디, 또는 패스워드를 확인해주세요');
					}
				},
				error: function(){
					alert('로그인 통신실패');
				}
			});
		}
		
	}

	function enter_input(){
		if(window.event.keyCode == 13){
			login();
		}
	}