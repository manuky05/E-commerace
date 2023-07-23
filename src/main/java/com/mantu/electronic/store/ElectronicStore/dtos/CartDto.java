package com.mantu.electronic.store.ElectronicStore.dtos;

import com.mantu.electronic.store.ElectronicStore.entities.CartItem;
import com.mantu.electronic.store.ElectronicStore.entities.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDto {

    private String cartId;
    private Date createdAt;
    private UserDto userDto;
    private List<CartItemDto> items=new ArrayList<>();

}
