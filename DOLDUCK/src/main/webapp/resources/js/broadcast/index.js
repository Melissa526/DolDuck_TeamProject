/* Server Side */
const express = require('express'),
      https = require('https'),
      socketIO = require('socket.io'),
      fs = require('fs')
var app = express()

var title
var roomNumber
var caster 
var roomArr = []

/*******************
      Server
*******************/

//Allow to use STATIC Files
app.use('/css/', express.static('./css'))
app.use('/js/', express.static('./js'))

//Setting Https server's Options
var options = {
    key : fs.readFileSync('./key/server.key'),
    cert : fs.readFileSync('./key/server.cert')
}

app.get('/', (req, res) => {

    fs.readFile('./index.html', (err, data) =>{
        if(err){
            res.send(err)
        }else{
            res.writeHead(200, { 'Content-Type' : 'text/html'})
            res.write(data)
            res.end()
        }
    })
})

app.get('/IoException', (req, res) => {
    fs.readFile('./exception.html', (err, data) => {
        if(err){
            
        }
    })
})


app.get('/caster/:page', (req, res) => {
    roomNumber = req.params.page;

    fs.readFile(`./caster.html`, (err, data) => {
        if(err){
            res.send(err)
        }else{
            res.writeHead(200, { 'Content-Type' : 'text/html'})
            res.write(data)
            res.end()
        }
    }) 
})

app.get('/user/:page', (req, res) => {

    fs.readFile('./user.html', (err,data) => {
        if(err){
            res.send(err)
        }else{
            res.writeHead(200, { 'Content-Type' : 'text/html'})
            res.write(data)
            res.end()
        }
    })
    
})

/*******************
    Useful func
*******************/

function findCaster(roomNum){
    for(let i=0 ; i<roomArr.length ; i++){
        if(roomArr[i].room == roomNum){
            return roomArr[i].casterid
        }
    }
}

function getNumClients(room) {
    var clientsInRoom = io.sockets.adapter.rooms[room];
    var numClients = clientsInRoom ? Object.keys(clientsInRoom.sockets).length : 0;
    return numClients
}

function deleteRoom(roomNum){
    for(let i=0 ; i<roomArr.length ; i++){
        if(roomArr[i].room == roomNum){
            roomArr.splice(i, 1)
        }
    }
}

/*******************
       Socket
*******************/
const server = https.createServer(options, app)
const io = socketIO(server)



io.sockets.on('connection', (socket) => {

    //caster 접속 (방 생성)
    socket.on('create', (casterName, title, roomNumber) => {
        socket.name = casterName
        caster = socket.id
        console.log(`Caster(socket.id) : ${caster}`)
        console.log(`[Caster Join] "${socket.name}" created room "${title}"(${roomNumber})`)

        var rooms = io.sockets.adapter.rooms
        for(var key in rooms){
            if(key.room != roomNumber){
                socket.join(roomNumber)
                io.sockets.to(roomNumber).emit('createdRoom', roomNumber)
                break;
            }
        }
    })

    socket.on('caster-join', (roomInfo) => {
        //console.log('<<<생성된 방 정보>>>\n', roomInfo);
        roomArr.push(roomInfo)
        console.log(`${roomInfo.caster}가 ${roomInfo.room}을 개설했습니다!`)   
    })
    
    socket.on('user-join', (_room, name) => {
        console.log(`${_room}에 ${name}(${socket.id})님이 들어왔습니다`)
        socket.join(_room)
        io.to(findCaster(_room)).emit('newUserJoined', name, socket.id)
        io.sockets.to(_room).emit('joinedUser', name, socket.id, getNumClients(_room)) 
    })

    socket.on('userMessage', (msg, room) =>{
        var casterid = findCaster(room)
        console.log('casterid : ', casterid);
        io.to(casterid).emit('message', msg, socket.id)
    })
    socket.on('casterMessage', (msg, id) =>{
        io.to(id).emit('message', msg, socket.id)
    })

    //Index.html -> 방 목록 요청 
    socket.on('requestRoomlist', () =>{
        socket.emit('roomlist', roomArr)
    })
    
    //Event on Chat :: 'Message'
    socket.on('chat-message', (_room, name, msg) => {
        io.sockets.to(_room).emit('chat-message', name , msg)
    })

    
  socket.on('CasterLived', (room) => {
        socket.leave(room)
        deleteRoom(room)
    })

    // socket.on('ByeUsers', (room) => {
    //     socket.leave(room)
    // }) 

    // socket.on('disconnect', function () {
    //     for (let i = 0; i < roomArr.length; i++) {
    //         if (roomArr[i].caster === socket.id) {
    //             var room = roomArr[i].room
    //             io.in(room).clients((error, socketIds) => {
    //                 socketIds.forEach(socketId => io.to(socketId).emit('chatMsg', { type: 'leaveCaster', class_num: room }));
    //             });
    //             roomArr.splice(i, 1);
    //             break;
    //         }
    //     }
    // })
})

server.listen(5571, () => { console.log('::: Port listening 5571 :::'); } )

