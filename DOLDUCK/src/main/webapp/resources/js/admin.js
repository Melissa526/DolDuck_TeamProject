var acc = document.getElementsByClassName("accordion");
var i;

for (i = 0; i < acc.length; i++) {
  acc[i].addEventListener("click", function() {
    this.classList.toggle("active");
    var panel = this.nextElementSibling;
    if (panel.style.display === "block") {
      panel.style.display = "none";
    } else {
      panel.style.display = "block";
    }
  });
}

function roleUpdate(role) {
	if(role == 'ROLE_ARTIST'){
		role = 'ROLE_ARTIST'
			alert(role);
	}else if(role == 'ROLE_USER'){
		role = 'ROLE_USER'
			alert(role);
	}
	return role;
}


$(document).ready(function(){
	$("#upgrade").click(function(){
		alert(role);
		alert(id);
		$.ajax({
			type : 'post' ,
			url : 'role_update.do',
			data : {"role":role,"id":id},
			dataType : 'text',
			success : function(data){
				alert(data);
				//성공했을때 동작
			},error: function(){
				alert('네트워크 오류');
				// 실패했을때 동작
			}
	});
});
})
/*
$.ajax({
   type : 'post' ,
   url : 'update-grade.do',
   data : '보낼 데이터' (예시 -> { name : '한지수' , age : 27} ),
   dataType : '받을 데이터의 타입'  (예시 ->'text'),
   success : function(data){
      alert(data)
   //성공했을때 동작
   },error: function(){
      alert('네트워크 오류')
   // 실패했을때 동작
   }
})

function textLengthOverCut(txt, len, lastTxt) {
    if (len == "" || len == null) { // 기본값
        len = 10;
    }
    if (lastTxt == "" || lastTxt == null) { // 기본값
        lastTxt = "...";
    }
    if (txt.length > len) {
        txt = txt.substr(0, len) + lastTxt;
    }
    return txt;
}
*/