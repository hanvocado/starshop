package com.starshop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.starshop.models.User;
import com.starshop.models.Wishlist;
import com.starshop.models.WishlistKey;

public interface WishlistRepository extends JpaRepository<Wishlist, WishlistKey>{
    List<Wishlist> findByUser(User user);
}
