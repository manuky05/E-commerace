package com.codewithMantu.blog.services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithMantu.blog.entities.Category;
import com.codewithMantu.blog.exceptions.ResourceNotFoundException;
import com.codewithMantu.blog.payloads.CategoryDto;
import com.codewithMantu.blog.repositories.CategoryRepo;
import com.codewithMantu.blog.repositories.UserRepo;
import com.codewithMantu.blog.services.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CategoryRepo caterotyRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category cat = this.modelMapper.map(categoryDto, Category.class);
		Category addedCat=this.categoryRepo.save(cat);
		return this.modelMapper.map(addedCat, CategoryDto.class);
		
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category  updatedcategory= this.categoryRepo.save(cat);
		
		return this.modelMapper.map(updatedcategory, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
	Category cat = this.categoryRepo.findById(categoryId)
			.orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id",categoryId ));
		this.categoryRepo.save(cat);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
	Category cat	=this.categoryRepo.findById(categoryId)
			.orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id",categoryId));
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategories() {
		List<Category> categories = this.categoryRepo.findAll();
		List<CategoryDto> CategoryDtos  =categories.stream().map((cat)-> this.modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
		
		return CategoryDtos;
	}

}
