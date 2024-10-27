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
        
        ViewMessage message  = (ViewMessage) model.asMap().get("result");
        model.addAttribute("message", message);
        
        return "/admin/categories";
    }

    @PostMapping("/admin/add-category")
    public String add(String name, boolean isPublished, RedirectAttributes attributes){
        try {
            categoryService.add(new Category(name, isPublished));
            attributes.addFlashAttribute("result", new ViewMessage("success", Constants.createSuccess));
        }catch (DataIntegrityViolationException e){
            e.printStackTrace();
            attributes.addFlashAttribute("result", new ViewMessage("danger", Constants.duplicateName));
        }
        catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("result", new ViewMessage("danger", Constants.failed));
        }
        return "redirect:/admin/categories";
    }

    @PostMapping("/admin/update-category")
    public String update(Long id, String name, boolean isPublished, RedirectAttributes attributes){
        try {
            categoryService.update(new Category(id, name, isPublished));
            attributes.addFlashAttribute("result", new ViewMessage("success", Constants.updateSuccess));
        }catch (DataIntegrityViolationException e){
            e.printStackTrace();
            attributes.addFlashAttribute("result", new ViewMessage("danger", Constants.duplicateName));
        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("result", new ViewMessage("danger", Constants.failed));
        }
        return "redirect:/admin/categories";
    }

    @PostMapping("/admin/delete-category")
    public String delete(Long id, RedirectAttributes attributes){
        try {
            categoryService.deleteById(id);
            new ViewMessage("success", Constants.deleteSuccess);
        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("result", new ViewMessage("danger", Constants.failed));
        }
        return "redirect:/admin/categories";
    }
}