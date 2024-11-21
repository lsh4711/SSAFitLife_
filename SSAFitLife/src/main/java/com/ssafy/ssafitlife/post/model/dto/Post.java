package com.ssafy.ssafitlife.post.model.dto;


import com.ssafy.ssafitlife.user.model.dto.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
	private int postNo; // 게시글번호

	private String postTitle; // 게시글제목
	private String postContent; // 게시글내용
	private String PostCreatedDate; // 작성일
	private String PostUpdatedDate; // 수정일
	private int postViews; // 조회수
	private Integer memNo; // 작성자번호
}