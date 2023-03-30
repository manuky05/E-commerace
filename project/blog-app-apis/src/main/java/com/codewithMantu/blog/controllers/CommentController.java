package com.codewithMantu.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codewithMantu.blog.exceptions.ApiResponse;
import com.codewithMantu.blog.payloads.CommentDto;
import com.codewithMantu.blog.services.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {
	@Autowired
	private CommentService  commentService;
	
	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDto> creatreComment(@RequestBody CommentDto commentDto,@PathVariable Integer postId){
		CommentDto createComment = this.commentService.createComment(commentDto, postId);
		return new ResponseEntity<CommentDto>(createComment,HttpStatus.CREATED);
		
	}
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ApiResponse> DeleteComment(@PathVariable Integer commentId){
		 this.commentService.deleteComment(commentId);
		return new ResponseEntity<>(new ApiResponse("comment successfully deleted!!",true),HttpStatus.OK);
	}
}
