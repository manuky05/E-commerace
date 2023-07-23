package com.mantu.electronic.store.ElectronicStore.repositories;

import com.mantu.electronic.store.ElectronicStore.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Integer> {

}
