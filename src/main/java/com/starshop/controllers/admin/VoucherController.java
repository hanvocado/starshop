package com.starshop.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.starshop.models.Product;
import com.starshop.models.Voucher;
import com.starshop.services.VoucherService;
import com.starshop.utils.ViewMessage;

@Controller
@RequestMapping("/admin/vouchers")
public class VoucherController {
	@Autowired
	private VoucherService voucherService;
	
	@RequestMapping
	public String vouchers(Model model, String search, Integer pageNo, Integer pageSize) {
		if (pageNo == null) pageNo = 0;
		if (pageSize == null) pageSize = 9;
		Page<Voucher> page = voucherService.getAll(pageNo, pageSize, search);

		model.addAttribute("vouchers", page.getContent());
		model.addAttribute("pageNo", page.getNumber());
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("count", page.getTotalElements());
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("isFirst", page.isFirst());
		model.addAttribute("isLast", page.isLast());
		
		ViewMessage message  = (ViewMessage) model.asMap().get("result");
        model.addAttribute("message", message);
		return "/admin/vouchers";
	}

}
