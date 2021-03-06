package com.my.dolduck.model.dto;

import java.sql.Date;

public class FreeboardCommentDto {

	private int freeboard_num;
	private int freeboard_comment_num;
	private int freeboard_groupseq;
	private int freeboard_groupno;
	private int freeboard_titletap;
	private String freeboard_comment_content;
	private Date freeboard_comment_regdate;
	private String freeboard_id;

	public FreeboardCommentDto() {

	}

	public FreeboardCommentDto(int freeboard_num, int freeboard_comment_num,int freeboard_groupseq,
			int freeboard_groupno, int freeboard_titletap,String freeboard_comment_content,
			Date freeboard_commnet_regdate, String freeboard_id) {
		this.freeboard_num = freeboard_num;
		this.freeboard_comment_num = freeboard_comment_num;
		this.freeboard_groupseq = freeboard_groupseq;
		this.freeboard_groupno = freeboard_groupno;
		this.freeboard_titletap = freeboard_titletap;
		this.freeboard_comment_content = freeboard_comment_content;
		this.freeboard_comment_regdate = freeboard_commnet_regdate;
		this.freeboard_id = freeboard_id;
	}

	public int getFreeboard_num() {
		return freeboard_num;
	}

	public void setFreeboard_num(int freeboard_num) {
		this.freeboard_num = freeboard_num;
	}

	public int getFreeboard_comment_num() {
		return freeboard_comment_num;
	}

	public void setFreeboard_comment_num(int freeboard_comment_num) {
		this.freeboard_comment_num = freeboard_comment_num;
	}

	public String getFreeboard_comment_content() {
		return freeboard_comment_content;
	}

	public void setFreeboard_comment_content(String freeboard_comment_content) {
		this.freeboard_comment_content = freeboard_comment_content;
	}

	public Date getFreeboard_comment_regdate() {
		return freeboard_comment_regdate;
	}

	public void setFreeboard_comment_regdate(Date freeboard_comment_regdate) {
		this.freeboard_comment_regdate = freeboard_comment_regdate;
	}

	public String getFreeboard_id() {
		return freeboard_id;
	}

	public void setFreeboard_id(String freeboard_id) {
		this.freeboard_id = freeboard_id;
	}

	public int getFreeboard_groupseq() {
		return freeboard_groupseq;
	}

	public void setFreeboard_groupseq(int freeboard_groupseq) {
		this.freeboard_groupseq = freeboard_groupseq;
	}

	public int getFreeboard_groupno() {
		return freeboard_groupno;
	}

	public void setFreeboard_groupno(int freeboard_groupno) {
		this.freeboard_groupno = freeboard_groupno;
	}

	public int getFreeboard_titletap() {
		return freeboard_titletap;
	}

	public void setFreeboard_titletap(int freeboard_titletap) {
		this.freeboard_titletap = freeboard_titletap;
	}
}
