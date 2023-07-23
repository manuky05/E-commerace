package com.mantu.electronic.store.ElectronicStore.entities;

import com.mantu.electronic.store.ElectronicStore.dtos.CategoryDto;
import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.Date;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="products")
@Builder
public class Product {
    @Id
    private String productId;
    private String tittle;
    @Column(length=10000)
    private String description;
    private int price;
    private int discountedPrice;
    private int quantity;
    private Date addedDate;
    private boolean live;
    private boolean stock;
    private  String ProductImageName;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="category_id")
    private Category category;





}
