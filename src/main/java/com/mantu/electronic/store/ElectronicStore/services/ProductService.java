package com.mantu.electronic.store.ElectronicStore.services;

import com.mantu.electronic.store.ElectronicStore.dtos.PageableResponse;
import com.mantu.electronic.store.ElectronicStore.dtos.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto create(ProductDto productDto);
    ProductDto update(ProductDto productDto,String productId);
    void delete(String productId);
     ProductDto get(String productId);
    PageableResponse<ProductDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir);
    PageableResponse<ProductDto>getAllLive(int pageNumber,int pageSize,String sortBy,String sortDir);
    PageableResponse<ProductDto>searchByTittle(String subTittle,int pageNumber,int pageSize,String sortBy,String sortDir);
    //create product with category
    ProductDto createWithCategory(ProductDto productDto,String categoryId);
    //update category of product
    ProductDto updateCategory(String productId,String categoryId);
    PageableResponse<ProductDto>getAllofCategory(String categoryId,int pageNumber,int pageSize,String sortBy,String sortDir);



}
