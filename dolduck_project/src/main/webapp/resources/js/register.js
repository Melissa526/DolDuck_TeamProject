	var email_chk = false;
	var idChk = false;
	var pass_chk = false;
	var pass_cfm = false;
	var nickChk = false;
	
	var widgetId1;
	
	var onloadCallback = function(){
		widgetId1 = grecaptcha.render('example1', {
	        'sitekey' : '6LenA6cUAAAAAPu-anBVg9EEUxfQ9cgVkFalKvwN',
	        'theme' : 'light'
	    });
	}
	
	function sendEmail(){

		var user_email = $("input[name=user_email]").val();
		if(user_email == null || user_email == ""){
			alert("이메일을 입력해 주세요");								
		}else{
			$.ajax({
				url:"sendEmail.do",
				type:"GET",
				data:"email="+user_email,
		
				success:function(data){
					alert("이메일 전송 완료")
					$("input[name=code]").change(function(){
						if($("input[name=code]").val()==data){
							$("div[id=confirm]").html('');
							email_chk = true;
						}else{
							$("div[id=confirm]").html('');
							$("div[id=confirm]").html('인증실패 다시 쓰세요');
							$("div[id=confirm]").val("");
						}
					});
				},
				error:function(){
					
				}
			});
		}
		
	}
	
	function ranNumChk(){
		var ranNumPass = $("input[name=ranNum").val();
		
		$.ajax({
			url:"emailCheck.do",
			type:"GET",
			data:"ranNumPass=" + ranNumPass,
			success:function(data){
				if(ranNumPass == null || ranNumPass == ""){
					ranNumChk = false;
					$("div[id=confirm").html('<b>인증번호를 입력해 주세요</b>');
				}else{
					if(data.ranChk != true){
						ranNumChk = false;
						$("div[id=emailChkConfirm]").html('');
						$("div[id=confirm").html('<b style="color:red;">인증번호 확인 실패</b>');
					}else{
						ranNumChk = true;
						$("div[id=emailChkConfirm]").html('');
						$("div[id=confirm").html('<b style="color:green;">인증번호 확인 완료</b>');
					}
				}
			},
			error:function(){
				alert("이메일체크 에러")
			}
		})
	}
	
	function id_check(){
		
		var user_id = $("input[name=user_id]").val();
		if(user_id == null || user_id == ""){
			alert("아이디를 입력하세요");
		}else{
		
			$.ajax({
				url:"idChk.do",
				type:"GET",
				data : "id="+user_id,

				success:function(data){
					if(data.idChk != true){
						var idChk = false;
						$(".result .msg").text('이미 사용중인 아이디 입니다.');
						$(".result .msg").attr("style", "color:#f00")
					}else{
						 $(".result .msg").text("사용 가능");
						 $(".result .msg").attr("style", "color:#00f");
					}
				},
				error:function(){
					alert("아이디 확인 실패");
				}
			});
		}
	}
	
	function setAddr() {
		new daum.Postcode({
			oncomplete : function(data) {
				var fullAddr = ""; //최종 주소 변수
				var extraAddr = ""; //조합형 주소 변수
				if (data.userSelectedType === 'R') {
					//사용자가 도로명 주소를 선택했을 경우
					fullAddr = data.roadAddress;
				} else {//사용자가 지번 주소를 선택했을 경우
					fullAddr = data.jibunAddress;
				}

				//사용자가 선택한 주소가 도로명 타입일 때 조합한다.
				if (data.userSelectedType === 'R') {
					//법정 동명이 있을 경우 추가한다.
					if (data.bname !== "") {
						extraAddr += data.bname
					}//건물명이 있을 경우
					if (data.buildingName !== "") {
						extraAddr += (extraAddr !== '' ? ', '
								+ data.buildingName : data.buildingName);
					}//조합형 주소의 유무에 따라 양쪽에 괄호를 추가해 최종 주소를 만든다.
					fullAddr += (extraAddr !== '' ? ' (' + extraAddr + ')'
							: '');
				}

				document.getElementById("addr").value = fullAddr;
				document.getElementById("zonecode").value = data.zonecode;
				document.getElementById("detail_addr").focus();

			}
		}).open();
	}
	
	function pass_check(){
		var length = $("input[name=user_pw]").val().length;
		if(length<6){
			$("div[id=pass_check]").html('비밀번호 6자리이상 입력해 주세요');
			$("div[id=pass_check]").val("");
		}else{
			$("div[id=pass_check]").html('');
			pass_chk = true;
		}
	}
	
	function pass_confirm(){
		var once = $("input[name=user_pw]").val();
		var twice = $("input[name=user_pw02]").val();
		if(once != twice){
			$("div[id=pass_confirm]").html('비밀번호 같지 않습니다.');
			$("div[id=pass_confirm]").val("");
		}else{
			$("div[id=pass_confirm]").html('비밀번호 같음 확인');
			pass_cfm = true;
		}
	}
	
	function formsubmit(){
		if(grecaptcha.getResponse(widgetId1) != null && grecaptcha.getResponse(widgetId1) != "" && email_chk == true &&	id_chk == true && pass_chk == true && pass_cfm == true && nick_chk == true){
			alert("회원가입 완료!");
			return true;
		}
		alert("다시 확인해 주세요!")
		return false;
	}