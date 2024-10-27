package com.starshop.controllers.admin;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.starshop.services.CategoryService;
import com.starshop.models.Category;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value="/admin/categories")
    public String categories(String search, Model model){
    	List<Category> categories;
    	if(search != null && !search.isBlank())
    		categories = categoryService.findByName(search);
		else
			categories = categoryService.findAll();
        
        model.addAttribute("categories", categories);
        model.addAttribute("count", categories.size());
        model.addAttribute("search", search);
        
        return "/admin/categories";
    }

    @PostMapping("/admin/add-category")
    public String add(String name, boolean isPublished, RedirectAttributes attributes){
        try {
            categoryService.add(new Category(name, isPublished));
            attributes.addFlashAttribute("success", "Added successfully");
        }catch (DataIntegrityViolationException e){
            e.printStackTrace();
            attributes.addFlashAttribute("failed", "Failed to add because duplicate name");
        }
        catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("failed", "Error server");
        }
        return "redirect:/admin/categories";

    }

    @PostMapping("/admin/update-category")
    public String update(Long id, String name, boolean isPublished, RedirectAttributes attributes){
        try {
            categoryService.update(new Category(id, name, isPublished));
            attributes.addFlashAttribute("success","Updated successfully");
        }catch (DataIntegrityViolationException e){
            e.printStackTrace();
            attributes.addFlashAttribute("failed", "Failed to update because duplicate name");
        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("failed", "Error server");
        }
        return "redirect:/admin/categories";
    }

    @PostMapping("/admin/delete-category")
    public String delete(Long id, RedirectAttributes attributes){
        try {
            categoryService.deleteById(id);
            attributes.addFlashAttribute("success", "Deleted successfully");
        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("failed", "Failed to deleted");
        }
        return "redirect:/admin/categories";
    }

    @RequestMapping(value = "/admin/publish-category", method = {RequestMethod.PUT, RequestMethod.GET})
    public String enable(Long id, RedirectAttributes attributes){
        try {
            categoryService.publishById(id);
            attributes.addFlashAttribute("success", "Enabled successfully");
        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("failed", "Failed to enabled");
        }
        return "redirect:/admin/categories";
    }





}