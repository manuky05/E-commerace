package com.mantu.electronic.store.ElectronicStore.services;

import com.mantu.electronic.store.ElectronicStore.dtos.CategoryDto;
import com.mantu.electronic.store.ElectronicStore.dtos.PageableResponse;
import com.mantu.electronic.store.ElectronicStore.entities.Category;



public interface CategoryService {
    //create
    Category createCategory(CategoryDto categoryDto);
    //update
    CategoryDto update(CategoryDto categoryDto,String categoryId);
    //delete
    void Delete(String categoryId);
    //get all


    PageableResponse<CategoryDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir);

    //single user
    CategoryDto get(String categoryId);



}
