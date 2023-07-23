package com.mantu.electronic.store.ElectronicStore.serviceImpl;

import com.mantu.electronic.store.ElectronicStore.dtos.AddItemToCartRequest;
import com.mantu.electronic.store.ElectronicStore.dtos.CartDto;
import com.mantu.electronic.store.ElectronicStore.entities.Cart;
import com.mantu.electronic.store.ElectronicStore.entities.CartItem;
import com.mantu.electronic.store.ElectronicStore.entities.Product;
import com.mantu.electronic.store.ElectronicStore.entities.User;
import com.mantu.electronic.store.ElectronicStore.exceptions.BadApiRequestException;
import com.mantu.electronic.store.ElectronicStore.exceptions.ResourceNotFoundException;
import com.mantu.electronic.store.ElectronicStore.repositories.CartItemRepository;
import com.mantu.electronic.store.ElectronicStore.repositories.CartRepository;
import com.mantu.electronic.store.ElectronicStore.repositories.ProductRepository;
import com.mantu.electronic.store.ElectronicStore.repositories.UserRepository;
import com.mantu.electronic.store.ElectronicStore.services.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public CartDto addItemToCart(String userId, AddItemToCartRequest request) {
       int quantity = Integer.parseInt(request.getQuantity());

        String productId = request.getProductId();
        if(quantity<=0){
            throw new BadApiRequestException("Request quantity is not valied!!");
        }
        //fetch the product
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not fount id datbase !!"));
        // fetch the user from dbv

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found in database!!"));

        Cart cart =  null;
        try{
            cart =   cartRepository.findByUser(user).get();
        }catch(NoSuchElementException e){
            cart=new Cart();
            cart.setCartId(UUID.randomUUID().toString());
            cart.setCreatedAt(new Date());

        }
        // perform cart opretion


       AtomicReference<Boolean> updated=new AtomicReference<>(false);
        List<CartItem> items = cart.getItems();
       List<CartItem>  updatedItems= items.stream().map(item -> {
            if (item.getProduct().equals(productId)) {
            //item already present in cart
                item.setQuantity(quantity);
                item.setTotalPrice(quantity*product.getPrice());

                updated.set(true);
            }
            return item;

        }).collect(Collectors.toList());
       cart.setItems(updatedItems);
        // create items
        if(!updated.get()){
            CartItem cartItem = CartItem.builder()
                    .quantity(quantity)
                    .totalPrice(quantity * product.getPrice())
                    .cart(cart)
                    .product(product)
                    .build();
            cart.getItems().add(cartItem);
        }

        cart.setUser(user);
        Cart save = cartRepository.save(cart);
        return modelMapper.map(save,CartDto.class);
    }

    @Override
    public void removeItemFromCart(String userId, int cartItem) {
        //condition
        CartItem cartItem1 = cartItemRepository.findById(cartItem).orElseThrow(() -> new ResourceNotFoundException("Cart Item not found !1"));
        cartItemRepository.delete(cartItem1);

    }

    @Override
    public void clearCart(String userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found in database!!"));
        Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("Cart Of User Not found"));
        cart.getItems().clear();
        cartRepository.save(cart);
    }

    @Override
    public CartDto getCartByUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found in database!!"));
        Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("Cart Of User Not found"));
        return modelMapper.map(cart,CartDto.class);
    }
}
