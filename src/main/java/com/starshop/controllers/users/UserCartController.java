package com.starshop.controllers.users;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserCartController {

	@GetMapping("/cart")
	public String viewCart() { 
		return "user/cart";
	}
	
//	@PostMapping("/cart/{product-id}")
//	public String addToCart(@PathVariable Long productId) { }
	
//	@PostMapping("/cart/{product-id}")
//	public String updateCartItem(@PathVariable Long productId, @RequestParam Integer quantity) {  }

//	@PostMapping("/cart/{product-id}")
//	public String removeFromCart(@PathVariable Long productId) { }


}
