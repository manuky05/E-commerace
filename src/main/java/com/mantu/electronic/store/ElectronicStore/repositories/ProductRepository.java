package com.mantu.electronic.store.ElectronicStore.repositories;

import com.mantu.electronic.store.ElectronicStore.dtos.PageableResponse;
import com.mantu.electronic.store.ElectronicStore.entities.Category;
import com.mantu.electronic.store.ElectronicStore.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,String> {
    Page<Product> findByTittleContaining(String subTittle,Pageable pageable);
    Page<Product> findByLiveTrue(Pageable pageable);
    Page<Product>findByCategory(Category category,Pageable pageable);
}

