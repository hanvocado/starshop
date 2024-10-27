package com.starshop.controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.starshop.models.Category;
import com.starshop.models.Product;
import com.starshop.services.CategoryService;
import com.starshop.services.ProductService;

@Controller
@RequestMapping("/admin")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/products")
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
	
	@GetMapping("/add-product")
	public String addProduct(Model model) {
		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);
		return "/admin/add-product";		
	}
}
