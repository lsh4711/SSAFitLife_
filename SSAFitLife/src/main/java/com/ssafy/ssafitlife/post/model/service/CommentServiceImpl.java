package com.ssafy.ssafitlife.post.model.service;

import com.ssafy.ssafitlife.post.model.dao.CommentDao;
import com.ssafy.ssafitlife.post.model.dto.Comment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

	private final CommentDao commentDao;
	
	public CommentServiceImpl(CommentDao commentDao) {
		this.commentDao = commentDao;
	}
	
	// 댓글 전체조회
	@Override
	public List<Comment> getCommentList() {
		return commentDao.selectAll();
	}
	// N번 게시글 M번 댓글 대댓글 조회
	@Override
	public List<Comment> getCommentListPNCN(int postNo, int commentParentNo) {
			
		return commentDao.selectAllPNCN(postNo,commentParentNo);
	}
	
	@Override
	public List<Comment> getCommentListPN(int postNo) {
		return commentDao.selectAllPN(postNo);
	}
	
//	@Override
//	public Comment readComment(int postNo) {
//		commentDao.updateViews(postNo);
//		return commentDao.selectOne(postNo);
//	}

	// 댓글 등록
	@Transactional
	@Override
	public boolean writeComment(Comment comment) {
		return commentDao.insertComment(comment)==1;
	}
	
	// 댓글 삭제
	@Transactional
	@Override
	public boolean removeComment(int postNo) {
		return commentDao.deleteComment(postNo) == 1;
	}
	
	// 댓글 수정
	@Transactional
	@Override
	public boolean modifyComment(Comment comment) {
		return commentDao.updateComment(comment)==1;
	}



//	@Override
//	public List<Comment> search(SearchCondition condition) {
//		return commentDao.search(condition);
//	}

}
