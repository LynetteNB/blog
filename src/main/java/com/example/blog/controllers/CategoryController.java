package com.example.blog.controllers;

import com.example.blog.models.Category;
import com.example.blog.models.Post;
import com.example.blog.repositories.CategoryRepository;
import com.example.blog.services.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;

@Controller
public class CategoryController {
    private final PostService postService;
    private final CategoryRepository categoryRepo;

    public CategoryController(PostService postService, CategoryRepository categoryRepo) {
        this.postService = postService;
        this.categoryRepo = categoryRepo;
    }

    @GetMapping("/category/{categoryString}")
    public String showHome(@PathVariable String categoryString, Model model){
        Category category = categoryRepo.findByCategory(categoryString);
        List<Post> posts= category.getPosts();
        Collections.reverse(posts);
        model.addAttribute("posts", posts);
        return "posts/show_category";
    }
}
