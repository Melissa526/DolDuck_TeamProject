$(document).on('click', '#quickframe-close-btn', function(){
		$('.quick-menu-frame').hide()
	})
  $(document).on('click', ".icon-chat-circle", function(){
				var type = $(this).siblings('input[name=quick-menu]').val()
				
				switch(type){
				case 'onair':
					location.href = 'live-onair.do'
					break;
				case 'translate':
<<<<<<< HEAD
					$('#quick-iframe').attr('src','https://localhost:8787/dolduck/translate')
=======
					$('#quick-iframe').attr('src','https://192.168.10.169:3000/translate')
>>>>>>> 67adb92d0029ba25c27ce410ab2d81778b9aaef6
					//'https://192.168.10.169:3000/translate-form'
					$('.quick-menu-frame').show()
					break;
				case 'twits':
					$('#quick-iframe').attr('src','https://localhost:3000/twits')
					$('.quick-menu-frame').show()
					break;
				case 'chat':
<<<<<<< HEAD
					$('#quick-iframe').attr('src','https://localhost:3000/')
=======
					$('#quick-iframe').attr('src','https://192.168.10.169:3000/')
>>>>>>> 67adb92d0029ba25c27ce410ab2d81778b9aaef6
					$('.quick-menu-frame').show()
					break;
				}
		})