package com.mantu.electronic.store.ElectronicStore.controlles;

import com.mantu.electronic.store.ElectronicStore.dtos.PageableResponse;
import com.mantu.electronic.store.ElectronicStore.dtos.ProductDto;
import com.mantu.electronic.store.ElectronicStore.exceptions.ApiResponseMessage;
import com.mantu.electronic.store.ElectronicStore.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;


    //create
    @PostMapping("/")
    public ResponseEntity<ProductDto>createProduct(@RequestBody ProductDto productDto){
        ProductDto productDto1 = productService.create(productDto);
        return new ResponseEntity<>(productDto1, HttpStatus.CREATED);
    }

    //update
    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto>updateProduct(@PathVariable String productId, ProductDto productDto){
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
                                                   @RequestParam(value = "sortBy",defaultValue="categoryId",required = false)String  sortBy,
                                                   @RequestParam(value = "sortDir",defaultValue="asc",required = false)String sortDir){
        PageableResponse<ProductDto> pageableResponse = productService.getAll(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(pageableResponse,HttpStatus.OK);

    }
    //get all live
    @GetMapping("/live")
    public ResponseEntity<PageableResponse<ProductDto>>getAllLive( @RequestParam(value = "pageNumber",defaultValue="0",required = false)int pageNumber,
                                                   @RequestParam(value = "pageSize",defaultValue="10",required = false)int pageSize,
                                                   @RequestParam(value = "sortBy",defaultValue="categoryId",required = false)String  sortBy,
                                                   @RequestParam(value = "sortDir",defaultValue="asc",required = false)String sortDir){
        PageableResponse<ProductDto> pageableResponse = productService.getAllLive(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(pageableResponse,HttpStatus.OK);

    }

    //search all
    @GetMapping("/search/{query}")
    public ResponseEntity<PageableResponse>searchProduct(@PathVariable String query,
                                                   @RequestParam(value = "pageNumber",defaultValue="0",required = false)int pageNumber,
                                                   @RequestParam(value = "pageSize",defaultValue="10",required = false)int pageSize,
                                                   @RequestParam(value = "sortBy",defaultValue="categoryId",required = false)String  sortBy,
                                                   @RequestParam(value = "sortDir",defaultValue="asc",required = false)String sortDir){
        PageableResponse<ProductDto> pageableResponse = productService.searchByTittle(query,pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(pageableResponse,HttpStatus.OK);

    }
}
