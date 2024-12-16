package com.starshop.controllers.customer;

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

import com.starshop.entities.Address;
import com.starshop.entities.Customer;
import com.starshop.entities.Product;
import com.starshop.entities.User;
import com.starshop.models.ViewMessage;
import com.starshop.services.CustomerService;
import com.starshop.services.FeeService;
import com.starshop.services.JwtService;
import com.starshop.services.ProductService;
import com.starshop.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/customer")
@Slf4j
public class CustomerProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;

	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private FeeService feeService;
	
	@Autowired
	private CustomerService customerService;

	@GetMapping("/products")
	public String publishedProducts(Model model, Integer pageNo, Integer pageSize, String search,
			@RequestParam(required = false) UUID userId, @AuthenticationPrincipal UserDetails userDetails)
			throws AuthenticationException {

		return "redirect:/";
		/*
		 * String userName = userDetails.getUsername(); User user =
		 * userService.getUserByUserName(userName);
		 * 
		 * Page<Product> page = null; if (pageNo == null) pageNo = 0; if (pageSize ==
		 * null) pageSize = 21;
		 * 
		 * page = productService.getPublishedProductsPagination(pageNo, pageSize, null);
		 * ViewMessage message = (ViewMessage) model.asMap().get("result");
		 * 
		 * model.addAttribute("message", message); model.addAttribute("user", user);
		 * model.addAttribute("products", page.getContent());
		 * model.addAttribute("pageNo", page.getNumber());
		 * model.addAttribute("pageSize", pageSize); model.addAttribute("count",
		 * page.getTotalElements()); model.addAttribute("totalPages",
		 * page.getTotalPages()); model.addAttribute("isFirst", page.isFirst());
		 * model.addAttribute("isLast", page.isLast());
		 * 
		 * return "index";
		 */
	}
	
	@GetMapping("/products/details/{product-id}")
	public String getProductDetails(@PathVariable("product-id") Long id, Model model, Principal principal) {
		User user = jwtService.getUserFromPrincipal(principal);
		Customer customer = jwtService.getCustomerFromPrincipal(principal);
		Product product = productService.getById(id);
		Optional<Address> optionalAddress  = customerService.getDefaultAddress(customer);
		Address defaultAddress = optionalAddress.orElse(null); 
		int shippingFee = feeService.getShippingFeeVND(defaultAddress);
		model.addAttribute("product", product);
		model.addAttribute("user", user);
		model.addAttribute("customer", customer);
		model.addAttribute("shippingFee", shippingFee);
		model.addAttribute("defaultAddress", defaultAddress);
		return "customer/product-details";
	}
	
//	@GetMapping("/categories/{categoryId}/products")
//	public String getProductsByCategory(@PathVariable Long categoryId) {  }

}
