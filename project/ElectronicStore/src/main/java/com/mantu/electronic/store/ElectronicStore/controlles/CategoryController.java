package com.mantu.electronic.store.ElectronicStore.controlles;

import com.mantu.electronic.store.ElectronicStore.dtos.CategoryDto;
import com.mantu.electronic.store.ElectronicStore.dtos.PageableResponse;
import com.mantu.electronic.store.ElectronicStore.entities.Category;
import com.mantu.electronic.store.ElectronicStore.exceptions.ApiResponseMessage;
import com.mantu.electronic.store.ElectronicStore.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/categories")

public class CategoryController {
    private Logger logger= LoggerFactory.getLogger("CategoryController");
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ModelMapper modelMapper;
    //create
    @GetMapping("/")
    public ResponseEntity<CategoryDto>CreateCategory(CategoryDto categoryDto){
        Category category = this.categoryService.createCategory(categoryDto);
        return  new ResponseEntity<>(categoryDto, HttpStatus.CREATED);


    }
    //updare
    @PutMapping("/{categoryId}")
    public  ResponseEntity<CategoryDto>updateCategory(@Valid @PathVariable("/categoryId") String categoryId, @RequestBody CategoryDto categoryDto){
        CategoryDto updatedCategory = this.categoryService.update(categoryDto, categoryId);
        return new ResponseEntity<>(updatedCategory,HttpStatus.OK);


    }
    //delete
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponseMessage>deleteCategory(@PathVariable String categoryId){
        categoryService.Delete(categoryId);
        ApiResponseMessage responseMessage=ApiResponseMessage.builder().message("Category successfully deleted").success(true)
                .status(HttpStatus.OK).build();
        return  new ResponseEntity<>(responseMessage,HttpStatus.OK);

    }

    //get all
    @GetMapping("/allcategory")
    public ResponseEntity<PageableResponse<CategoryDto>>getAll(
            @RequestParam(value = "pageNumber",defaultValue="1",required = false)int pageNumber,
            @RequestParam(value = "pageSize",defaultValue="10",required = false)int pageSize,
            @RequestParam(value = "sortBy",defaultValue="name",required = false)String  sortBy,
            @RequestParam(value = "sortDir",defaultValue="asc",required = false)String sortDir){
        PageableResponse<CategoryDto> allcategory = this.categoryService.getAll(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(allcategory,HttpStatus.OK);

    }
    //get single
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto>getSingle(@PathVariable String categoryId){
        CategoryDto categoryDto = this.categoryService.get(categoryId);
        return new ResponseEntity<>(categoryDto,HttpStatus.OK);

    }

}
