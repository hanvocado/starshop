package com.starshop.controllers.users;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.starshop.entities.Product;
import com.starshop.entities.User;
import com.starshop.services.ProductService;
import com.starshop.services.UserService;

@Controller
public class UserProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;

	@GetMapping("/products")
	public String publishedProducts(Model model, Integer pageNo, Integer pageSize, String search,
			@RequestParam(required = false) UUID userId) {

		Page<Product> page = null;
		if (pageNo == null)
			pageNo = 0;
		if (pageSize == null)
			pageSize = 21;
		page = productService.getPublishedProductsPagination(pageNo, pageSize, null);

		model.addAttribute("role", "user");
		model.addAttribute("products", page.getContent());
		model.addAttribute("pageNo", page.getNumber());
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("count", page.getTotalElements());
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("isFirst", page.isFirst());
		model.addAttribute("isLast", page.isLast());
		/*
		 * if (!user.isEmpty()) { model.addAttribute("user", user); }
		 */
//		userId = (UUID)1;
		if (userId != null) {
			Optional<User> user = userService.getUserById(userId);
			user.ifPresent(value -> model.addAttribute("user", value));
		}

		return "index";
	}

	@PostMapping("/{userId}/wishlist")
	public String addToWishlist(@PathVariable UUID userId, @RequestParam Long productId) {
		userService.addProductToWishlist(userId, productId);
		return "redirect:/users/" + userId + "/wishlist";
	}

	@GetMapping("/{userId}/wishlist")
	public String getWishlist(@PathVariable UUID userId, Model model) {
		List<Product> wishlist = userService.getWishlist(userId);
		model.addAttribute("wishlist", wishlist);
		return "user/wishlist";
	}
	
	@GetMapping("/user/cart")
	public String abc() {
		return "user/cart";
	}
	
	@GetMapping("/user/payment")
	public String abd() {
		return "user/payment";
	}
	
    @GetMapping("/products/{id}")
    public String productDetails(@PathVariable Long id, Model model) {
        Product product = productService.getById(id);
        model.addAttribute("product", product);
        return "user/product-details"; 
    }
    
    
}
