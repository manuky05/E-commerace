package com.mantu.electronic.store.ElectronicStore.controlles;

import com.mantu.electronic.store.ElectronicStore.dtos.AddItemToCartRequest;
import com.mantu.electronic.store.ElectronicStore.dtos.CartDto;
import com.mantu.electronic.store.ElectronicStore.exceptions.ApiResponseMessage;
import com.mantu.electronic.store.ElectronicStore.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {
    @Autowired
    private CartService cartService;
    // add item to cart
    @PostMapping("/{usrId}")
    public ResponseEntity<CartDto> addItemTocart(@PathVariable String userId, @RequestBody AddItemToCartRequest request){
        CartDto cartDto = cartService.addItemToCart(userId, request);


        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }
    @DeleteMapping("/{userId}/items/{itemId}")
    public ResponseEntity<ApiResponseMessage>clearmCart(@PathVariable String userId, @PathVariable int itemId ){
         cartService.removeItemFromCart(userId,itemId);
        ApiResponseMessage response=ApiResponseMessage.builder().message("Item is remove").success(true).status(HttpStatus.OK).build();
         return new ResponseEntity<>(response,HttpStatus.OK);
    }
    //clear cart
    @DeleteMapping("/{itemId}")
    public ResponseEntity<ApiResponseMessage>removeItemFromCart(@PathVariable String userId ){
        cartService.clearCart(userId);
        ApiResponseMessage response=ApiResponseMessage.builder().message("cart is clear").success(true).status(HttpStatus.OK).build();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @GetMapping("/{usrId}")
    public ResponseEntity<CartDto> getCart(@PathVariable String userId){
        CartDto cartDto = cartService.getCartByUser(userId);


        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

}
