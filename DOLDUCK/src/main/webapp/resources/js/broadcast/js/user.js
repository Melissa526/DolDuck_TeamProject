/*  USER  */
var socket = io.connect('https://localhost:5571')

var query = location.href.split('/')[4]
var param = query.split('?')[1]
var requestedRoom = param.split('=')[1]
console.log(requestedRoom)
//var requestedRoom = $('#userRoom').atrr('room')

var remoteStream
var remoteVideo = document.getElementById('remoteVideo')
var pc;
var pcConfig = {
    'iceServers': [
        {
            'urls': 'stun:stun.l.google.com:19302'
        }
        ,
        {
            urls: 'turn:numb.viagenie.ca',
            credential: 'muazkh',
            username: 'webrtc@live.com'
        }
    ]
};

const constraints = {
    audio: true,
    video: {
    width: 1280, height: 720
    }
};


/**************************** 
          User Info
*****************************/
var name = $('#user_id').text()
var _room = requestedRoom
console.log(`${name}님이 ${_room}에 접속하였습니다`)


/**************************** 
            Socket
*****************************/

//user 접속 
socket.emit('user-join', _room, name)

socket.on('joinedUser', (name, id, numberofClients) => {
    $('#numoof-visitor').text(numberofClients)
    //$('#channel-name').text('('+roomInfo.room+')')
    //$('#onair-title').text(roomInfo.title)
})

socket.on('roomSetting', (roomInfo) => {
    console.log('정보 세팅하기');
    console.log(roomInfo)
    $('#channel-name').text(roomInfo.caster)
    $('#onair-title').text(roomInfo.title)
})

socket.on('message', (msg) => {
    if(msg.type === 'offer'){
        console.log(`(Offer)받은메시지`)
        createPeerConnectionUser()
        pc.setRemoteDescription(new RTCSessionDescription(msg))
        sendAnswer()
    } else if (msg.type === 'candidate') {
        var candidate = new RTCIceCandidate({
            sdpMLineIndex: msg.label,
            candidate: msg.candidate
        })
        pc.addIceCandidate(candidate)
    } else if(msg.type === 'bye'){
        alert('방송이 종료되었습니다')
        handleRemoteHangup()
    }else{
        console.log(`잘 못 보낸 메세지입니다! ${msg}`)
    }

})

//Chat
socket.on('chat-message', (name, msg) => {
    appendMessage(name, msg)
})

socket.on('livedCaster', (room) =>{
    alert('방송이 종료되었습니다')
    location.href='https://localhost:5571/'
    //location.href='http://192.168.10.169:8787/dolduck/live-home.do'
})

/**************************** 
    WebRTC - PeerConnection
*****************************/
function createPeerConnectionUser(){    
    try{
        pc = new RTCPeerConnection(null)
        pc.onaddstream =  handleRemoteStreamAdded
        pc.onicecandidate = handleIceCandidateUser
        pc.onremovestream = handleRemoteStreamRemoved
        console.log('(User)PeerConnection Created')

    }catch(e){
        console.log('Error in Creating (User)PeerConnection , e: ', e);
        alert('Cannot create RTCPeerConnection object.');
        return;
    }
}

function sendAnswer(){
    console.log('Sending answer to Remote Peer ');
    pc.createAnswer()
    .then(setLocalAndSendMessageUser, handleCreateSessionDescriptionError)
}

function setLocalAndSendMessageUser(sdp){
    pc.setLocalDescription(sdp)
    console.log('setLocalAndSendMessage : ', sdp)
    sendMessage(sdp)
}

function handleCreateSessionDescriptionError(err){
    console.log('Failed to create session descriptioin : ', err.toString())
}

function handleRemoteStreamAdded(event) {
    console.log('Remote stream added.');
    remoteStream = event.stream;
    remoteVideo.srcObject = remoteStream;
}

function handleIceCandidateUser(event){
    console.log('IceCandidate event : ', event)
    if(event.candidate){
        sendMessage({
            type : 'candidate',
            label : event.candidate.sdpMLineIndex,
            id : event.candidate.sdpMid,
            candidate : event.candidate.candidate
        })
    }else{
        console.log('End of candidate')
    }
    
}

function handleRemoteStreamRemoved(event) {
    console.log('Remote stream removed. Event: ', event);
}

function hanldeRemoteHangup(id){
    console.log('Session terminated')
    close(id)
    sendByeMessage()
}

function close(id){
    findPc(id).close()
}

function sendMessage(msg){
    console.log(`클라이언트가 보냄 -> ${msg}`)
    socket.emit('userMessage', msg, _room)
}

function sendByeMessage(){
    socket.emit('message', 'bye')
}



/*******************
    Chatting
*******************/

function appendMessage(userName, msg){
    var _name = userName
    var text;
    text = `<p class="nameSpace">${_name}</p>&nbsp;<p>${msg}</p>`
    $('#messages').append($(`<li>`).html(text))
    $(".chatroom").scrollTop($("#msgDiv")[0].scrollHeight);
}

function onChatSubmit(){
    if(event.keyCode == 13){
        event.preventDefault()
        var msg = $('#msg').val().trim();
        if (msg != "" && msg != null) {
            socket.emit('chat-message', _room, name , msg)
        }
        $('#msg').val('');
    }
}

