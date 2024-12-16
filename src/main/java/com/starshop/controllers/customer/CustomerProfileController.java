package com.starshop.controllers.customer;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.starshop.entities.Address;
import com.starshop.entities.Customer;
import com.starshop.entities.User;
import com.starshop.models.ViewMessage;
import com.starshop.services.AddressService;
import com.starshop.services.CustomerService;
import com.starshop.services.JwtService;
import com.starshop.utils.Constants;

@Controller
@RequestMapping("/customer/account")
public class CustomerProfileController {

	@Autowired
	private JwtService jwtService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private AddressService addressService;

	@GetMapping("/profile")
	public String customerProfile(Model model, Principal principal) {
		User user = jwtService.getUserFromPrincipal(principal);
		Customer customer = jwtService.getCustomerFromPrincipal(principal);
		Optional<Address> optionalAddress = customerService.getDefaultAddress(customer);
		Address defaultAddress = optionalAddress.orElse(null);
		model.addAttribute("defaultAddress", defaultAddress);
		model.addAttribute("customer", customer);
		model.addAttribute("user", user);
		return "customer/profile";
	}

	@GetMapping("/addresses")
	public String getAddress(Principal principal, Model model) {
		User user = jwtService.getUserFromPrincipal(principal);
		model.addAttribute("user", user);

		ViewMessage message = (ViewMessage) model.asMap().get("result");
		model.addAttribute("message", message);

		Customer customer = jwtService.getCustomerFromPrincipal(principal);
		Optional<Address> defaultAddress = customerService.getDefaultAddress(customer);
		List<Address> otherAddresses = customerService.getOtherAddresses(customer);
		model.addAttribute("defaultAddress", defaultAddress);
		model.addAttribute("otherAddresses", otherAddresses);
		return "customer/address";
	}

	@PostMapping("/addresses/new")
	public String addNewAddress(@ModelAttribute("address") Address address, Principal principal,
			RedirectAttributes redirectAttributes, BindingResult result) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("result", new ViewMessage("danger", "Thêm địa chỉ không thành công"));
		}
		Customer customer = jwtService.getCustomerFromPrincipal(principal);
		address.setCustomer(customer);
		addressService.save(address);
		redirectAttributes.addFlashAttribute("result", new ViewMessage("success", "Thêm địa chỉ thành công"));

		return "redirect:/customer/account/addresses";
	}

	@PostMapping("/addresses/set-default")
	public String setDefaultAddress(@ModelAttribute("addressId") Long addressId, Principal principal,
			RedirectAttributes redirectAttributes) {
		Customer customer = getCustomer(principal);

		try {
			addressService.setDefaultAddress(customer, addressId);
			redirectAttributes.addFlashAttribute("successMessage", "Đặt địa chỉ mặc định thành công.");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Không thể đặt địa chỉ mặc định.");
		}

		return "redirect:/customer/account/addresses";
	}

	@PostMapping("/addresses/update")
	public String updateAddress(@ModelAttribute Address address, Principal principal,
			RedirectAttributes redirectAttributes) {
		try {
			Customer customer = jwtService.getCustomerFromPrincipal(principal);

			Address existingAddress = addressService.getAddressByIdAndCustomer(address.getId(), customer);
			if (existingAddress != null) {
				existingAddress.setHouseNumber(address.getHouseNumber());
				existingAddress.setStreet(address.getStreet());
				existingAddress.setWard(address.getWard());
				existingAddress.setDistrict(address.getDistrict());
				existingAddress.setCity(address.getCity());

				addressService.save(existingAddress);
				redirectAttributes.addFlashAttribute("result",
						new ViewMessage("success", "Cập nhật địa chỉ thành công."));
			} else {
				redirectAttributes.addFlashAttribute("result",
						new ViewMessage("danger", "Không thể cập nhật địa chỉ."));
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("result", new ViewMessage("danger", "Có lỗi xảy ra"));
		}
		return "redirect:/customer/account/addresses";
	}

	@PostMapping("/addresses/delete")
	public String deleteAddress(@RequestParam("id") Long addressId, Principal principal,
			RedirectAttributes redirectAttributes) {
		try {
			Customer customer = getCustomer(principal);
			addressService.deleteAddress(addressId, customer);
			redirectAttributes.addFlashAttribute("result", new ViewMessage("success", "Xóa địa chỉ thành công."));
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("result", new ViewMessage("danger", "Không thể xóa địa chỉ."));
		}
		return "redirect:/customer/account/addresses";
	}

	public Customer getCustomer(Principal principal) {
		return jwtService.getCustomerFromPrincipal(principal);
	}
}
