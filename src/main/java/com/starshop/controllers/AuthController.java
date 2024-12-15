package com.starshop.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.starshop.entities.Customer;
import com.starshop.entities.User;
import com.starshop.models.UserLogin;
import com.starshop.models.ViewMessage;
import com.starshop.services.AuthService;
import com.starshop.services.EmailService;
import com.starshop.services.JwtService;
import com.starshop.services.OtpStorageService;
import com.starshop.services.UserService;
import com.starshop.utils.Constants;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/auth")
@Slf4j
public class AuthController {
	@Autowired
	JwtService jwtService;

	@Autowired
	AuthService authService;

	@Autowired
	UserService userService;

	@Autowired
    private EmailService emailService;
	
	@Autowired
	private OtpStorageService otpStorageService;

	@GetMapping("/login")
	public String login(Model model) {
		ViewMessage message = (ViewMessage) model.asMap().get("result");
		model.addAttribute("message", message);
		return "login";
	}

	@PostMapping("/login")
	public String authenticate(Model model, @ModelAttribute("userLogin") UserLogin userLogin, BindingResult result,
			RedirectAttributes redirectAttributes, HttpServletResponse response, HttpServletRequest request)
			throws AuthenticationException {
		if (result.hasErrors()) {
			return "redirect:/auth/login";
		}

		// Check username và password
		boolean validated = userService.checkUserLogin(userLogin);
		if (!validated) {
			redirectAttributes.addFlashAttribute("result", new ViewMessage("danger", Constants.validateLoginFailed));
			return "redirect:/auth/login";
		}

		Authentication authentication = authService.authenticate(userLogin);
		if (authentication == null || !authentication.isAuthenticated()) {
			throw new AuthenticationException("Authentication failed for user: " + userLogin.getUsername());
		}

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtService.generateToken(authentication);

		// Đặt JWT vào Cookie
		Cookie cookie = new Cookie("jwt", jwt);
		cookie.setHttpOnly(true);
		cookie.setSecure(true);
		cookie.setPath("/");
		cookie.setMaxAge(24 * 60 * 60);
		response.addCookie(cookie);

		String role = userService.getUserRole(authentication);
		log.warn("User role: {}", role);

		switch (role) {
		case "ADMIN":
			return "redirect:/admin/categories";
		case "SHIPPER":
			return "redirect:/shipper/orders";
		case "CUSTOMER":
			return "redirect:/customer/products";
		default:
			log.warn("Unknown role: {}", role);
			return "redirect:/";
		}
	}

	@GetMapping("/register")
	public String register(Model model) {
		ViewMessage message = (ViewMessage) model.asMap().get("result");
		model.addAttribute("message", message);
		return "register";
	}
	
	@PostMapping("/sendOTP")
	public void sendOTP(@RequestParam String email) {
		emailService.sendOtp(email);			
	}

	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("user") Customer user, BindingResult result,
			String otp, RedirectAttributes redirectAttributes, Model model, HttpSession session) {
		if (result.hasErrors() || !otpStorageService.validateOtp(user.getEmail(), otp)) {
			return "redirect:/auth/register";
		}
		
		otpStorageService.clearOtp(user.getEmail());
		boolean success = userService.addCustomer(user);
		if (success) {
			redirectAttributes.addFlashAttribute("result", new ViewMessage("success", Constants.registerSuccess));
			return "redirect:/auth/login";
		} else {
			redirectAttributes.addFlashAttribute("result", new ViewMessage("danger", Constants.registerFailed));
			return "redirect:/auth/register";
		}		
	}


	@PostMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = new Cookie("jwt", null);
		cookie.setHttpOnly(true);
		cookie.setSecure(true);
		cookie.setPath("/");
		cookie.setMaxAge(0);
		response.addCookie(cookie);

		return "redirect:/auth/login";
	}

}
