var app = require('express')();
var http = require('http').createServer(app);
var https = require('https');
var io = require('socket.io').listen(http);
var fs = require('fs');

app.get('/', (req, res) => {
    res.sendFile(__dirname + '/chatroom.html');
});

io.sockets.on('connection', (socket) => {
    var socketId = socket.id;
    var clientIp = socket.request.connection.remoteAddress;    //접속한 클라이트의 ip 추적  
    console.log('New Connection from '+ clientIp);
    
    socket.on('chat message', (msg) => {
        //소켓을 통해 이벤트 전송
        io.emit('data', {"chatMessage" : msg, "clientIp" : clientIp });
        console.log('['+clientIp+'] sent >>' + msg);
    });
});

http.listen(3000, function(){
    console.log('listening on port : 3000');
});