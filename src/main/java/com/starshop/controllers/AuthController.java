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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.starshop.entities.User;
import com.starshop.models.UserLogin;
import com.starshop.models.ViewMessage;
import com.starshop.services.AuthService;
import com.starshop.services.JwtService;
import com.starshop.services.UserService;
import com.starshop.utils.Constants;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

	@GetMapping("/login")
	public String login(Model model) {
		ViewMessage message = (ViewMessage) model.asMap().get("result");
		model.addAttribute("message", message);
		return "login";
	}

	@PostMapping("/login")
	public String authenticate(Model model, @ModelAttribute("userLogin") UserLogin userLogin, BindingResult result,
			RedirectAttributes redirectAttributes, HttpServletResponse response, HttpServletRequest request) throws AuthenticationException {
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

//		// Đặt JWT vào Cookie
//	    Cookie cookie = new Cookie("jwt", jwt);
//	    cookie.setHttpOnly(true); 
//	    cookie.setSecure(true);   
//	    cookie.setPath("/");      
//	    cookie.setMaxAge(3600);   
//	    response.addCookie(cookie);	
//		redirectAttributes.addFlashAttribute("jwt", jwt);

		String role = userService.getUserRole(authentication);
		log.warn("User role: {}", role);

		switch (role) {
		case "ADMIN":
			return "redirect:/admin/categories";
		case "USER":
			return "redirect:/products";
		default:
			log.warn("Unknown role: {}", role);
			return "redirect:/products";
		}
	}

	@GetMapping("/register")
	public String register(Model model) {
		ViewMessage message = (ViewMessage) model.asMap().get("result");
		model.addAttribute("message", message);
		return "register";
	}

	@PostMapping("/register")
	public String addUser(ModelMap model, @Valid @ModelAttribute("user") User user, BindingResult result,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "redirect:/auth/register";
		}

		boolean success = userService.addUser(user);
		if (!success) {
			redirectAttributes.addFlashAttribute("result", new ViewMessage("danger", Constants.registerFailed));
			return "redirect:/auth/register";
		}

		redirectAttributes.addFlashAttribute("result", new ViewMessage("success", Constants.registerSuccess));
		return "redirect:/auth/login";
	}
//	
//	@Autowired
//  private AuthenticationManager authenticationManager;
//	 @PostMapping("/login")
//	    public ResponseEntity<?> login(@RequestBody UserLogin userLogin) throws IllegalAccessException {
//	        Authentication authentication =
//	                authenticationManager
//	                        .authenticate(new UsernamePasswordAuthenticationToken(
//	                                userLogin.getUsername(),
//	                                userLogin.getPassword()));
//	        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//	        UserLogin userDetails = (UserLogin) authentication.getPrincipal();
//
//
//	        log.info("Token requested for user :{}", authentication.getAuthorities());
//	        String token = jwtService.generateToken(authentication);
//
////	        AuthDTO.Response response = new AuthDTO.Response("User logged in successfully", token);
//
//	        return ResponseEntity.ok(token);
//	    }

}
