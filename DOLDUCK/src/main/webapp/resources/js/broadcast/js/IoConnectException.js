try {
	//맥북
    //var socket = io.connect('https://192.168.10.107:5571', { rejectUnauthorized: false })
<<<<<<< HEAD
	
	var socket = io.connect('https://localhost:5571', { rejectUnauthorized: false });
=======
	var socket = io.connect('https://192.168.10.169:5571', { rejectUnauthorized: false });
>>>>>>> 67adb92d0029ba25c27ce410ab2d81778b9aaef6
} catch (error) {
    console.log('Socket.io Exception : ', error)
    Swal.fire({
    	type : 'error',
    	title : '유료 인증서 오류',
    	text : '[고급]탭에서 이동하기를 클릭해주세요!'
    }).then(function(){
    	location.href = 'https://localhost:5571/IoException'
    })
}