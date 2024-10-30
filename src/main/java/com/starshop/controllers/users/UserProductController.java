package com.starshop.controllers.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.starshop.models.Product;
import com.starshop.services.ProductService;
import com.starshop.services.UserService;

@Controller
@RequestMapping("/user")
public class UserProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;

	@GetMapping("")
	public String publishedProducts(Model model, Integer pageNo, Integer pageSize, String search) {

		Page<Product> page = null;
		if (pageNo == null)
			pageNo = 0;
		if (pageSize == null)
			pageSize = 20;
		page = productService.getPublishedProductsPagination(pageNo, pageSize, null);

		model.addAttribute("role", "user");
		model.addAttribute("products", page.getContent());
		model.addAttribute("pageNo", page.getNumber());
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("count", page.getTotalElements());
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("isFirst", page.isFirst());
		model.addAttribute("isLast", page.isLast());
		return "index";
	}

	@PostMapping("/{userId}/wishlist")
	public String addToWishlist(@PathVariable Long userId, @RequestParam Long productId) {
		userService.addProductToWishlist(userId, productId);
		return "redirect:/users/" + userId + "/wishlist"; 
	}

	@GetMapping("/{userId}/wishlist")
	public String getWishlist(@PathVariable Long userId, Model model) {
		List<Product> wishlist = userService.getWishlist(userId);
		model.addAttribute("wishlist", wishlist);
		return "wishlist"; 
	}
}
