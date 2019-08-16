package com.my.test.freeboard.dao;

import java.util.List;

import com.my.test.freeboard.dto.FreeboardCommentDto;
import com.my.test.freeboard.dto.FreeboardDto;

public interface FreeboardCommentDao {
	
	String namespace="freeboard_comment.";
	
	public List<FreeboardCommentDto> freeboard_comment_list(int freeboard_num);
	public FreeboardCommentDto freeboard_comment_detail(String id);
	public int freeboard_comment_insert(FreeboardCommentDto dto);
	public int freeboard_comment_update(FreeboardCommentDto dto);
	public int freeboard_comment_delete(int Comment_num);
}