package com.example.blog.controllers;

import com.example.blog.models.Category;
import com.example.blog.models.Post;
import com.example.blog.models.User;
import com.example.blog.repositories.CategoryRepository;
import com.example.blog.repositories.UserRepository;
import com.example.blog.services.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class PostController {
    private final PostService postService;
    private final UserRepository userRepo;
    private final CategoryRepository categoryRepo;

    public PostController(PostService postService, UserRepository userRepo, CategoryRepository categoryRepo) {
        this.postService = postService;
        this.userRepo = userRepo;
        this.categoryRepo = categoryRepo;
    }

    @GetMapping("/posts")
    public String showPosts(Model model){
        List<Post> posts = postService.findAll();
        model.addAttribute("posts", posts);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String viewIndividualPost(@PathVariable long id, Model model){
        Post post = postService.findOne(id);
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String viewCreatePost(Model model){
        Post post = new Post();
        model.addAttribute(post);
        return "posts/create_post";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post){
        User user = userRepo.findOne( 1L);
        post.setUser(user);
        post.setCreatedAt(postService.today());

//        List<Category> postCategories = new ArrayList<>();
//        for(String category : categories.split(",")) {
//            category = category.toLowerCase().trim();
//            System.out.println(category + " ");
//            if(categoryRepo.findByCategory(category) == null){
//                Category newCategory = new Category();
//                newCategory.setCategory(category);
//                newCategory = categoryRepo.save(newCategory);
//                postCategories.add(newCategory);
//            } else {
//                postCategories.add(categoryRepo.findByName(category));
//            }
//        };
//        post.setCategories(postCategories);
        Post newPost = postService.save(post);
        return "redirect:/posts/" + newPost.getId();
    }
    @GetMapping("/posts/{id}/edit")
    public String viewEditPost(@PathVariable long id, Model model){
        Post post = postService.findOne(id);
        model.addAttribute(post);
        return "posts/edit_post";
    }

    @PostMapping("/posts/edit")
    public String editPost(@ModelAttribute Post post){
        postService.save(post);
        return "redirect:/posts/" + post.getId();
    }

    @PostMapping("/posts/delete")
    public String deletePost(@RequestParam Long id){
        postService.deleteById(id);
        return "redirect:/posts";
    }
}
