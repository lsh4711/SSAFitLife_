package com.ssafy.ssafitlife.post.model.service;


import com.ssafy.ssafitlife.post.model.dto.Comment;

import java.util.List;

public interface CommentService {
	// 댓글 전체 조회
	List<Comment> getCommentList();

	// N번 게시글 M번 댓글 대댓글 조회
	List<Comment> getCommentListPNCN(int postNo, int commentParentNo);

	// N번 게시글 댓글 조회
	List<Comment> getCommentListPN(int postNo);

	// 댓글 상세조회 (클릭시 읽는거)
//	Board readBoard(int postNo);

	// 댓글 작성
	boolean writeComment(Comment comment);

	// 댓글 삭제
	boolean removeComment(int postNo);

	// 댓글 수정
	boolean modifyComment(Comment comment);



	// 검색버튼을 눌러을때 처리할 메서드
//	List<Comment> search(SearchCondition condition);
}
