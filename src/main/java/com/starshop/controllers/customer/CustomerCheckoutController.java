package com.starshop.controllers.customer;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.starshop.entities.User;
import com.starshop.services.JwtService;
import com.starshop.services.VNPayService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/customer/order")
public class CustomerCheckoutController {

	@Autowired
	private VNPayService vnPayService;
	
	@Autowired
	private JwtService jwtService;
	
	 @GetMapping("/vnpay-payment")
	    public String GetMapping(HttpServletRequest request, Model model, Principal principal){
		 	User user = jwtService.getUserFromPrincipal(principal);
	        int paymentStatus =vnPayService.orderReturn(request);

	        String orderInfo = request.getParameter("vnp_OrderInfo");
	        String paymentTime = request.getParameter("vnp_PayDate");
	        String transactionId = request.getParameter("vnp_TransactionNo");
	        String totalPrice = request.getParameter("vnp_Amount");

	        model.addAttribute("orderId", orderInfo);
	        model.addAttribute("totalPrice", totalPrice);
	        model.addAttribute("paymentTime", paymentTime);
	        model.addAttribute("transactionId", transactionId);
	        model.addAttribute("user", user);

	        return paymentStatus == 1 ? "customer/order-success" : "customer/order-fail";
	    }
//	@PostMapping("/checkout")
//	public String placeOrder() { }

}
