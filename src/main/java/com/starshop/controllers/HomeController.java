package com.starshop.controllers;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.starshop.entities.Category;
import com.starshop.entities.Product;
import com.starshop.entities.User;
import com.starshop.models.ViewMessage;
import com.starshop.services.CategoryService;
import com.starshop.services.JwtService;
import com.starshop.services.ProductService;
import com.starshop.services.UserService;

@Controller
@ControllerAdvice
public class HomeController {

	@Autowired
	private ProductService productService;

	@Autowired
    private CategoryService categoryService;
	
	@Autowired
	private JwtService jwtService;
	
	@GetMapping(value = {"", "/", "/products", "/categories/{categoryName}"})
	public String publishedProducts(Model model, Integer pageNo, Integer pageSize, @RequestParam(value = "search", required = false) String search,
			@RequestParam(required = false) UUID userId, Principal principal,
			@PathVariable(value = "categoryName", required = false) String categoryName)
			throws AuthenticationException {
		if (principal != null) {
			User user = jwtService.getUserFromPrincipal(principal);
			model.addAttribute("user", user);			
		}

		Page<Product> page = null;
		if (pageNo == null)
			pageNo = 0;
		if (pageSize == null)
			pageSize = 21;
		
		if (categoryName != null) {
	        if (search != null && !search.isEmpty()) {
	            page = productService.searchPublishedProductsByCategoryAndName(search, categoryName, pageNo, pageSize);
	        } else {
	            page = productService.getPublishedProductsByCategory(categoryName, pageNo, pageSize);
	        }
	        model.addAttribute("categoryName", categoryName);
	    } else {
	    	page = productService.getPublishedProductsPagination(pageNo, pageSize, search);
	    }

		ViewMessage message = (ViewMessage) model.asMap().get("result");
		
		model.addAttribute("message", message);
		model.addAttribute("products", page.getContent());
		model.addAttribute("pageNo", page.getNumber());
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("count", page.getTotalElements());
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("isFirst", page.isFirst());
		model.addAttribute("isLast", page.isLast());
		model.addAttribute("categoryName", categoryName);

		return "index";
	}
	
	@ModelAttribute("categories")
    public List<Category> populateCategories() {
        return categoryService.getPublishedCategories(); 
    }
	
	 @ModelAttribute("user")
	    public User populateUser(Principal principal) {
	        if (principal != null) {
	            return jwtService.getUserFromPrincipal(principal);
	        }
	        return null; 
	    }
}