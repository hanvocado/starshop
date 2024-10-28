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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.starshop.models.Category;
import com.starshop.models.Product;
import com.starshop.services.CategoryService;
import com.starshop.services.ProductService;
import com.starshop.utils.Constants;
import com.starshop.utils.ViewMessage;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/product")
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
	public String addProduct(Model model) {
		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);
		return "/admin/add-product";		
	}
	
	@PostMapping("/add")
	public String saveProduct(@Valid Product product, boolean isPublished, Long categoryId, MultipartFile file, RedirectAttributes attributes) {
		try {
			
			if (!file.isEmpty()) {	
				String imageName = file.getOriginalFilename();
				File saveFile = new ClassPathResource("static/").getFile();
				
				String uploadPath = saveFile.getAbsolutePath() + File.separator + "img_product";
				File uploadDir = new File(uploadPath);
				if (!uploadDir.exists())
					uploadDir.mkdir();
				Path path = Paths.get(uploadPath + File.separator + file.getOriginalFilename());				
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
				product.setImage(imageName);
			} else {
				product.setImage(Constants.productImgDefault);
			}
			
			product.setPublished(isPublished);
			product.setCategory(categoryService.findById(categoryId));
            productService.add(product);
            attributes.addFlashAttribute("result", new ViewMessage("success", Constants.createSuccess));
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
	
	@GetMapping("/update")
	public String updateProduct(Model model, Long id) {
		Product product = productService.getById(id);
		if (product == null)
	        return "redirect:/admin/products";

		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);
		model.addAttribute("product", product);
		return "/admin/update-product";		
	}
}
