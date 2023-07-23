package com.mantu.electronic.store.ElectronicStore.services;

import com.mantu.electronic.store.ElectronicStore.dtos.AddItemToCartRequest;
import com.mantu.electronic.store.ElectronicStore.dtos.CartDto;
import org.springframework.stereotype.Service;

@Service
public interface CartService {
    //add item in cart
    //case1: cart for user is not available:we will create the cart and then add
    //case2: cart available ad the item to cart
    CartDto addItemToCart(String userId, AddItemToCartRequest request);

    //remove item to cart:
    void removeItemFromCart(String userId,int cartItem);
    //remove all items from cart
    void clearCart(String userId);

    CartDto getCartByUser(String userId);
}
