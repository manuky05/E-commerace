package com.mantu.electronic.store.ElectronicStore.controlles;

import com.mantu.electronic.store.ElectronicStore.dtos.*;
import com.mantu.electronic.store.ElectronicStore.exceptions.ApiResponseMessage;
import com.mantu.electronic.store.ElectronicStore.services.CategoryService;
import com.mantu.electronic.store.ElectronicStore.services.FileService;
import com.mantu.electronic.store.ElectronicStore.services.ProductService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/categories")

public class CategoryController {
    private Logger logger= LoggerFactory.getLogger("CategoryController");
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private FileService fileService;
    @Value("${category.profile.image.path}")
    private String imageUploadPath;



    //create
    @PostMapping("/")
    public ResponseEntity<CategoryDto>CreateCategory( @Valid @RequestBody CategoryDto categoryDto){
        logger.info("Initiating Request for create Category:"+categoryDto);
        CategoryDto categoryDto1 = this.categoryService.createCategory(categoryDto);
        logger.info("Create Category successful:"+categoryDto1);
        return  new ResponseEntity<>(categoryDto1, HttpStatus.CREATED);


    }
    //update
    @PutMapping("/{categoryId}")
    public  ResponseEntity<CategoryDto>updateCategory(@PathVariable String categoryId, @RequestBody CategoryDto categoryDto){
        logger.info("Initiating Request for Update Category:"+categoryDto);
        CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto, categoryId);
        logger.info(" Categorye update successfully:"+updatedCategory);
        return new ResponseEntity<>(updatedCategory,HttpStatus.OK);


    }
    //delete
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponseMessage>deleteCategory( @Valid  @PathVariable String categoryId){
        categoryService.Delete(categoryId);
        ApiResponseMessage responseMessage=ApiResponseMessage.builder().message("Category successfully deleted").success(true)
                .status(HttpStatus.OK).build();
        logger.info(" Category Deleted successfully:"+responseMessage);
        return  new ResponseEntity<>(responseMessage,HttpStatus.OK);

    }

    //get all
    @GetMapping
    public ResponseEntity<PageableResponse<CategoryDto>>getAllCategory(
            @RequestParam(value = "pageNumber",defaultValue="1",required = false)int pageNumber,
            @RequestParam(value = "pageSize",defaultValue="10",required = false)int pageSize,
            @RequestParam(value = "sortBy",defaultValue="tittle",required = false)String  sortBy,
            @RequestParam(value = "sortDir",defaultValue="asc",required = false)String sortDir){
        logger.info("Initiating Request for Get all Category:");
        PageableResponse<CategoryDto> allCategory = this.categoryService.getAllCategory(pageNumber, pageSize, sortBy, sortDir);
        logger.info(" AllCategory get  successful:"+ allCategory);
        return new ResponseEntity<PageableResponse<CategoryDto>>(allCategory,HttpStatus.OK);

    }
    //get single
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto>getSingle(@PathVariable String categoryId){
        logger.info("Initiating Request for Get Single Category:"+categoryId);
        CategoryDto categoryDto = this.categoryService.get(categoryId);
        logger.info(" AllCategory get  successful:"+ categoryDto);
        return new ResponseEntity<>(categoryDto,HttpStatus.OK);

    }

    @PostMapping("/image/{categoryId}")
    public ResponseEntity<ImageResponse> uploadImage(@RequestParam("coverImage")MultipartFile image, @PathVariable String categoryId) throws IOException {
        String imageName = fileService.uploadFile(image, imageUploadPath);
        CategoryDto categoryDto = categoryService.get(categoryId);
        categoryDto.setCoverImage(imageName);
        CategoryDto update = categoryService.updateCategory(categoryDto, categoryId);
        ImageResponse imageResponse = ImageResponse.builder().imageName(imageName).message("Image upload Successful").success(true).status(HttpStatus.CREATED).build();
        return new ResponseEntity<>(imageResponse, HttpStatus.CREATED);
    }

    @GetMapping(value = "/image/{categoryId}")
     public void searchImage(@PathVariable String categoryId,HttpServletResponse response)throws IOException{
    CategoryDto categoryDto = categoryService.get(categoryId);
    InputStream resource = fileService.getResource(imageUploadPath, categoryDto.getCoverImage());
    response.setContentType(MediaType.IMAGE_JPEG_VALUE);
    StreamUtils.copy(resource,response.getOutputStream());
    }

    //create product with category
    @PostMapping("/{categoryId}/products")
    public ResponseEntity<ProductDto>createProductWithCategory(
            @PathVariable("categoryId")String categoryId,
            @RequestBody ProductDto dto
    ){
        ProductDto productwithCategory = productService.createWithCategory(dto, categoryId);
        return new ResponseEntity<>(productwithCategory,HttpStatus.CREATED);
    }
    //update category
    @PutMapping("/{categoryId}/products/{productId}")
    public ResponseEntity<ProductDto>updateCategory(
                                            @PathVariable String categoryId,
                                            @PathVariable String productId
    ){
        ProductDto productDto = productService.updateCategory(productId, categoryId);
        return new ResponseEntity<>(productDto,HttpStatus.OK);
    }
    @GetMapping("/{categoryId}/products")
    public ResponseEntity<PageableResponse<ProductDto>>getProductsofCategory(
            @PathVariable String categoryId,
            @RequestParam(value = "pageNumber",defaultValue="0",required = false)int pageNumber,
            @RequestParam(value = "pageSize",defaultValue="10",required = false)int pageSize,
            @RequestParam(value = "sortBy",defaultValue="tittle",required = false)String  sortBy,
            @RequestParam(value = "sortDir",defaultValue="asc",required = false)String sortDir)
    {
        PageableResponse<ProductDto> response = productService.getAllofCategory(categoryId,pageNumber,pageSize,sortBy,sortDir);
        return  new ResponseEntity<>(response,HttpStatus.OK);
    }
}
