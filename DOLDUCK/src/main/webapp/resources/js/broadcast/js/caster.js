/**************************** 
        RTC & Peer Info
*****************************/
var localStream
var remoteStream
var pc
var pcArr = []
var pcConfig = {
    'iceServers': [
        { 'urls': 'stun:stun.l.google.com:19302' }
        ,
        {
            urls: 'turn:numb.viagenie.ca',
            credential: 'muazkh',
            username: 'webrtc@live.com'
        }
    ]
}
var sdpConstraints = {      
    offerToReceiveAudio: 1,         
    offerToReceiveVideo: 1              // 1 = true    
};

var video = document.getElementById('localVideo')
const constraints = {
    audio: true,
    video: { width: 1280, height: 720 }
};

/**************************** 
          Caster Info
*****************************/
var name = $('#channel-name').text()
var title = $('#onair-title').text()
var room_number = $('#casterInfo').attr('room')
var _room
var clients = []

$(function(){
	/*room_number = location.href.split('/')
	console.log(room_number)*/
    console.log(`Request room is.. caster: ${name} / title : ${title} / room : ${room_number}`)
	
    init(constraints); 
    stopBtn.disabled = true
})


/**************************** 
            Socket
*****************************/
var socket = io.connect('https://localhost:5571')

socket.emit('create', name, title, room_number)

socket.on('createdRoom', (roomNumber) =>{
    _room = roomNumber
    console.log(`this room number is ${_room}`)

    var roomInfo = {
        'room' : _room,
        'caster' : name,
        'casterid' : socket.id,
        'title' : title,
        'thumb' : `https://v-phinf.pstatic.net/20190813_56/1565623602340qwcBD_JPEG/upload_2.jpg?type=f228_128`,
        'date' : getTimeStamp()
    }
    socket.emit('caster-join', roomInfo)
})

socket.on('joinedUser', (name, id, numberofClients) => {
    var newUser = {
        name : name,
        id : id
    }
    $('#numoof-visitor').text(numberofClients)
    clients.push(newUser)
    console.log(`(New user joined)${newUser.name}님이 접속하였습니다!`);
    casterPeerCreate(newUser.id)
})

socket.on('message', (message,  id) => {
    console.log('Client received message: ', message)
    if(message.type === 'answer'){
        console.log('Received Answer message')
        commit(findPc(id).setRemoteDescription(new RTCSessionDescription(message)),id)
    }else if(message.type === 'candidate'){
        var candidate = new RTCIceCandidate({
            sdpMLineIndex : message.label,
            candidate : message.candidate
        })
        commit(findPc(id).addIceCandidate(candidate), id)
    }else if(message.type === 'bye'){
        hanldeRemoteHangup()
    }
})

socket.on('chat-message', (name, msg) => {
    appendMessage(name, msg)
})

/**************************** 
    WebRTC - PeerConnection
*****************************/

function findPc(id) {
    for (let i = 0; i < pcArr.length; i++) {
        if (pcArr[i].id === id) {
            return pcArr[i].pc
        }
    }
}

function commit(pc, id){
    for (let i = 0; i < pcArr.length; i++) {
        if (pcArr[i].id === id) {
            pcArr[i].pc === pc
        }
    }
}

function casterPeerCreate(id) {
    console.log('피어 커넥션 생성');


    pcArr.push({ 'id': id, 'pc': createPeerConnection(id) })
    console.log('생성된 피어 : ', findPc(id) );
    commit(findPc(id).addStream(localStream), id)
    sendOffer(id)
}

function createPeerConnection(id){
    var emptyPc
    try{
        emptyPc = new RTCPeerConnection(null)
        emptyPc.onicecandidate = function(event){
            handleIceCandidateCaster(event, id)
        }
        //emptyPc.onaddStream = handleRemoteStreamAdded
        //emptyPc.onremovestream = handleRemoteStreamRemoved
        console.log('Created RTCPeerConnection')
        return emptyPc
    }catch(e){
        console.log('Failed to create PeerConnection.\nexception : ', e)
        alert('Cannot create RTCPeerConnection object.')
        return;
    }
}

function sendOffer(id) {
    console.log(`Send Offer to Client(${id})`);
    findPc(id).createOffer()
        .then(function (sessionDescription) {
            setLocalAndSendMessageCaster(sessionDescription, id)
        })
        .catch(function (err) {
            handleCreateOfferError(err)
        })
}

function setLocalAndSendMessageCaster(sdp, id){
    commit(findPc(id).setLocalDescription(sdp), id)
    console.log('SetLocal And SendMessage sending message : ', sdp)
    sendMessage(sdp, id)
}

function handleCreateOfferError(err){
    console.log(`Error : ${err}`);
}

function handleIceCandidateCaster(e, id){
    console.log('icecandidate event: ', e)
    if(e.candidate){
        sendMessage({
            type : 'candidate',
            label : e.candidate.sdpMLineIndex,
            id : e.candidate.sdpMid,
            candidate : e.candidate.candidate
        },id)
    }else{
        console.log('End of candidates.')
    }
}

function handleRemoteStreamAdded(event){
    console.log('Remote Stream Added')
    remoteStream = event.stream
    remoteVideo.srcObject = remoteStream
}

function handleRemoteStreamRemoved(event){
    console.log('Remote Stream Removed : ', event);
}

function hanldeRemoteHangup(id){
    console.log('Session terminated')
    close(id)
}

function close(id){
    commit(findPc(id).close(), id)
}

function sendMessage(msg, id){
    console.log(`sendMessage : ${msg} / ${id}`);
    socket.emit('casterMessage', msg, id)
}

