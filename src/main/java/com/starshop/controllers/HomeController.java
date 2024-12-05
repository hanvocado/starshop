package com.starshop.controllers;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.starshop.entities.Product;
import com.starshop.entities.User;
import com.starshop.services.ProductService;
import com.starshop.services.UserService;

@Controller
public class HomeController {

	@Autowired
	private ProductService productService;

	@GetMapping("/")
	public String publishedProducts(Model model, Integer pageNo, Integer pageSize, String search) throws AuthenticationException {
		
		Page<Product> page = null;
		if (pageNo == null) pageNo = 0;
		if (pageSize == null) pageSize = 12;
		page = productService.getPublishedProductsPagination(pageNo, pageSize, null);
		
		model.addAttribute("user", null);
		model.addAttribute("products", page.getContent());
		model.addAttribute("pageNo", page.getNumber());
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("count", page.getTotalElements());
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("isFirst", page.isFirst());
		model.addAttribute("isLast", page.isLast());
		return "index";
	}
	
}