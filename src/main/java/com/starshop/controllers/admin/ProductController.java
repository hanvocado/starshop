package com.starshop.controllers.admin;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.starshop.models.Category;
import com.starshop.models.Product;
import com.starshop.services.CategoryService;
import com.starshop.services.ProductService;
import com.starshop.utils.Constants;
import com.starshop.utils.FileHandler;
import com.starshop.utils.ViewMessage;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/products")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("")
	public String products(Model model, String status, String search, Integer pageNo, Integer pageSize) {
		
		Page<Product> page = null;
		if (pageNo == null) pageNo = 0;
		if (pageSize == null) pageSize = 6;
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
		return "/admin/products";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);
		return "/admin/add-product";		
	}
	
	@PostMapping("/save")
	public String save(@Valid Product product, Long categoryId, MultipartFile file, RedirectAttributes attributes) {
		try {			
			if (!file.isEmpty()) {	
				String imageName = FileHandler.save(file);
				String oldImage = product.getImage();
				if (imageName != null) {
					if (!oldImage.startsWith("https") && !oldImage.equals(Constants.productImgDefault))
						FileHandler.delete(oldImage);
					
					product.setImage(imageName);
				}
			} else if (product.getImage() == null) {
				product.setImage(Constants.productImgDefault);
			}
			
			product.setCategory(categoryService.findById(categoryId));
            productService.update(product);
            attributes.addFlashAttribute("result", new ViewMessage("success", product.getId() == null ? Constants.createSuccess : Constants.updateSuccess));
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
	
}
