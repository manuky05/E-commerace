package com.mantu.electronic.store.ElectronicStore.entities;

import lombok.*;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.util.Stack;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="cart_items")

public class CartItem {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
private  int cartItem;
    @JoinColumn(name="product-id")
    @OneToOne
    private Product product;
     private int quantity;
     private int totalPrice;
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name="cart_id")
   private Cart cart;
}
