package com.starshop.controllers.customer;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.starshop.entities.Address;
import com.starshop.entities.Customer;
import com.starshop.entities.Product;
import com.starshop.entities.ProductLine;
import com.starshop.entities.User;
import com.starshop.entities.Voucher;
import com.starshop.models.OrderInfo;
import com.starshop.models.ViewMessage;
import com.starshop.services.AddressService;
import com.starshop.services.CustomerService;
import com.starshop.services.FeeService;
import com.starshop.services.JwtService;
import com.starshop.services.ProductLineService;
import com.starshop.services.ProductService;
import com.starshop.services.VoucherService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/customer")
public class CustomerOrderController {
	@Autowired
	private JwtService jwtService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private ProductLineService productLineService;

	@Autowired
	private ProductService productService;

	@Autowired
	private VoucherService voucherService;
	
	@Autowired
	private FeeService feeService;
	
	@Autowired
	private CustomerService customerService;

//	@GetMapping("/orders")
//	public String listUserOrders() {  }

	@RequestMapping("/order")
	public String placeOrder(@ModelAttribute OrderInfo order,
			@RequestParam(value = "selectedProductLineIds", required = false) String selectedProductLineIds,
			@RequestParam(value = "productId", required = false) Long productId,
			@RequestParam(value = "quantity", defaultValue = "1", required = false) int quantity, Principal principal,
			Model model, BindingResult result, HttpServletRequest request) {
		User user = jwtService.getUserFromPrincipal(principal);
		Customer customer = jwtService.getCustomerFromPrincipal(principal);
		Address address = addressService.getDefaultAddress(customer);

		ViewMessage message = (ViewMessage) model.asMap().get("result");
		model.addAttribute("message", message);

		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
		Double finalPrice = 0.0;
		if (inputFlashMap != null) {
			finalPrice = (Double) inputFlashMap.get("finalPrice");
			Double discount = (Double) inputFlashMap.get("discount");
			Double freeShip = (Double) inputFlashMap.get("freeShip");
			String voucherCode = (String) inputFlashMap.get("voucherCode");
			selectedProductLineIds = (String) inputFlashMap.get("selectedProductLineIds");
			productId = (Long) inputFlashMap.get("productId");

			order.setFinalPrice(finalPrice != null ? finalPrice : 0.0);
			order.setDiscount(discount != null ? discount : 0.0);
			order.setFreeShip(freeShip != null ? freeShip : 0.0);
			order.setVoucherCode(voucherCode);
		}

		List<ProductLine> selectedProductLines = new ArrayList<>();

		// Người mua có thể thanh toán tại trang sản phẩm hoặc thanh toán từ giỏ hàng
		if (productId != null) {
			Product product = productService.getById(productId);
			ProductLine productLine = new ProductLine();
			productLine.setProduct(product);
			productLine.setQuantity(quantity);
			selectedProductLines.add(productLine);
			if (inputFlashMap != null)
				order.setFinalPrice(finalPrice != null ? finalPrice : 0.0);
			else
				order.setFinalPrice(productLine.getSubTotal());
		} else if (selectedProductLineIds != null && !selectedProductLineIds.isEmpty()) {
			selectedProductLines = productLineService.getProductLinesByIds(selectedProductLineIds);
		}
		order.setSelectedProductLines(selectedProductLines);
		order.setCustomer(customer);
		order.setAddress(address);
		
		Optional<Address> optionalAddress  = customerService.getDefaultAddress(customer);
		Address defaultAddress = optionalAddress.orElse(null); 
		int shippingFee = feeService.getShippingFeeVND(defaultAddress);
		order.setShippingFee(shippingFee);
		double finalPriceAfterShip = order.getFinalPrice();
		order.setFinalPrice(finalPriceAfterShip - shippingFee);

		model.addAttribute("user", user);
		model.addAttribute("order", order);
		model.addAttribute("productId", productId);
		model.addAttribute("selectedProductLineIds", selectedProductLineIds);

		List<Voucher> discountVouchers = voucherService.getAvailableDiscountVouchers(customer.getId());
		List<Voucher> freeShipVouchers = voucherService.getAvailableFreeShipVouchers(customer.getId());
		model.addAttribute("discountVouchers", discountVouchers);
		model.addAttribute("freeShipVouchers", freeShipVouchers);

		return "customer/order";
	}

//	@PostMapping("/submit")
//    public String submitOrder(@ModelAttribute OrderInfo order, 
//                              Principal principal, 
//                              Model model) {
//        User user = jwtService.getUserFromPrincipal(principal);
//        Customer customer = jwtService.getCustomerFromPrincipal(principal);
//
//        Address defaultAddress = addressService.getDefaultAddress(customer);
//        order.setCustomer(customer);
//        order.setAddress(defaultAddress);
//
//        // Tính toán tổng tiền và các giảm giá từ voucher
//        double subtotal = order.getSelectedProductLines()
//                               .stream()
//                               .mapToDouble(productLine -> 
//                                   productLine.getProduct().getDisplayPrice() * productLine.getQuantity())
//                               .sum();
//
//        double discount = order.getDiscount();
//        double freeShip = order.getFreeShip();
//        double finalPrice = subtotal - discount - freeShip;
//
//        order.setSubtotal(subtotal);
//        order.setFinalPrice(finalPrice);
//
//        // Lưu order vào cơ sở dữ liệu
//        Order savedOrder = orderService.saveOrder(order);
//
//        // Chuyển đến trang xác nhận
//        model.addAttribute("order", savedOrder);
//        model.addAttribute("message", "Đơn hàng của bạn đã được đặt thành công!");
//        return "customer/order-confirmation";
//    }

//	@PostMapping("/orders/{order-id}/return-request")
//	public String requestReturn(@PathVariable Long orderId) { }

}
