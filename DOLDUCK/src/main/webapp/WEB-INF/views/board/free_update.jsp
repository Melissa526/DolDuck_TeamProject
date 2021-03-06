<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All About K-POP! DolDuck - 자유게시판(수정)</title>
</head>
<!-- include libraries(jQuery, bootstrap) -->
<link href="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
<!-- include summernote css/js-->
<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.js"></script>
<script>
(function($){
	$(document).ready(function() {
		$('#summernote').summernote({
		    	placeholder: 'content',
		        minHeight: 370,
		        maxHeight: null,
		        focus: true, 
		        lang : 'ko-KR'
		  });
		});
	
})(jQuery);
</script>
<body>
<%@ include file="../header.jsp" %>

	<div class="container freeboard">
	<div class="center">
	<h1 class="text-center">수정 하기</h1>

	<form action="free_updateform.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="freeboard_num" value="${one.freeboard_num }">
		<table class="table">
			<tr align="right">
				<td colspan="4" style="border-top: none; ">
					<button type="button" onclick="location.href='free_list.do'">취소</button>
					<button type="submit">작성</button>
				</td>
			</tr>
			<tr>
				<th>글쓴이</th>
				<td>${one. freeboard_id }</td>
				<th>작성일</th>
				<td>${one.freeboard_regdate }</td>
			</tr>
			<tr>
				<th>제목</th>
				<td colspan="3"><input type="text" name="freeboard_title" value="${one.freeboard_title }" style="width: 100%" ></td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3"><textarea id="summernote"rows="10" cols="60" name="freeboard_content">${one.freeboard_content }</textarea>
						<input type="file" name="file">
				</td>

			</tr> 

		</table>
	</form>
	</div>
	</div>
</body>
</html>