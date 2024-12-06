package com.starshop.controllers.customer;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.starshop.entities.Product;
import com.starshop.services.UserService;

@Controller
@RequestMapping("/customer")
public class CustomerWishlistController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/wishlist")
	public String getWishlist(@PathVariable UUID userId, Model model) {
		List<Product> wishlist = userService.getWishlist(userId);
		model.addAttribute("wishlist", wishlist);
		return "customer/wishlist";
	}
	
//	@PostMapping("/wishlist/{product-id}")
//	public String addToWishlist(@PathVariable Long productId) { }

//	@PostMapping("/wishlist/{product-id}")
//	public String removeFromWishlist(@PathVariable Long productId) { }
	
}
