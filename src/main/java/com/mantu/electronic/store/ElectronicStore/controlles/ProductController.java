package com.mantu.electronic.store.ElectronicStore.controlles;

import com.mantu.electronic.store.ElectronicStore.dtos.ImageResponse;
import com.mantu.electronic.store.ElectronicStore.dtos.PageableResponse;
import com.mantu.electronic.store.ElectronicStore.dtos.ProductDto;
import com.mantu.electronic.store.ElectronicStore.dtos.UserDto;
import com.mantu.electronic.store.ElectronicStore.exceptions.ApiResponseMessage;
import com.mantu.electronic.store.ElectronicStore.services.FileService;
import com.mantu.electronic.store.ElectronicStore.services.ProductService;
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
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/products")
public class ProductController {
    private Logger logger= LoggerFactory.getLogger(ProductController .class);
    @Autowired
    private ProductService productService;
    @Autowired
    private FileService fileService;
    @Value("${product.profile.image.path}")
    private String imagePath;


    //create
    @PostMapping("/")
    public ResponseEntity<ProductDto>createProduct(@RequestBody ProductDto productDto){
        ProductDto productDto1 = productService.create(productDto);
        return new ResponseEntity<>(productDto1, HttpStatus.CREATED);
    }

    //update
    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto>updateProduct(@PathVariable String productId, @RequestBody ProductDto productDto){
        ProductDto updatedproduct = productService.update(productDto,productId);
        return new ResponseEntity<>(updatedproduct, HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponseMessage>DeleteProduct(@PathVariable String productId){
        ApiResponseMessage responseMessage = ApiResponseMessage.builder().message("Product Deleted successfully").success(true).status(HttpStatus.OK).build();
        return new ResponseEntity<>(responseMessage ,HttpStatus.OK);
    }

    //get single
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto>getProduct(@PathVariable String productId){
        ProductDto productDto=productService.get(productId);
        return  new ResponseEntity<>(productDto,HttpStatus.OK);

    }

    //get all
    @GetMapping()
    public ResponseEntity<PageableResponse<ProductDto>>getAll( @RequestParam(value = "pageNumber",defaultValue="0",required = false)int pageNumber,
                                                   @RequestParam(value = "pageSize",defaultValue="10",required = false)int pageSize,
                                                   @RequestParam(value = "sortBy",defaultValue="productId",required = false)String  sortBy,
                                                   @RequestParam(value = "sortDir",defaultValue="asc",required = false)String sortDir){
        PageableResponse<ProductDto> pageableResponse = productService.getAll(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(pageableResponse,HttpStatus.OK);

    }
    //get all live
    @GetMapping("/live")
    public ResponseEntity<PageableResponse<ProductDto>>getAllLive( @RequestParam(value = "pageNumber",defaultValue="0",required = false)int pageNumber,
                                                   @RequestParam(value = "pageSize",defaultValue="10",required = false)int pageSize,
                                                   @RequestParam(value = "sortBy",defaultValue="productId",required = false)String  sortBy,
                                                   @RequestParam(value = "sortDir",defaultValue="asc",required = false)String sortDir){
        PageableResponse<ProductDto> pageableResponse = productService.getAllLive(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(pageableResponse,HttpStatus.OK);

    }

    //search all
    @GetMapping("/search/{query}")
    public ResponseEntity<PageableResponse<ProductDto>>searchProduct(@PathVariable String query,
                                                   @RequestParam(value = "pageNumber",defaultValue="0",required = false)int pageNumber,
                                                   @RequestParam(value = "pageSize",defaultValue="10",required = false)int pageSize,
                                                   @RequestParam(value = "sortBy",defaultValue="productId",required = false)String  sortBy,
                                                   @RequestParam(value = "sortDir",defaultValue="asc",required = false)String sortDir){
        PageableResponse<ProductDto> pageableResponse = productService.searchByTittle(query,pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(pageableResponse,HttpStatus.OK);

    }

    @PostMapping("/image/{productId}")
    public ResponseEntity<ImageResponse>uploadProductImage(
            @RequestParam("productImage") MultipartFile image,
            @PathVariable String productId
    ) throws IOException {
        String fileName = fileService.uploadFile(image, imagePath);
        ProductDto productDto = productService.get(productId);
        productDto.setProductImageName(fileName);
        ProductDto updatedProduct = productService.update(productDto, productId);
        ImageResponse response=ImageResponse.builder().imageName(updatedProduct.getProductImageName()).message("Product Image is Successfully uploaded !!").status(HttpStatus.CREATED).success(true).build();
       return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
    @GetMapping(value="/image/{productId}")
    public void serveProductImage(@PathVariable String productId, HttpServletResponse response) throws IOException {
        ProductDto productDto = productService.get(productId);

        logger.info("Product image name:{}",productDto.getProductImageName());
        InputStream resource = fileService.getResource(imagePath, productDto.getProductImageName());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());

    }


}
