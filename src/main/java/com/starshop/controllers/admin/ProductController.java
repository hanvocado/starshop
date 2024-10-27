package com.starshop.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.starshop.models.Product;
import com.starshop.services.ProductService;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@GetMapping("/admin/products")
	public String products(Model model, String search, Integer pageNo, Integer pageSize) {
		
		Page<Product> page = null;
		if (pageNo == null) pageNo = 0;
		if (pageSize == null) pageSize = 2;
		if (search != null && search.length() > 0) {
			page = productService.searchProductPagination(pageNo, pageSize, search);
		} else {
			page = productService.getAllProductsPagination(pageNo, pageSize);
		}
		
		model.addAttribute("products", page.getContent());
		model.addAttribute("pageNo", page.getNumber());
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("count", page.getTotalElements());
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("isFirst", page.isFirst());
		model.addAttribute("isLast", page.isLast());
		return "/admin/products";
	}
	
	
}
