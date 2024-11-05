package com.starshop.controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.starshop.entities.Category;
import com.starshop.entities.Product;
import com.starshop.services.CategoryService;
import com.starshop.services.ProductService;
import com.starshop.utils.*;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/products")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping()
	public String products(Model model, String status, String search, Integer pageNo, Integer pageSize) {
		
		Page<Product> page = null;
		if (pageNo == null) pageNo = 0;
		if (pageSize == null) pageSize = 9;
		if (status == null || status.isBlank() || status.contentEquals("all")) {
			page = productService.getProductsPagination(pageNo, pageSize, search);
		} else if (status.contentEquals("published")) {
			page = productService.getPublishedProductsPagination(pageNo, pageSize, search);
		} else
			page = productService.getUnpublishedProductsPagination(pageNo, pageSize, search);
	
		model.addAttribute("products", page.getContent());
		model.addAttribute("pageNo", page.getNumber());
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("count", page.getTotalElements());
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("isFirst", page.isFirst());
		model.addAttribute("isLast", page.isLast());
		model.addAttribute("status", status);
		
		ViewMessage message  = (ViewMessage) model.asMap().get("result");
        model.addAttribute("message", message);
		return "/admin/products";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);
		return "/admin/add-product";		
	}
	
	@PostMapping("/save")
	public String save(@Valid Product product, @RequestParam(value = "categoryIds", required = false) List<Long> categoryIds, MultipartFile file, RedirectAttributes attributes) {
		try {			
			if (!file.isEmpty()) {	
				String imageName = FileHandler.save(file);
				String oldImage = product.getImage();
				if (imageName != null) {
					if (!oldImage.startsWith("https") && !oldImage.equals(Constants.productImgDefault))
						FileHandler.delete(oldImage);
					
					product.setImage(imageName);
				}
			} else if (product.getImage() == null || product.getImage().isBlank()) {
				product.setImage(Constants.productImgDefault);
			}
			
            productService.save(product, categoryIds);
            attributes.addFlashAttribute("result", new ViewMessage("success", Constants.success));
        }catch (DataIntegrityViolationException e){
            e.printStackTrace();
            attributes.addFlashAttribute("result", new ViewMessage("danger", Constants.duplicateName));
        }
        catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("result", new ViewMessage("danger", Constants.failed));
        }
        return "redirect:/admin/products";
	}
	
	@GetMapping("/update/{id}")
	public String update(Model model, @PathVariable("id") Long id) {
		Product product = productService.getById(id);
		if (product == null)
	        return "redirect:/admin/products";

		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);
		model.addAttribute("product", product);
		return "/admin/update-product";		
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(Model model, @PathVariable("id") Long id, RedirectAttributes attributes) {
		try {
			productService.deleteById(id);
            attributes.addFlashAttribute("result", new ViewMessage("success", Constants.deleteSuccess));
		} catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("result", new ViewMessage("danger", Constants.failed));
        }
        return "redirect:/admin/products";
	}
}
