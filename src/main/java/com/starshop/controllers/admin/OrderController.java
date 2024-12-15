package com.starshop.controllers.admin;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.starshop.entities.Address;
import com.starshop.entities.Order;
import com.starshop.entities.Shipper;
import com.starshop.models.ViewMessage;
import com.starshop.services.*;
import com.starshop.utils.Constants;
import com.starshop.utils.OrderStatus;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private ShipperService shipperService;

	@RequestMapping()
	public String orders(Model model, String status, Long searchId, Integer pageNo, Integer pageSize) {
		if (searchId != null) {
			return "redirect:/admin/orders/details/" + searchId;
		}
		
		OrderStatus orderStatus;
		if (pageNo == null)
			pageNo = 0;
		if (pageSize == null)
			pageSize = 9;
		if (status == null || status.isBlank())
			orderStatus = OrderStatus.PENDING;
		else
			orderStatus = OrderStatus.valueOf(status.toUpperCase());

		if (orderStatus == OrderStatus.PREPARING) {
			List<Shipper> shippers = shipperService.findAllShippers();
			model.addAttribute("shippers", shippers);
		}
		

		Page<Order> orders = orderService.findByStatus(orderStatus, pageNo, pageSize);

		ViewMessage message = (ViewMessage) model.asMap().get("result");
		model.addAttribute("message", message);

		model.addAttribute("orders", orders.getContent());
		model.addAttribute("pageNo", orders.getNumber());
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("count", orders.getTotalElements());
		model.addAttribute("totalPages", orders.getTotalPages());
		model.addAttribute("isFirst", orders.isFirst());
		model.addAttribute("isLast", orders.isLast());
		model.addAttribute("status", orderStatus.name().toLowerCase());

		return "admin/orders/orders";
	}

	@GetMapping("/update/{id}/{newStatus}")
	public String update(@PathVariable("id") Long id, @PathVariable("newStatus") String newStatus,
			RedirectAttributes attributes) {
		OrderStatus newOrderStatus = OrderStatus.valueOf(newStatus.toUpperCase());
		try {
			orderService.updateOrderStatus(id, newOrderStatus);
			attributes.addFlashAttribute("result", new ViewMessage("success", Constants.updateSuccess));
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("result", new ViewMessage("danger", Constants.failed));
		}

		return "redirect:/admin/orders?status=" + newOrderStatus.name().toLowerCase();
	}
	
	@PostMapping("/assignshipper")
	public String assignShipper(Long orderId, String shipperId, RedirectAttributes attributes) {
		try {
			Order order = orderService.assignShipper(orderId, UUID.fromString(shipperId));
			if (order != null)
				attributes.addFlashAttribute("result", new ViewMessage("success", Constants.updateSuccess));
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("result", new ViewMessage("danger", Constants.failed));
		}

		return "redirect:/admin/orders?status=" + OrderStatus.READY_FOR_SHIP.name().toLowerCase();
	}
	
	@GetMapping("/details/{id}")
	public String details(@PathVariable("id") Long orderId, String status, Model model, RedirectAttributes attributes, HttpServletRequest request) {
		Order order = orderService.findByOrderId(orderId);
		if (order != null) {
			Address address = orderService.getAddress(order);
			model.addAttribute("order", order);
			model.addAttribute("address", address);
			return "admin/orders/order-details";			
		} else {
			attributes.addFlashAttribute("result", new ViewMessage("danger", Constants.notFound));
			String referer = request.getHeader("Referer"); 
			return "redirect:" + referer;
		}
	}
}
