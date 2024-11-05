package com.starshop.controllers;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.starshop.entities.AuthenticationResponse;
import com.starshop.entities.User;
import com.starshop.models.UserLogin;
import com.starshop.repositories.UserRepository;
import com.starshop.services.AuthService;
import com.starshop.services.JwtService;
import com.starshop.services.UserService;
import com.starshop.services.impl.UserServiceImpl;
import com.starshop.utils.Constants;
import com.starshop.utils.ViewMessage;

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
		ViewMessage message  = (ViewMessage) model.asMap().get("result");
        model.addAttribute("message", message);
		return "login";
	}
	
	@PostMapping("/login")
    public String authenticate(Model model, @ModelAttribute("userLogin") UserLogin userLogin, BindingResult result,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "redirect:/auth/login";
		}
		
        boolean validated = userService.checkUserLogin(userLogin);
        if(!validated) {
        	redirectAttributes.addFlashAttribute("result", new ViewMessage("danger", Constants.validateLoginFailed));
	        return "redirect:/auth/login"; 
        }
        
        User authenticatedUser = authService.authenticate(userLogin);
        model.addAttribute("user", authenticatedUser);

//        String jwtToken = jwtService.generateToken(authenticatedUser);
//        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());
        return "redirect:/user/products";
    }
//	@Autowired
//    private AuthenticationManager authenticationManager;
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
	 
	@GetMapping("/register")
	public String register(Model model) {
		ViewMessage message  = (ViewMessage) model.asMap().get("result");
        model.addAttribute("message", message);
		return "register";
	}
	
	@PostMapping("/token")
	public ResponseEntity<User> authenticate(@RequestBody UserLogin request) {
		var res = authService.authenticate(request);
		return ResponseEntity.ok(res);
	}
	
//	@PostMapping("/introspect")
//	public ResponseEntity<IntrospectResponse> authenticate(@RequestBody IntrospectToken request) throws JOSEException, ParseException {
//		var result = authenticationService.introspect(request);
//		/*
//		 * if (result) { return ResponseEntity.ok("Authentication successful"); } else {
//		 * return ResponseEntity.status(401).body("Invalid password"); }
//		 */
//		return ResponseEntity.ok(result);
//	}

}
