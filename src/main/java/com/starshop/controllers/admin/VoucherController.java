package com.starshop.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.starshop.entities.Voucher;
import com.starshop.services.VoucherService;
import com.starshop.utils.Constants;
import com.starshop.utils.ViewMessage;

import jakarta.validation.Valid;

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
	
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("type", "Tạo");
		return "/admin/add-voucher";
	}
	
	@GetMapping("/update/{code}")
	public String update(Model model, @PathVariable("code") String code, RedirectAttributes attributes) {
		Voucher voucher = voucherService.findByCode(code);
		if (voucher != null) {
			model.addAttribute("voucher", voucher);
			model.addAttribute("type", "Cập nhật");
			return "/admin/add-voucher";
		}
		attributes.addFlashAttribute("result", new ViewMessage("danger", Constants.invalidVoucher));
		return "redirect:/admin/vouchers";
	}

	@PostMapping("/save")
    public String save(@Valid Voucher voucher, RedirectAttributes attributes){
        try {
            voucherService.save(voucher);
            attributes.addFlashAttribute("result", new ViewMessage("success", Constants.success));
        }catch (DataIntegrityViolationException e){
            e.printStackTrace();
            attributes.addFlashAttribute("result", new ViewMessage("danger", "Mã này đã tồn tại"));
        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("result", new ViewMessage("danger", Constants.failed));
        }
        return "redirect:/admin/vouchers";
    }
	
	@RequestMapping("/delete/{code}")
    public String delete(@PathVariable("code") String code, RedirectAttributes attributes){
        try {
            voucherService.delete(code);
            attributes.addFlashAttribute("result", new ViewMessage("success", Constants.deleteSuccess));
        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("result", new ViewMessage("danger", Constants.failed));
        }
        return "redirect:/admin/vouchers";
    }
}
