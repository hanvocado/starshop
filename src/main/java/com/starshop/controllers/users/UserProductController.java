package com.starshop.controllers.users;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.starshop.models.Product;
import com.starshop.models.User;
import com.starshop.services.ProductService;
import com.starshop.services.UserService;

@Controller
@RequestMapping("/user")
public class UserProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;

	@GetMapping("")
	public String publishedProducts(Model model, Integer pageNo, Integer pageSize, String search,
			@RequestParam(required = false) UUID userId) {

		Page<Product> page = null;
		if (pageNo == null)
			pageNo = 0;
		if (pageSize == null)
			pageSize = 21;
		page = productService.getPublishedProductsPagination(pageNo, pageSize, null);

		model.addAttribute("role", "user");
		model.addAttribute("products", page.getContent());
		model.addAttribute("pageNo", page.getNumber());
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("count", page.getTotalElements());
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("isFirst", page.isFirst());
		model.addAttribute("isLast", page.isLast());
		/*
		 * if (!user.isEmpty()) { model.addAttribute("user", user); }
		 */
//		userId = (UUID)1;
		if (userId != null) {
			Optional<User> user = userService.getUserById(userId);
			user.ifPresent(value -> model.addAttribute("user", value));
		}

		return "index";
	}

	@PostMapping("/{userId}/wishlist")
	public String addToWishlist(@PathVariable UUID userId, @RequestParam Long productId) {
		userService.addProductToWishlist(userId, productId);
		return "redirect:/users/" + userId + "/wishlist";
	}

	@GetMapping("/{userId}/wishlist")
	public String getWishlist(@PathVariable UUID userId, Model model) {
		List<Product> wishlist = userService.getWishlist(userId);
		model.addAttribute("wishlist", wishlist);
		return "user/wishlist";
	}

	@GetMapping("/list")
	public String listUsers(Model model) {
		model.addAttribute("users", userService.getAllUsers());
		return "user/list";
	}

	@GetMapping("/add")
	public String showAddUserForm(Model model) {
		model.addAttribute("user", new User());
		return "user/add";
	}

	@PostMapping("/add")
	public String addUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
		userService.addUser(user);
		redirectAttributes.addFlashAttribute("message", "Thêm người dùng thành công!");
		return "redirect:/users";
	}

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
