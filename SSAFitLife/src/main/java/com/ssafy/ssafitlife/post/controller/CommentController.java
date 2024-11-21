package com.ssafy.ssafitlife.post.controller;

import com.ssafy.ssafitlife.post.model.dto.Comment;
import com.ssafy.ssafitlife.post.model.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-comment")
@CrossOrigin(origins = "http://localhost:5173")
public class CommentController {
	// 서비스 의존성 주입
	private final CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	// 댓글 전체조회
	@GetMapping("/comment")
	public ResponseEntity<Object> list() {
		try {
			List<Comment> list = commentService.getCommentList();
			if   (!list.isEmpty())
				return ResponseEntity.status(HttpStatus.OK).body(list);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("조회할 댓글이 없습니다.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류로 댓글 불러오기에 실패했습니다.");
		}
	}

	// N번 게시글 M번 댓글 대댓글 조회
	@GetMapping("/comment/{memNo}/{postNo}/{commentParentNo}")
	public ResponseEntity<Object> listPostNumberCommentNumber(@PathVariable("postNo") int postNo, @PathVariable("commentParentNo") int commentParentNo) {
		try {
			List<Comment> list = commentService.getCommentListPNCN(postNo,commentParentNo);
			if (!list.isEmpty())
				return ResponseEntity.status(HttpStatus.OK).body(list);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("조회할 댓글이 없습니다.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류로 댓글 불러오기에 실패했습니다.");
		}
	}
	
	
	// N번 게시글 댓글 조회
	@GetMapping("/comment/{postNo}")
	public ResponseEntity<Object> listPostNumber(@PathVariable("postNo") int postNo) {
		try {
			List<Comment> list = commentService.getCommentListPN(postNo);
			if (!list.isEmpty())
				return ResponseEntity.status(HttpStatus.OK).body(list);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("조회할 댓글이 없습니다.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류로 댓글 불러오기에 실패했습니다.");
		}
	}
	
	// 검색
//	@GetMapping("/comment")
//	public ResponseEntity<?> list(@ModelAttribute SearchCondition condition){
//		System.out.println(condition);
//		List<Comment> list = commentService.search(condition);
//		if(list == null || list.size() == 0) {
//			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//		}
//		return new ResponseEntity<List<Comment>>(list, HttpStatus.OK);
//	}

	// 댓글 상세 보기
//	@GetMapping("/comment/{commentNo}")
//	public ResponseEntity<?> detail(@PathVariable("commentNo") int commentNo) {
//		Comment comment = commentService.readComment(commentNo);
//		if (comment != null)
//			return ResponseEntity.status(HttpStatus.OK).body(comment);
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("댓글이 삭제되었거나 존재하지 않습니다");
//	}

	// 댓글 등록
	@PostMapping("/comment/{memNo}/{postNo}/{commentParentNo}")
	public ResponseEntity<String> write(@PathVariable("memNo") int memNo , @PathVariable("postNo") int postNo, @PathVariable("commentParentNo") int commentParentNo , @RequestBody Comment comment) {
		
		comment.setMemNo(memNo);
		comment.setPostNo(postNo);
		comment.setCommentParentNo(commentParentNo);
		
		if (commentService.writeComment(comment))
			return ResponseEntity.status(HttpStatus.CREATED).body("댓글이 등록되었습니다.");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("댓글 등록에 실패했습니다.");
	}

	// 댓글 삭제
	@DeleteMapping("/comment/{commentNo}")
	public ResponseEntity<String> delete(@PathVariable("commentNo") int commentNo) {
		boolean isDeleted = commentService.removeComment(commentNo);

		if (isDeleted)
			return ResponseEntity.status(HttpStatus.OK).body("댓글이 삭제되었습니다.");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("댓글 삭제에 실패했습니다.");
	}

	// 댓글 수정
//	@PutMapping("/comment")
	@PutMapping("/comment/{commentNo}")
	public ResponseEntity<String> update(@PathVariable("commentNo") int commentNo, @RequestBody Comment comment) {

		comment.setCommentNo(commentNo);

		if (commentService.modifyComment(comment))
			return ResponseEntity.status(HttpStatus.OK).body("댓글이 수정되었습니다.");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("댓글 수정에 실패했습니다.");
	}
}
