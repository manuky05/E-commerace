package com.mantu.electronic.store.ElectronicStore.dtos;

import com.mantu.electronic.store.ElectronicStore.entities.Category;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class ProductDto {

    private String productId;
    private String tittle;
    private String description;
    private int price;
    private int discountedPrice;
    private int quantity;
    private Date addedDate;
    private boolean live;
    private boolean stock;
    private  String ProductImageName;
    private CategoryDto category;

}
