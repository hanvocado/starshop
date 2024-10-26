package com.starshop.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {
	@GetMapping("/admin/products")
	public String products() {
		return "/admin/products";
	}
}
