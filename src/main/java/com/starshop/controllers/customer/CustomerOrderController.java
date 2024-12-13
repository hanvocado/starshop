package com.starshop.controllers.customer;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.starshop.entities.Address;
import com.starshop.entities.Customer;
import com.starshop.entities.ProductLine;
import com.starshop.entities.User;
import com.starshop.models.OrderInfo;
import com.starshop.services.AddressService;
import com.starshop.services.JwtService;
import com.starshop.services.ProductLineService;

@Controller
@RequestMapping("/customer")
public class CustomerOrderController {
	@Autowired
	private JwtService jwtService;

	@Autowired
	private AddressService addressService;
	
	@Autowired
	private ProductLineService productLineService;
	
//	@GetMapping("/orders")
//	public String listUserOrders() {  }

	@PostMapping("/order")
    public String placeOrder(@ModelAttribute OrderInfo order, @RequestParam("selectedProductLineIds") String selectedProductLineIds ,
    		Principal principal, Model model, BindingResult result) {
        User user = jwtService.getUserFromPrincipal(principal);
        Customer customer = jwtService.getCustomerFromPrincipal(principal);
        Address address = addressService.getDefaultAddress(customer);
        List<ProductLine> selectedProductLines = productLineService.getProductLinesByIds(selectedProductLineIds);
        order.setSelectedProductLines(selectedProductLines);
        order.setCustomer(customer);
        order.setAddress(address);

        model.addAttribute("user", user);
        model.addAttribute("order", order);

        return "customer/order";
    }

//	@PostMapping("/orders/{order-id}/return-request")
//	public String requestReturn(@PathVariable Long orderId) { }

}
