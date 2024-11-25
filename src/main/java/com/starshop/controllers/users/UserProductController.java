package com.starshop.controllers.users;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
import com.starshop.services.JwtService;
import com.starshop.services.ProductService;
import com.starshop.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;

	@Autowired
	private JwtService jwtService;

	@GetMapping("/products")
	public String publishedProducts(Model model, Integer pageNo, Integer pageSize, String search,
			@RequestParam(required = false) UUID userId, @AuthenticationPrincipal UserDetails userDetails)
			throws AuthenticationException {

		String userName = userDetails.getUsername();
		User user = userService.getUserByUserName(userName);

		Page<Product> page = null;
		if (pageNo == null)
			pageNo = 0;
		if (pageSize == null)
			pageSize = 21;

		page = productService.getPublishedProductsPagination(pageNo, pageSize, null);

		model.addAttribute("user", user);
		model.addAttribute("products", page.getContent());
		model.addAttribute("pageNo", page.getNumber());
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("count", page.getTotalElements());
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("isFirst", page.isFirst());
		model.addAttribute("isLast", page.isLast());

		return "index";
	}
	
	@GetMapping("/products/{product-id}")
	public String getProductDetails(@PathVariable("product-id") Long id, Model model) {
		Product product = productService.getById(id);
		model.addAttribute("product", product);
		return "user/product-details";
	}
	
//	@GetMapping("/categories/{categoryId}/products")
//	public String getProductsByCategory(@PathVariable Long categoryId) {  }

}
