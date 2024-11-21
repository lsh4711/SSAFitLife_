package com.ssafy.ssafitlife.post.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
	private int commentNo;
	private String commentContent;
	private LocalDateTime commentCreatedDate;
	private int commentParentNo; // case 댓글 => 0 | case 대댓글 => (int)부모댓글번호
	private Integer memNo;
	private Integer postNo;
}