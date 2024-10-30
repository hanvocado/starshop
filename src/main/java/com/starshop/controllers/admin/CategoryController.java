package com.starshop.controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.starshop.services.CategoryService;
import com.starshop.utils.Constants;
import com.starshop.utils.ViewMessage;

import jakarta.validation.Valid;

import com.starshop.models.Category;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping()
    public String categories(String search, Model model){
    	List<Category> categories;
    	if(search != null && !search.isBlank())
    		categories = categoryService.findByName(search);
		else
			categories = categoryService.findAll();
        
        model.addAttribute("categories", categories);
        model.addAttribute("count", categories.size());
        model.addAttribute("search", search);
        
        ViewMessage message  = (ViewMessage) model.asMap().get("result");
        model.addAttribute("message", message);
        
        return "/admin/categories";
    }

    @PostMapping("/save")
    public String save(@Valid Category category, RedirectAttributes attributes){
        try {
            categoryService.update(category);
            attributes.addFlashAttribute("result", new ViewMessage("success", Constants.success));
        }catch (DataIntegrityViolationException e){
            e.printStackTrace();
            attributes.addFlashAttribute("result", new ViewMessage("danger", Constants.duplicateName));
        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("result", new ViewMessage("danger", Constants.failed));
        }
        return "redirect:/admin/categories";
    }

    @PostMapping("/delete")
    public String delete(Long id, RedirectAttributes attributes){
        try {
            categoryService.deleteById(id);
            attributes.addFlashAttribute("result", new ViewMessage("success", Constants.deleteSuccess));
        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("result", new ViewMessage("danger", Constants.failed));
        }
        return "redirect:/admin/categories";
    }
}