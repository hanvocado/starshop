package com.starshop.controllers.users;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.starshop.entities.ProductLine;
import com.starshop.entities.Voucher;
import com.starshop.models.ViewMessage;
import com.starshop.services.CartService;
import com.starshop.services.JwtService;
import com.starshop.services.ProductLineService;
import com.starshop.services.VoucherService;

@Controller
@RequestMapping("/user/cart")
public class UserCartController {

	@Autowired
	private CartService cartService;

	@Autowired
	private VoucherService voucherService;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private ProductLineService productLineService;

	@GetMapping()
	public String viewCart(Model model, Principal principal) {
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

		return "user/cart";
	}

	@PostMapping("/add/{product-id}")
	public String addToCart(@PathVariable("product-id") Long productId, @RequestParam(defaultValue = "1") int quantity,
			Principal principal) {
		UUID userId = jwtService.getUserIdFromPrincipal(principal);
		cartService.addToCart(userId, productId, quantity);
		return "redirect:/user/cart";
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
	public String applyVoucher(Model model, String voucherCode, int totalPrice, RedirectAttributes redirectAttributes) {
		
		redirectAttributes.addFlashAttribute("showModal", true);
		if (voucherCode == null || voucherCode.trim().isEmpty()) {
			redirectAttributes.addFlashAttribute("result", new ViewMessage("danger", "Vui lòng nhập mã voucher"));
			return "redirect:/user/cart";
		}

		Voucher voucher = voucherService.findByCode(voucherCode);
		if (voucher == null) {
			redirectAttributes.addFlashAttribute("result", new ViewMessage("danger", "Voucher không tồn tại"));
		}

		double discountValue = totalPrice * voucher.getDiscountPercent() / 100;
		double discountAmount = Math.min(discountValue, voucher.getMaxDiscountAmount());
		double finalPrice = totalPrice - discountAmount;

		if (discountAmount <= 0) {
			redirectAttributes.addFlashAttribute("result", new ViewMessage("danger", "Voucher không tồn tại"));
		}

		redirectAttributes.addFlashAttribute("result", new ViewMessage("success", "Voucher áp dụng thành công!"));
		redirectAttributes.addFlashAttribute("showModal", false);
		model.addAttribute("finalPrice", finalPrice);
		model.addAttribute("discountAmount", discountAmount);
		return "redirect:/user/cart";
	}

	@PostMapping("/update/{product-id}")
	public String updateCartItem(@PathVariable("product-id") Long productId, @RequestParam Integer quantity,
			Principal principal) {
		UUID userId = jwtService.getUserIdFromPrincipal(principal);
		cartService.updateCartItem(userId, productId, quantity);
		return "redirect:/cart";
	}

	@PostMapping("/delete/{product-id}")
	public String removeFromCart(@PathVariable("product-id") Long productId, Principal principal) {
		UUID userId = jwtService.getUserIdFromPrincipal(principal);
		cartService.removeFromCart(userId, productId);
		return "redirect:/user/cart";
	}

}
