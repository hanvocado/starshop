package com.starshop.controllers.shipper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.starshop.entities.Order;
import com.starshop.entities.Shipper;
import com.starshop.models.ViewMessage;
import com.starshop.services.OrderService;
import com.starshop.utils.Constants;
import com.starshop.utils.OrderStatus;

@Controller
@RequestMapping("/shipper/orders")
public class ShipperOrdersController {
	@Autowired
	private OrderService orderService;
	
	@RequestMapping()
	public String orders(Model model, String status, Integer pageNo, Integer pageSize) {
		OrderStatus orderStatus;
		if (pageNo == null)
			pageNo = 0;
		if (pageSize == null)
			pageSize = 9;
		if (status == null || status.isBlank())
			orderStatus = OrderStatus.READY_FOR_SHIP;
		else
			orderStatus = OrderStatus.valueOf(status.toUpperCase());

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

		return "shipper/orders";
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

		return "redirect:/shipper/orders?status=" + newOrderStatus.name().toLowerCase();
	}
}