function sendByeMessage(){
    socket.emit('message', 'bye')
    socket.emit('CasterLived', _room)
}


window.onbeforeunload = function () {
    sendByeMessage()
};

/** Setting TURN Server **/
var turnReady

function requestTurn(turnURL){
    var turnExists = false;
    for(var i in pcConfig.iceServers){
        if(pcConfig.iceServers[i].urls.substr(0,5) === 'turn:'){
            turnExists = true;
            turnReady = true;
            break;
        }
    }
    if(!turnExists){
        console.log('Getting TURN server from', turnURL)

        var xhr = new XMLHttpRequest()
        xhr.onreadystatechange = function(){
            if(xhr.readyState === 4 && xhr.status === 200){
                var turnServer = JSON.parse(xhr.responseText)
                console.log('Got TURN server: ', turnServer)
                pcConfig.iceServers.push({
                    'urls' : 'turn:' + turnServer.username + '@' + turnServer.turn,
                    'credential' : turnServer.password
                })
                turnReady = true
            }
        }
        xhr.open('GET', turnURL, true)
        xhr.send()
    }
}


/**************************** 
    WebRTC- Recording Video
*****************************/

//var startBtn = document.getElementById('startButton')
var stopBtn = document.getElementById('stopButton')

const mediaSource = new MediaSource()
let mediaRecorder;
let recordedBlobs = [];
let sourceBuffer;
var videoSeq = 0;

function handleSourceopen(e){
    console.log('MediaSource Opened')
    sourceBuffer = mediaSource.addSourceBuffer('video/webm; codecs="vp8"')
    console.log('Source buffer: ', sourceBuffer)
}

function handleSuccess(stream){
    console.log('getUserMedia() got stream : ', stream)
    localStream = stream
    //window.stream = stream
    video.srcObject = stream
    startRecording()
}

function handleDataAvailable(event) {
    if (event.data && event.data.size > 0) {
      recordedBlobs.push(event.data);
    }
  }

function startRecording(){
    //Setting Recording Options
    let options = {mimeType: 'video/webm;codecs=vp9'};
    if (!MediaRecorder.isTypeSupported(options.mimeType)) {
      console.error(`${options.mimeType} is not Supported`);
      errorMsgElement.innerHTML = `${options.mimeType} is not Supported`;
      options = {mimeType: 'video/webm;codecs=vp8'};
      if (!MediaRecorder.isTypeSupported(options.mimeType)) {
        console.error(`${options.mimeType} is not Supported`);
        errorMsgElement.innerHTML = `${options.mimeType} is not Supported`;
        options = {mimeType: 'video/webm'};
        if (!MediaRecorder.isTypeSupported(options.mimeType)) {
          console.error(`${options.mimeType} is not Supported`);
          errorMsgElement.innerHTML = `${options.mimeType} is not Supported`;
          options = {mimeType: ''};
        }
      }
    }

    try{
        mediaRecorder = new MediaRecorder(localStream, options)
    }catch(e){
        console.log('Error : ', e);
        return ;
    }   
    
    //녹화시작: rec버튼 무효/stop버튼 유효화 
    stopBtn.disabled = false
    //레코딩이 끝나면 저장
    mediaRecorder.onstop = function(e){
        downloadRecording()
        console.log('Recoreded stop : ', e);
    }

    //녹화 시작!
    mediaRecorder.ondataavailable = handleDataAvailable;
    mediaRecorder.start(); 
    console.log('MediaRecorder started', mediaRecorder);
}

function stopRecording(){
    stopBtn.disabled = true
    mediaRecorder.stop()
    console.log('Recorded Blobs: ', recordedBlobs);
}

function downloadRecording(){
    ++videoSeq;
    //Blob 객체는 파일과 흡사한 불변 객체로 raw data
    const blob = new Blob(recordedBlobs, {type: 'video/webm'});
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.style.display = 'none';
    a.href = url
    a.download = `dd-live-${name}-${videoSeq}.webm`;
    document.body.appendChild(a);
    a.click();
    setTimeout(() => {
      document.body.removeChild(a);
      window.URL.revokeObjectURL(url);
    }, 100);
}


/*****************************
 * 
 * 
 *  여기는 빨간줄 나는게 맞아요!
 *  이클립스 버전문제라서 빨간줄나는데 
 *  동작에는 이상 없습니다!!!
 * 
 * ***************************/
async function init(constraints){
    try {
        const stream = await navigator.mediaDevices.getUserMedia(constraints);
        handleSuccess(stream);
    } catch (e) {
        console.error('navigator.getUserMedia error:', e)
        alert(`Error occured on getUserMedia() : ${e} `)
    }
}


stopBtn.addEventListener('click', ()=>{ stopRecording(); console.log('Stop Recording....')})
mediaSource.addEventListener('sourceopen', handleSourceopen, false)

/************** 
    Chatting
***************/

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
            socket.emit('chat-message', _room, name, msg)
        }
        $('#msg').val('');
    }
}

/*************** 
   Useful Func
****************/

function getTimeStamp() {
    var d = new Date();
    var stamp =
      leadingZeros(d.getFullYear(), 4) + '-' +
      leadingZeros(d.getMonth() + 1, 2) + '-' +
      leadingZeros(d.getDate(), 2) + ' ' +
      leadingZeros(d.getHours(), 2) + ':' +
      leadingZeros(d.getMinutes(), 2) + ":00";
    console.log('now is', stamp, ' ...');
    
    return stamp;
}

function leadingZeros(n, digits) {
   var zero = '';
   n = n.toString();

    if (n.length < digits) {
      for (i = 0; i < digits - n.length; i++)
        zero += '0';
    }
    return zero + n;
}

