package com.starshop.controllers.customer;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.starshop.entities.Customer;
import com.starshop.entities.Product;
import com.starshop.models.ViewMessage;
import com.starshop.services.CustomerService;
import com.starshop.services.JwtService;
import com.starshop.services.UserService;
import com.starshop.utils.Constants;

import jakarta.persistence.EntityNotFoundException;

@Controller
@RequestMapping("/customer")
public class CustomerWishlistController {

	@Autowired
	private JwtService jwtService;

	@Autowired
	private CustomerService customerService;

	@GetMapping("/wishlist")
	public String getWishlist(Model model, Principal principal) {
		List<Product> wishlist = customerService.getWishlist(principal.getName());
		model.addAttribute("wishlist", wishlist);
		
		ViewMessage message = (ViewMessage) model.asMap().get("result");
		model.addAttribute("message", message);
		
		return "customer/wishlist";
	}

	@PostMapping("/add-wishlist/{product-id}")
	public String addToWishlist(@PathVariable("product-id") Long productId, Principal principal, RedirectAttributes redirectAttributes) {
		Customer customer = jwtService.getCustomerFromPrincipal(principal);
		
		try {
	        customerService.addToWishlist(customer, productId);
	        redirectAttributes.addFlashAttribute("result", new ViewMessage("success", Constants.addWishlistSuccess));
	    } catch (IllegalStateException e) {
	    	redirectAttributes.addFlashAttribute("result", new ViewMessage("danger", Constants.existedWishlist));
	    }
	    return "redirect:/customer/products";
	}

	@PostMapping("/remove-wishlist/{product-id}")
	public String removeFromWishlist(@PathVariable("product-id") Long productId, Principal principal,
			RedirectAttributes redirectAttributes) {
		Customer customer = jwtService.getCustomerFromPrincipal(principal);
		
		try {
			customerService.removeFromWishlist(customer, productId);
			redirectAttributes.addFlashAttribute("result", new ViewMessage("success", Constants.removeWishlistSuccess));
		} catch (EntityNotFoundException e) {
			redirectAttributes.addFlashAttribute("result", new ViewMessage("danger", Constants.notExistWishlist));
		}
		return "redirect:/customer/wishlist";
	}

}
