package com.starshop.controllers.customer;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.starshop.entities.User;
import com.starshop.models.ViewMessage;
import com.starshop.services.UserService;
import com.starshop.utils.Constants;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private UserService userService;

	@GetMapping("/edit/{id}")
	public String showEditUserForm(@PathVariable UUID id, Model model) {
		model.addAttribute("user", userService.getUserById(id)
				.orElseThrow(() -> new IllegalArgumentException("Không tìm thấy người dùng")));
		return "user/edit";
	}

	@PostMapping("/edit/{id}")
	public String updateUser(@PathVariable UUID id, @ModelAttribute User user, RedirectAttributes redirectAttributes) {
		userService.updateUser(id, user);
		redirectAttributes.addFlashAttribute("message", "Cập nhật người dùng thành công!");
		return "redirect:/user";
	}

	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
		userService.deleteUser(id);
		redirectAttributes.addFlashAttribute("message", "Xóa người dùng thành công!");
		return "redirect:/user";
	}
}
