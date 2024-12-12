package com.starshop.controllers.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.starshop.services.VNPayService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/customer")
public class CustomerCheckoutController {

	@Autowired
	private VNPayService vnPayService;

	@GetMapping("/checkout")
	public String checkout(@RequestParam("amount") int orderTotal, @RequestParam("orderInfo") String orderInfo,
			HttpServletRequest request) {
		String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        String vnpayUrl = vnPayService.createOrder(orderTotal, orderInfo, baseUrl);
        return "redirect:" + vnpayUrl;
	}
	
	 @GetMapping("/vnpay-payment")
	    public String GetMapping(HttpServletRequest request, Model model){
	        int paymentStatus =vnPayService.orderReturn(request);

	        String orderInfo = request.getParameter("vnp_OrderInfo");
	        String paymentTime = request.getParameter("vnp_PayDate");
	        String transactionId = request.getParameter("vnp_TransactionNo");
	        String totalPrice = request.getParameter("vnp_Amount");

	        model.addAttribute("orderId", orderInfo);
	        model.addAttribute("totalPrice", totalPrice);
	        model.addAttribute("paymentTime", paymentTime);
	        model.addAttribute("transactionId", transactionId);

	        return paymentStatus == 1 ? "ordersuccess" : "orderfail";
	    }

//	@PostMapping("/checkout")
//	public String placeOrder() { }

}
