//var socket = io.connect('https://192.168.10.169:5571')
//var startLiveBtn = document.getElementById('startLiveBtn')


var reloadBtn = document.getElementById('channel-reload')
var channelList = document.getElementById('channel-list')

var random 
var roomArr = []


$(function(){
    socket.emit('requestRoomlist')
    
    socket.on('roomlist', (rooms) => {
        console.log(rooms)
        if(rooms.length == 0){
        	$('#channel-item').html('')
            $('#channel-item').append($(`<li>`).html(`<p class="no-live-room">아직 방송중인 방이 없습니다!</p>`))
        }else{
            addRoomList(rooms)
        }
    })

})

function addRoomList(list){
    $('#channel-item').html('')
    list.forEach( item => {
        console.log(item);
        appendRoom(item)
    });
}

function appendRoom(item){
    var roomUnit = `<li class="channel-box">
    					<div>
	    			        <a onclick="checkLoginStatus(${item.room})">
	                        	<img src="${item.thumb}" width="228" height="128">
	                    	</a>
                    	</div>
                    	<div class="channel-info">
                    		<p><a href="join-onair.do?room=${item.room}">[${item.caster}] ${item.title}</a></p>
                    		<p>방송시작시간: ${item.date}</p>
                    	</div>
                    </li>`
    $('#channel-item').append(roomUnit)
}

function checkLoginStatus(room){
	$.ajax({
		type : 'post',
		url : 'login-status.do',
		success : function(res){
			if(res == 'true'){
				loaction.href=`join-onair.do?room=${room}`
			}else {
				alert('로그인이 필요한 페이지입니다')
				locatio.href='login.do'
			}
		}
	})
}


reloadBtn.addEventListener('click', ()=>{
	console.log('룸 목록 새로고침!')
    socket.emit('requestRoomlist')
})