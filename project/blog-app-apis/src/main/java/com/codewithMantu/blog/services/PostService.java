package com.codewithMantu.blog.services;

import java.util.List;

import com.codewithMantu.blog.entities.Post;
import com.codewithMantu.blog.payloads.PostDto;
import com.codewithMantu.blog.payloads.PostResponse;

public interface PostService {
	//create
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	//update
	PostDto updatePost(PostDto postDot,Integer postId);
	
	//getallPost
	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	
	//getbyId
	PostDto getPostById(Integer postId);
	
	//delete
   void deletePost(Integer postId);
   
  List<PostDto>getPostsByUser(Integer userId);
  
  List<PostDto>getPostsbyCategory(Integer categoryId);
  
  List<PostDto> searchPosts(String keyword);


}
