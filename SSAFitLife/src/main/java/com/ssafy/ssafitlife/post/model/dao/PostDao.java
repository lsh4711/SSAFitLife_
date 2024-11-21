package com.ssafy.ssafitlife.post.model.dao;

import com.ssafy.ssafitlife.post.model.dto.Post;
import com.ssafy.ssafitlife.post.model.dto.SearchCondition;

import java.util.List;

public interface PostDao {
	// 전체 게시글을 조회
	List<Post> selectAll();

	// ID에 해당하는 게시글 하나 가져오기
	Post selectOne(int postNo);

	// 게시글 등록
	int insertPost(Post post);

	// 게시글에 등록된 댓글 삭제
	int deleteComment(int postNo);
	
	// 게시글 삭제
	int deletePost(int postNo);

	// 게시글 수정
	int updatePost(Post post);

	// 조회수 증가
	void updateViews(int postNo);

	// 검색 기능
	List<Post> search(SearchCondition condition);

	// 파일 정보 저장
	void insertFile(Post Post);

}
