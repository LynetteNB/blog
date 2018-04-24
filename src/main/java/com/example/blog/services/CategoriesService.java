package com.example.blog.services;

import com.example.blog.models.Category;
import com.example.blog.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CategoriesService {

    private final CategoryRepository categoryRepo;

    public CategoriesService(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }
    public List<Category> makeCategoryList(String categoriesString) {
        List<Category> postCategories = new ArrayList<>();
        for(String category : categoriesString.split(",")) {
            category = category.toLowerCase().trim();
            if(categoryRepo.findByCategory(category) == null){
                Category newCategory = new Category();
                newCategory.setCategory(category);
                newCategory = categoryRepo.save(newCategory);
                postCategories.add(newCategory);
            } else {
                postCategories.add(categoryRepo.findByCategory(category));
            }
        };
        return postCategories;
    }
    public String makeCategoryString(List<Category> categoryList) {
        String categories = "";
//        for(Category category : categoryList) {
//            categories += category.getCategory() + ", ";
//        }
        Iterator<Category> category = categoryList.iterator();
        if (category.hasNext()) {
            categories += category.next().getCategory();
        }
        while (category.hasNext()) {
            categories += ", " + category.next().getCategory();
        }
        return categories;
    }
}
