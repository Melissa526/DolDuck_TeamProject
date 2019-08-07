const express = require('express')
const https = require('https')
const socketIO = require('socket.io')
const fs = require('fs')

var app = express()

const options = {
    key: fs.readFileSync('./key/server.key'),
    cert: fs.readFileSync('./key/server.cert')
}

app.use('/css', express.static('./css'))
app.use('/js', express.static('./js'))

app.get('/', (req, res) => {
    res.sendFile(__dirname + '/chatroom.html');
});

const server = https.createServer(options, app)

const io = socketIO(server)

io.sockets.on('connection', (socket) => {

    var room

    socket.on('joinRoom', function (name, _room) {

        room = _room

        socket.name = name
        socket.join(room)
        console.log(`user [${socket.name}] joined ${room}`);

        io.sockets.to(room).emit('joinedRoom', {
            room: room,
            name: socket.name
        })
    })

   /*  socket.on('joinRoom', (name, num) => {
        socket.join(room, () => {
            console.log(`${name} join a  room (${room})`);
            io.sockets.to(room).emit('joinRoom', {
                room : num,
                name : name
            })            
        })
    })
    */

    socket.on('leaveRoom', (name, num) => {
        socket.leave(num, () => {
            console.log(`${name} leave a room (${num})`);
            io.to(num).emit('leaveRoom', name, num)
        }) 
     })
 
    socket.on('chat message', (msg) => {
        //소켓을 통해 이벤트 전송
        socket.broadcast.to(room).emit('message', { type: 'msg', chatMessage: msg })
    });

    socket.on('disconnect', (name) => {
          console.log(`user [${socket.name}] disconnected`);
    });
});

server.listen(3000, function () {
    console.log('listening on port : 3000');
});