package com.mantu.electronic.store.ElectronicStore.dtos;

import com.mantu.electronic.store.ElectronicStore.entities.Cart;
import com.mantu.electronic.store.ElectronicStore.entities.Product;
import lombok.*;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemDto {
    private  int cartItem;
    private ProductDto productDto;
    private int quantity;
    private int totalPrice;

 }
