package com.starshop.controllers.admin;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.starshop.entities.Shipper;
import com.starshop.models.AppException;
import com.starshop.models.ViewMessage;
import com.starshop.services.ShipperService;
import com.starshop.utils.Constants;

import jakarta.validation.Valid;

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
		return "admin/shippers/shippers";
	}
	
	@RequestMapping("/change-status/{id}")
    public String changeStatus(@PathVariable("id") String shipperId, RedirectAttributes attributes) {
        shipperService.changeStatus(UUID.fromString(shipperId));
        attributes.addFlashAttribute("result", new ViewMessage("success", Constants.updateSuccess));
        return "redirect:/admin/shippers";
    }
	
	@PostMapping("/add")
	public String add(@ModelAttribute @Valid Shipper shipper, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			FieldError error = result.getFieldError();
			attributes.addFlashAttribute("result", new ViewMessage("danger", error.getField() + " không hợp lệ."));
		} else if (shipperService.isExisted(shipper.getEmail())) {
			attributes.addFlashAttribute("result", new ViewMessage("danger", Constants.emailExisted));
		} else {
			shipperService.add(shipper);
			attributes.addFlashAttribute("result", new ViewMessage("success", Constants.createSuccess));			
		}
		return "redirect:/admin/shippers";
	}
}
