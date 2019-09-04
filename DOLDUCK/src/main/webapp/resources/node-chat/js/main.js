$(function () {

    //script의 attr 가져오기
    var name = $('#userInfo').attr("name")
    var room = parseInt(Math.random()*9999999999)
   /*  var room = $('#userInfo').attr("room") */

    var socket = io();

    socket.emit('joinRoom', name, room)

    $('form').submit(function (e) {
        e.preventDefault();
        var msg = $('#m').val().trim();
        if (msg !== "" && msg !== null) {
            socket.emit('chat message', msg);
            $('#messages').append($('<li class="client">').append($('<p>').text(msg)))
            $('#m').val('');
        }
        return false;
    });

    socket.on('message', function (data) {
        if (data.type === 'msg') {
            $('#messages').append($('<li class="admin">').append($('<p>').text(data.chatMessage)))
        }
    });

    socket.on('joinedRoom', function (data) {
        $('#messages').append($('<li class="server">').text(`고객센터 QnA에 입장하셨습니다.`));
    })

})