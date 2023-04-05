package com.mantu.electronic.store.ElectronicStore.dtos;

import lombok.*;

import javax.persistence.Column;
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
}
