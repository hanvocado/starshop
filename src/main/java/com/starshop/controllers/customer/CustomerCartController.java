package com.starshop.controllers.customer;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.starshop.entities.ProductLine;
import com.starshop.entities.Voucher;
import com.starshop.models.ViewMessage;
import com.starshop.services.CartService;
import com.starshop.services.CustomerService;
import com.starshop.services.JwtService;
import com.starshop.services.ProductLineService;
import com.starshop.services.VoucherService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/customer/cart")
public class CustomerCartController {

	@Autowired
	private CartService cartService;

	@Autowired
	private VoucherService voucherService;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private ProductLineService productLineService;

	@Autowired
	private CustomerService customerService;

	@GetMapping
	public String viewCart(Model model, Principal principal,HttpServletRequest request) {
		UUID userId = jwtService.getUserIdFromPrincipal(principal);

		Optional<List<ProductLine>> productLines = cartService.getProductLines(userId);
		if (productLines.isPresent()) {
			model.addAttribute("productLines", productLines.get());
		} else {
			model.addAttribute("productLines", null);
		}

		List<Voucher> discountVouchers = voucherService.getDiscountVoucher();
		List<Voucher> freeShipVouchers = voucherService.getFreeshipVoucher();
		model.addAttribute("discountVouchers", discountVouchers);
		model.addAttribute("freeShipVouchers", freeShipVouchers);

		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
		if(inputFlashMap != null) {
			Double finalPrice = (Double) inputFlashMap.get("finalPrice");
			Double discount = (Double) inputFlashMap.get("discount");
			Double freeShip = (Double) inputFlashMap.get("freeShip");
			
			model.addAttribute("finalPrice", finalPrice != null ? finalPrice : 0);
			model.addAttribute("discount", discount != null ? discount : 0);
			model.addAttribute("freeShip", freeShip != null ? freeShip : 0);
		}

		return "customer/cart";
	}

	@PostMapping("/add/{product-id}")
	public String addToCart(@PathVariable("product-id") Long productId, @RequestParam(defaultValue = "1") int quantity,
			Principal principal) {
		UUID userId = jwtService.getUserIdFromPrincipal(principal);
		cartService.addToCart(userId, productId, quantity);
		return "redirect:/customer/cart";
	}

	@PostMapping("/update-quantity")
	public ResponseEntity<Map<String, Object>> updateProductLineQuantity(
			@RequestParam(value = "action", required = false) String action,
			@RequestParam(value = "quantity", required = false) Integer quantity,
			@RequestParam("productLineId") Long productLineId) {

		int change = 0;
		if ("increase".equals(action)) {
			change = 1;
		} else if ("decrease".equals(action)) {
			change = -1;
		} else if ("set".equals(action) && quantity != null) {
			change = quantity - productLineService.findById(productLineId)
					.orElseThrow(() -> new RuntimeException("ProductLine not found")).getQuantity();
		}

		// Tính toán lại giá sau khi cập nhật số lượng
		ProductLine updatedProductLine = cartService.updateProductLineQuantity(productLineId, change);
		double plTotalPrice = updatedProductLine.getQuantity() * updatedProductLine.getProduct().getDisplayPrice();
		double totalPrice = cartService.calculateProductLinePrice(updatedProductLine.getCart().getId());

		Map<String, Object> response = new HashMap<>();
		response.put("newQuantity", updatedProductLine.getQuantity());
		response.put("plTotalPrice", plTotalPrice);
		response.put("totalPrice", totalPrice);

		return ResponseEntity.ok(response);
	}

	@PostMapping("/apply-voucher")
	public String applyVoucher(Model model, String voucherCode, @RequestParam("totalPrice") int totalPrice,
			Principal principal, RedirectAttributes redirectAttributes) {
		UUID userId = jwtService.getUserIdFromPrincipal(principal);

		redirectAttributes.addFlashAttribute("showModal", true);
		if (voucherCode == null || voucherCode.trim().isEmpty()) {
			redirectAttributes.addFlashAttribute("result", new ViewMessage("danger", "Vui lòng nhập mã voucher"));
			return "redirect:/customer/cart";
		}

		Voucher voucher = voucherService.findByCode(voucherCode);
		if (voucher == null) {
			redirectAttributes.addFlashAttribute("result", new ViewMessage("danger", "Voucher không tồn tại"));
		} else {
			double discountValue = totalPrice * voucher.getDiscountPercent() / 100;
			double discountAmount = Math.min(discountValue, voucher.getMaxDiscountAmount());
			double finalPrice = totalPrice - discountAmount;

			if (discountAmount <= 0) {
				redirectAttributes.addFlashAttribute("result",
						new ViewMessage("danger", "Voucher áp dụng không thành công!"));
			} else {
//				customerService.addVoucherToCustomer(userId, voucherCode);
				redirectAttributes.addFlashAttribute("result",
						new ViewMessage("success", "Voucher áp dụng thành công!"));
				redirectAttributes.addFlashAttribute("showModal", false);

				redirectAttributes.addFlashAttribute("finalPrice", finalPrice);
				if (voucher.isFreeship()) {
					redirectAttributes.addFlashAttribute("freeShip", discountAmount);

				} else
					redirectAttributes.addFlashAttribute("discount", discountAmount);
			}
		}
		return "redirect:/customer/cart";
	}

	@PostMapping("/update/{product-id}")
	public String updateCartItem(@PathVariable("product-id") Long productId, @RequestParam Integer quantity,
			Principal principal) {
		UUID userId = jwtService.getUserIdFromPrincipal(principal);
		cartService.updateCartItem(userId, productId, quantity);
		return "redirect:/cart";
	}

	@RequestMapping("/delete/{productline-id}")
	public String removeFromCart(@PathVariable("productline-id") Long productLineId, Principal principal) {
		UUID userId = jwtService.getUserIdFromPrincipal(principal);
		cartService.removeFromCart(userId, productLineId);
		return "redirect:/customer/cart";
	}

}
