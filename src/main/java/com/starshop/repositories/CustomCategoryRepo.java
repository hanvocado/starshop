package com.starshop.repositories;
import java.util.List;

import com.starshop.models.Category;

public interface CustomCategoryRepo {
	List<Category> findByName(String name);
	
	List<Category> findDeleted();
	
	List<Category> findInactive();
}
