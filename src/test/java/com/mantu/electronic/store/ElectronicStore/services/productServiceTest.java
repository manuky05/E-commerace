package com.mantu.electronic.store.ElectronicStore.services;

import com.mantu.electronic.store.ElectronicStore.entities.Product;
import com.mantu.electronic.store.ElectronicStore.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest

public class productServiceTest {

    @Autowired
    private ProductService productService;
    @MockBean
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;
    Product product;
    @BeforeEach
    public void init(){

    }




}
