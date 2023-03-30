package com.codewithMantu.blog.services.Impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.codewithMantu.blog.entities.Category;
import com.codewithMantu.blog.entities.Post;
import com.codewithMantu.blog.entities.User;
import com.codewithMantu.blog.exceptions.ResourceNotFoundException;
import com.codewithMantu.blog.payloads.CategoryDto;
import com.codewithMantu.blog.payloads.PostDto;
import com.codewithMantu.blog.payloads.PostResponse;
import com.codewithMantu.blog.payloads.UserDto;
import com.codewithMantu.blog.repositories.CategoryRepo;
import com.codewithMantu.blog.repositories.PostRepo;
import com.codewithMantu.blog.repositories.UserRepo;
import com.codewithMantu.blog.services.PostService;

@Service
public class PostServiceImpl  implements PostService{
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	 private ModelMapper modelMapper;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private UserRepo userRepo;

	
	
	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		
		User user=this.userRepo.findById(userId)
				.orElseThrow(()->new com.codewithMantu.blog.exceptions.ResourceNotFoundException("User", "User Id", userId));
				
	Category category	=this.categoryRepo.findById(categoryId)
			.orElseThrow(()->new com.codewithMantu.blog.exceptions.ResourceNotFoundException("Category", "Category Id", categoryId));
			
		
		
//		Post post = this.modelMapper.map(postDto, Post.class);
//		post.setImageName("default.png");
//		post.setAddedDate(new Date());
	   Post post = this.modelMapper.map(postDto, Post.class);
	   post.setImageName("default.png");
	   post.setAddedDate(new Date());
	   post.setCategory(category);
	   post.setUser(user);
	   Post newpost = this.postRepo.save(post);
		
		return this.modelMapper.map(newpost, PostDto.class);
	}
	
	
	@Override
	public PostDto updatePost(PostDto postDot, Integer postId) {
	Post posts = this.postRepo.findById(postId)
			.orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", postId));
	posts.setTitle(posts.getTitle());
	posts.setContent(posts.getContent());
	posts.setImageName(posts.getImageName());
	
	Post updatedpost=this.postRepo.save(posts);
		return this.modelMapper.map(updatedpost,PostDto.class);
	}
	
	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
		
		Sort sort=(sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
//		Sort sort=null;
//		if(sortDir.equalsIgnoreCase("asc")){
//			sort=Sort.by(sortBy).ascending();
//			
//		}else {
//			sort=Sort.by(sortBy).descending();
//			
//		}
		Pageable p=PageRequest.of(pageNumber,pageSize,sort);
		   Page<Post> pagePost = this.postRepo.findAll(p);

		List<Post> Allpost=pagePost.getContent();
		List<PostDto> postDtos=Allpost.stream().map((post)->  this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		
		PostResponse postResponse=new PostResponse();
		
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotolElements(pagePost.getTotalElements());
		postResponse.setTotalpages(pagePost.getTotalPages());
		postResponse.setLastpage(pagePost.isLast());
		
		return postResponse; 
	}
	@Override
	public PostDto getPostById(Integer postId) {
		Post post=this.postRepo.findById(postId)
		.orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", postId));
		return this.modelMapper.map(post, PostDto.class);
	}
	@Override
	public void deletePost(Integer postId) {
		Post  findById = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", postId));
		this.modelMapper.map(findById, PostDto.class);
	}
	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User", "user Id", userId));
		
		List<Post> posts=this.postRepo.findByUser(user);
		List<PostDto> postDtos=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postDtos;
	}
	@Override
	public List<PostDto> getPostsbyCategory(Integer categoryId){
	Category cat	=this.categoryRepo.findById(categoryId)
			.orElseThrow(()-> new ResourceNotFoundException("Category", "category Id", categoryId));
	List<Post> posts=this.postRepo.findByCategory(cat);
	List<PostDto> postDtos=posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
			.collect(Collectors.toList());
	return postDtos;
		
	}
	@Override
	public List<PostDto> searchPosts(String keyword) {
		
		
		List<Post> posts=this.postRepo.findByTitleContaining(keyword);
		List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

}