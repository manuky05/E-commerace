package com.mantu.electronic.store.ElectronicStore.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddItemToCartRequest {
    private String productId;
    private String  quantity;
}
