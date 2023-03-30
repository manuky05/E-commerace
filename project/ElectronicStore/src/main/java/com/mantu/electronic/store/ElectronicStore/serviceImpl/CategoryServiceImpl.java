package com.mantu.electronic.store.ElectronicStore.serviceImpl;

import com.mantu.electronic.store.ElectronicStore.dtos.CategoryDto;
import com.mantu.electronic.store.ElectronicStore.dtos.PageableResponse;
import com.mantu.electronic.store.ElectronicStore.entities.Category;
import com.mantu.electronic.store.ElectronicStore.exceptions.ResourceNotFoundException;
import com.mantu.electronic.store.ElectronicStore.helper.Helper;
import com.mantu.electronic.store.ElectronicStore.repositories.CategoryRepository;
import com.mantu.electronic.store.ElectronicStore.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired

    private ModelMapper modelMapper;

    @Override
    public Category createCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        Category saveCategory = categoryRepository.save(category);

        return modelMapper.map(saveCategory,Category.class);
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto, String categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found Exception!!"));
        category.setTittle(categoryDto.getTittle());
        category.setDescription(categoryDto.getDescription());
        category.setCoverImage(categoryDto.getCoverImage());
        Category saveCategory = categoryRepository.save(category);

        return modelMapper.map(saveCategory,CategoryDto.class);
    }

    @Override
    public void Delete(String categoryId) {
      Category category=  categoryRepository.findById(categoryId).orElseThrow (()-> new ResourceNotFoundException("Category  Not Found !!"));
        categoryRepository.delete(category) ;


    }



    @Override
    public PageableResponse<CategoryDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir) {

        Sort sort=(sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending()) ;
        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);
        Page<Category> page = categoryRepository.findAll(pageable);
        PageableResponse<CategoryDto> pageableResponse=Helper.getPageableResponse(page,CategoryDto.class);
        return pageableResponse;
    }

    @Override
    public CategoryDto get(String categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Single Category not found!!"));
        return modelMapper.map(category,CategoryDto.class);
    }
}
