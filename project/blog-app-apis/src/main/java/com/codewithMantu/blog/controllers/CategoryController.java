package com.codewithMantu.blog.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.codewithMantu.blog.exceptions.ApiResponse;
import com.codewithMantu.blog.payloads.CategoryDto;
import com.codewithMantu.blog.services.CategoryService;
@RequestMapping("/api/categories")
@RestController
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	//create
	@PostMapping("/")
	public ResponseEntity<CategoryDto>createCategory(@Valid @RequestBody CategoryDto categoryDto) {
		 CategoryDto categoryDtos = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(categoryDtos,HttpStatus.CREATED);
	}
	//update
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto>updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable("catId") Integer categotyId) {
		
		 CategoryDto categoryDtos = this.categoryService.updateCategory(categoryDto, categotyId);
		return new ResponseEntity<CategoryDto>(categoryDtos,HttpStatus.OK);
	}
	
	//findAll
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>>getAllCategories() {
		
		return ResponseEntity.ok(this.categoryService.getCategories());
		
	} 
	
		
	//findbyid
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto>getCategoryById(@Valid @PathVariable("categotyId") Integer categoryId ){
		CategoryDto categoryDto = this.categoryService.getCategory(categoryId);
		
		return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
		
	}
	
	//delete
	@DeleteMapping("/{categoryId}")
 public ResponseEntity<ApiResponse>deleteCategory(@Valid @PathVariable("categoryId")Integer categoryId){
		this.categoryService.deleteCategory(categoryId);
	return new ResponseEntity<ApiResponse>(new ApiResponse("deleted succesful", true),HttpStatus.OK);
	 //public ResponseEntity<?>deleteCategory(@PathVariable("categoryId")Integer categoryId){
	//return new ResponseEntity(Map.of("message","Category deleted success"),HttpStatus.OK);
	 
 }
}
