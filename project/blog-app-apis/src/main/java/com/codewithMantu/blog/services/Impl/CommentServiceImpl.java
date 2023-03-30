package com.codewithMantu.blog.services.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithMantu.blog.entities.Comment;
import com.codewithMantu.blog.entities.Post;
import com.codewithMantu.blog.exceptions.ResourceNotFoundException;
import com.codewithMantu.blog.payloads.CommentDto;
import com.codewithMantu.blog.repositories.CommentRepo;
import com.codewithMantu.blog.repositories.PostRepo;
import com.codewithMantu.blog.services.CommentService;
@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private CommentRepo  commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post Id", postId));
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		Comment save = this.commentRepo.save(comment);
		
		return this.modelMapper.map(save, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment=this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "comment Id", commentId));
		this.commentRepo.delete(comment);
	}

}
