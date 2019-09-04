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
					$('#quick-iframe').attr('src','https://localhost:8787/dolduck/translate')
					//'https://192.168.10.169:3000/translate-form'
					$('.quick-menu-frame').show()
					break;
				case 'twits':
					$('#quick-iframe').attr('src','https://localhost:3000/twits')
					$('.quick-menu-frame').show()
					break;
				case 'chat':
					$('#quick-iframe').attr('src','https://localhost:3000/')
					$('.quick-menu-frame').show()
					break;
				}
		})