package com.mantu.electronic.store.ElectronicStore.services;

import com.mantu.electronic.store.ElectronicStore.dtos.CategoryDto;
import com.mantu.electronic.store.ElectronicStore.dtos.PageableResponse;
import com.mantu.electronic.store.ElectronicStore.entities.Category;
import com.mantu.electronic.store.ElectronicStore.repositories.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ActiveProfiles("test")
@SpringBootTest

public class CategoryServiceTest {
    @MockBean
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired

    private ModelMapper modelMapper;
    Category category;
    @BeforeEach
    public void init(){
        String CategoryId="categoryIdabc";
      category= Category.builder().tittle("Mobile").description("chiepest price of mobile ").coverImage("mobile.png").build();



    }
    @Test
    public void createCategoryTest(){

        Mockito.when(categoryRepository.save(Mockito.any())).thenReturn(category);
        CategoryDto category1 = categoryService.createCategory(modelMapper.map(category, CategoryDto.class));
        System.out.println(category.getTittle());
        Assertions.assertNotNull(category1);

       Assertions.assertEquals("Mobile",category1.getTittle());

    }
    @Test

    public void updateCategoryTest(){
        String categoryId="categoryIdabc";
       CategoryDto categoryDto =CategoryDto.builder().tittle("headPhone").description("best headphone").coverImage("Mobile.png").build();
       Mockito.when(categoryRepository.findById(Mockito.anyString())).thenReturn(Optional.of(category));
       Mockito.when(categoryRepository.save(Mockito.any())).thenReturn(category);
       CategoryDto categoryDto1 = categoryService.updateCategory(categoryDto, categoryId);
       System.out.println(categoryDto1.getTittle());
       Assertions.assertNotNull(category,"categoryDto1");
       Assertions.assertEquals(categoryDto1.getTittle(),category.getTittle(),"category not match !!");

    }
    @Test
    public void DeleteTest(){
        String categoryId="categoryIdabc";
        CategoryDto categoryDto =CategoryDto.builder().tittle("Mobile").description("chiepest price of mobile ").coverImage("mobile.png").build();
        Mockito.when(categoryRepository.findById("categoryIdabc")).thenReturn(Optional.of(category));
        categoryService.Delete(categoryId);
        Assertions.assertNotNull(category);



    }
    @Test
    public void getAllCategoryTest(){


        Category category1= Category.builder().tittle("Mobile1").description("chiepest price of mobile ").coverImage("mobile.png").build();
        Category category2= Category.builder().tittle("Mobile2").description("chiepest price of mobile ").coverImage("mobile.png").build();
        Category category3= Category.builder().tittle("Mobile3").description("chiepest price of mobile ").coverImage("mobile.png").build();
        List<Category> categoryList= Arrays.asList(category,category1,category2,category3);
        Page<Category> page= new PageImpl<>(categoryList);

        Mockito.when(categoryRepository.findAll((Pageable) Mockito.any())).thenReturn(page);

        PageableResponse<CategoryDto> allCategory = categoryService.getAllCategory(1, 2, "name", "asc");
        Assertions.assertEquals(4,allCategory.getContent().size());
    }
    @Test
   public void getTest(){
        String categoryId="categoryIdabc";
        CategoryDto categoryDto=CategoryDto.builder().tittle("Mobile").description("chiepest price of mobile ").coverImage("mobile.png").build();
        Mockito.when(categoryRepository.findById(Mockito.any())).thenReturn(Optional.of(category));
        categoryService.get(categoryId);
        Assertions.assertEquals(categoryDto.getTittle(),category.getTittle());

    }

}
