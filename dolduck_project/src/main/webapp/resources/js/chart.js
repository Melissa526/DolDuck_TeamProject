$(window).on('load', function(){

	var chart,
	start = 0, end = 30
			
	//GET MUSIC CHART 100 FROM MELON USING AJAX
	chart = getMusicChart()
	//DEFAULT LIST 1~30
	paginationOfList(chart, start, end)

	$(document).on('click','.button-pagination', function(){
		//If rank is over 100, end become 100 and 'button-pagination' become disabled
		if(end > 100){	
			end = 100
			$('.button-pagination').attr('disabled', 'disabled');
		}
		start = end
		end += 20
		paginationOfList(chart, start, end)	
	})	
})

function getMusicChart(){
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	var list;
	
	$.ajax({
		type : 'POST',
		url : 'musicsearch.do',
		dataType : 'json',
		async : false ,
		beforeSend: function( xhr ) {
			xhr.setRequestHeader(header, token);
		},
		success : function(data){
			$('.label-search').text(data.getTime);
			list = data.chart;
		},
		error : function(){
			console.log('ajax 통신에러')
			alert('네트워크 오류! 새로고침 후 다시 이용해주세요!')
		}
	});
	
	return list
}

function paginationOfList(chart, start, end){
	for(var i=start ; i<end ; i++){
		var song = chart[i]
		$('tbody').append(`<tr>
				  <td scope="row">${song.rank}</td>
				  <td><img class="chart-thumbnail" src="${song.thumb}"></td>
				  <td><a href="#">${song.title}</td>
				  <td>${song.singer}</td>
				  <td>${song.album}</td>
			      </tr>`
		);
	}
}

