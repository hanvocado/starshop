package com.starshop.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.starshop.entities.Cart;
import com.starshop.entities.Product;
import com.starshop.entities.ProductLine;

public interface ProductLineRepository extends JpaRepository<ProductLine, Long> {
	
	Optional<ProductLine> findByCartIdAndProductId(Long cartId, Long productId);
	ProductLine findByCartAndProduct(Cart cart, Product product);
	List<ProductLine> findByCartId(Long cartId);
}
