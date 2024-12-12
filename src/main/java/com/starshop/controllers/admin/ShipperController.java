package com.starshop.controllers.admin;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.starshop.entities.Shipper;
import com.starshop.models.ViewMessage;
import com.starshop.services.ShipperService;
import com.starshop.utils.Constants;

@Controller
@RequestMapping("/admin/shippers")
public class ShipperController {
	@Autowired
	ShipperService shipperService;
	
	@RequestMapping
	public String index(Model model) {
		List<Shipper> shippers = shipperService.findAllShippers();
		model.addAttribute("shippers", shippers);
		ViewMessage message  = (ViewMessage) model.asMap().get("result");
        model.addAttribute("message", message);
		return "admin/shippers";
	}
	
	@RequestMapping("/change-status/{id}")
    public String changeStatus(@PathVariable("id") String shipperId, RedirectAttributes attributes) {
        shipperService.changeStatus(UUID.fromString(shipperId));
        attributes.addFlashAttribute("result", new ViewMessage("success", Constants.updateSuccess));
        return "redirect:/admin/shippers";
    }
}
