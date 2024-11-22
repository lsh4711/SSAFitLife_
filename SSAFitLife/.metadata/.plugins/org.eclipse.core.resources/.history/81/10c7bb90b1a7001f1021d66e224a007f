package com.ssafy.ssafitlife.post.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafitlife.post.model.dto.Post;
import com.ssafy.ssafitlife.post.model.service.PostService;

@RestController // @Controller + @ResponseBody
@RequestMapping("/api-post")
public class PostController {
	// 서비스 의존성 주입
	private final PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}

	// 게시글 전체조회
	
	@GetMapping("/post")
	public ResponseEntity<Object> list() {
	    try {
	        List<Post> list = postService.getPostList();
	        if (!list.isEmpty())
	            return ResponseEntity.status(HttpStatus.OK).body(list);
	        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("조회할 게시글이 없습니다.");
	    } catch (Exception e) {
	        e.printStackTrace();  // 에러 발생 시 스택 트레이스를 출력
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류로 인해 게시글 불러오기에 실패했습니다.");
	    }
	}
	// 검색
//	@GetMapping("/post")
//	public ResponseEntity<?> list(@ModelAttribute SearchCondition condition){
//		System.out.println(condition);
//		List<post> list = postService.search(condition);
//		
//		
//		if(list == null || list.size() == 0) {
//			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//		}
//		return new ResponseEntity<List<post>>(list, HttpStatus.OK);
//	}

	// 게시글 상세보기
	@GetMapping("/post/{postNo}")
	public ResponseEntity<Object> detail(@PathVariable("postNo") int postNo) {
		
		try {
			Post post = postService.readPost(postNo);
			if (post != null)
				return ResponseEntity.status(HttpStatus.OK).body(post);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("게시글이 삭제되었거나 존재하지 않습니다");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류로 인해 게시글 불러오기에 실패했습니다.");
		}
	}

	// 게시글 등록
	@PostMapping("/post/{memNo}")
	public ResponseEntity<String> write(@PathVariable("memNo") int memNo, @RequestBody Post post) {
		post.setMemNo(memNo);
		
		if (postService.writePost(post))
			return ResponseEntity.status(HttpStatus.CREATED).body("게시글이 등록되었습니다.");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("게시글 등록에 실패했습니다.");
	}

	// 게시글 삭제
	@DeleteMapping("/post/{postNo}")
	public ResponseEntity<String> delete(@PathVariable("postNo") int postNo) {
		boolean isDeleted = postService.removePost(postNo);

		if (isDeleted)
			return ResponseEntity.status(HttpStatus.OK).body("게시글이 삭제되었습니다.");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("게시글 삭제에 실패했습니다.");
	}

	// 게시글 수정
//	@PutMapping("/post")
	@PutMapping("/post/{postNo}")
	public ResponseEntity<String> update(@PathVariable("postNo") int postNo, @RequestBody Post post) {

		post.setPostNo(postNo);
		if (postService.modifyPost(post))
			return ResponseEntity.status(HttpStatus.OK).body("게시글이 수정되었습니다.");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("게시글 수정에 실패했습니다.");
	}
}
