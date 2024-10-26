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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.starshop.services.CategoryService;
import com.starshop.models.Category;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/admin/categories")
    public String categories(Model model, Principal principal){
		/*
		 * if(principal == null){ return "redirect:/login"; }
		 */
        List<Category> categories = categoryService.findAll();
        
        List<Category> enabledList = categories.stream()
		                .filter(category -> category.isActivated())
		                .collect(Collectors.toList());
        
        List<Category> deletedList = categories.stream()
                .filter(category -> category.isDeleted())
                .collect(Collectors.toList());
        
        List<Category> disabledList = categories.stream()
                .filter(category -> !category.isActivated())
                .collect(Collectors.toList());
        
        model.addAttribute("categories", categories);
        model.addAttribute("enabled-categories", enabledList);
        model.addAttribute("disabled-categories", disabledList);
        model.addAttribute("deleted-categories", deletedList);
        model.addAttribute("count", categories.size());
        model.addAttribute("categoryNew", new Category());
        
        return "/admin/categories";
    }

    @PostMapping("/admin/add-category")
    public String add(@ModelAttribute("categoryNew") Category category, RedirectAttributes attributes){
        try {
            categoryService.add(category);
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

    @RequestMapping(value = "/admin/findById", method = {RequestMethod.PUT, RequestMethod.GET})
    @ResponseBody
    public Category findById(Long id){
        return categoryService.findById(id);
    }

    @GetMapping("/admin/update-category")
    public String update(Category category, RedirectAttributes attributes){
        try {
            categoryService.update(category);
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

    @RequestMapping(value = "/admin/delete-category", method = {RequestMethod.PUT, RequestMethod.GET})
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

    @RequestMapping(value = "/admin/enable-category", method = {RequestMethod.PUT, RequestMethod.GET})
    public String enable(Long id, RedirectAttributes attributes){
        try {
            categoryService.enableById(id);
            attributes.addFlashAttribute("success", "Enabled successfully");
        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("failed", "Failed to enabled");
        }
        return "redirect:/admin/categories";
    }





}