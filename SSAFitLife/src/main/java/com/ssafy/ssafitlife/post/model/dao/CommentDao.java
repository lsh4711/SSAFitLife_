package com.ssafy.ssafitlife.post.model.dao;

import com.ssafy.ssafitlife.post.model.dto.Comment;

import java.util.List;

public interface CommentDao {
	// 전체 댓글을 조회
	List<Comment> selectAll();

	// N번 게시글 M번 댓글 대댓글 조회
	List<Comment> selectAllPNCN(int postNo, int commentParentNo);
	
	// N번 게시글 댓글 조회
	List<Comment> selectAllPN(int postNo);

	// ID에 해당하는 댓글 하나 가져오기
//	Comment selectOne(int commentNo);

	// 댓글 등록
	int insertComment(Comment comment);

	// 댓글 삭제
	int deleteComment(int commentNo);

	// 댓글 수정
	int updateComment(Comment comment);


	

	// 조회수 증가
//	void updateViewCnt(int commentNo);

	// 검색 기능
//	List<Comment> search(SearchCondition condition);

	// 파일 정보 저장
//	void insertFile(Comment comment);

}
