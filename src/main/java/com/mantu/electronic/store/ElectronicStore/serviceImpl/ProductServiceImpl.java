package com.mantu.electronic.store.ElectronicStore.serviceImpl;

import com.mantu.electronic.store.ElectronicStore.dtos.PageableResponse;
import com.mantu.electronic.store.ElectronicStore.dtos.ProductDto;
import com.mantu.electronic.store.ElectronicStore.entities.Product;
import com.mantu.electronic.store.ElectronicStore.exceptions.ResourceNotFoundException;
import com.mantu.electronic.store.ElectronicStore.helper.Helper;
import com.mantu.electronic.store.ElectronicStore.repositories.ProductRepository;
import com.mantu.electronic.store.ElectronicStore.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public ProductDto create(ProductDto productDto) {
        String productId = UUID.randomUUID().toString();
        productDto.setProductId(productId);
        Product product= modelMapper.map(productDto, Product.class);
        Product saveproduct = productRepository.save(product);

        return modelMapper.map(saveproduct ,ProductDto.class);
    }

    @Override
    public ProductDto update(ProductDto productDto, String productId) {
        Product product=productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product not found with given Id"));

        product.setLive(productDto.isLive());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setStock(productDto.isStock());
        product.setQuantity(productDto.getQuantity());
        product.setTittle(productDto.getTittle());
        product.setDiscountedPrice(productDto.getDiscountedPrice());
        product.setQuantity(productDto.getQuantity());
        Product savedproduct = productRepository.save(product);
        return modelMapper.map(savedproduct,ProductDto.class);
    }

    @Override
    public void delete(String productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("product  not found with given Id "));
       productRepository.delete(product);
    }

    @Override
    public ProductDto get(String productId) {
        Optional<Product> product = productRepository.findById(productId);
        return modelMapper.map(product,ProductDto.class);
    }


    @Override
    public PageableResponse<ProductDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort=(sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());
        PageRequest pageable = PageRequest.of(pageNumber, pageSize,sort);
        Page<Product> page = productRepository.findAll(pageable);


        return Helper.getPageableResponse(page, ProductDto.class);
    }

    @Override
    public PageableResponse<ProductDto> getAllLive(int pageNumber,int pageSize,String sortBy,String sortDir) {
        Sort sort=(sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());
        PageRequest pageable = PageRequest.of(pageNumber-1, pageSize,sort);
        Page<Product> page = productRepository.findByLiveTrue(pageable);


        return Helper.getPageableResponse(page, ProductDto.class);
    }

    @Override
    public PageableResponse<ProductDto> searchByTittle(String subTittle, int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort=(sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());
        PageRequest pageable = PageRequest.of(pageNumber, pageSize,sort);
        Page<Product> page = productRepository.findByTittleContaining(subTittle, pageable);


        return Helper.getPageableResponse(page, ProductDto.class);
    }
}
