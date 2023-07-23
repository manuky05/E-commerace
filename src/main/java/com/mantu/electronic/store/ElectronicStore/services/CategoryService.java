package com.mantu.electronic.store.ElectronicStore.services;

import com.mantu.electronic.store.ElectronicStore.dtos.CategoryDto;
import com.mantu.electronic.store.ElectronicStore.dtos.PageableResponse;
import com.mantu.electronic.store.ElectronicStore.dtos.ProductDto;
import com.mantu.electronic.store.ElectronicStore.entities.Category;
import org.springframework.stereotype.Service;


@Service
public interface CategoryService {
    //create
    CategoryDto createCategory(CategoryDto categoryDto);
    //update
    CategoryDto updateCategory(CategoryDto categoryDto,String categoryId);
    //delete
    void Delete(String categoryId);
    //get single
    CategoryDto get(String categoryId);
    //get all
    PageableResponse<CategoryDto> getAllCategory(int pageNumber, int pageSize, String sortBy, String sortDir);






}
