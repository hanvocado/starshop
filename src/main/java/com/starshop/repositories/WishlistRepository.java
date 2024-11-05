package com.starshop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.starshop.entities.User;
import com.starshop.entities.Wishlist;
import com.starshop.entities.compositekeys.WishlistKey;

public interface WishlistRepository extends JpaRepository<Wishlist, WishlistKey>{
    List<Wishlist> findByUser(User user);
}
