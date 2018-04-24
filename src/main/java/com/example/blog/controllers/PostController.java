package com.example.blog.controllers;

import com.example.blog.models.Post;
import com.example.blog.models.User;
import com.example.blog.repositories.UserRepository;
import com.example.blog.services.CategoriesService;
import com.example.blog.services.PostService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@Controller
public class PostController {
    private final PostService postService;
    private final CategoriesService categoriesService;

    public PostController(PostService postService, CategoriesService categoriesService) {
        this.postService = postService;
        this.categoriesService = categoriesService;
    }

    @GetMapping("/")
    public String showHome(Model model){
        List<Post> posts = postService.findAll();
        Collections.reverse(posts);
        model.addAttribute("posts", posts);
        return "posts/index";
    }

    @GetMapping("/posts")
    public String showPosts(Model model){
        List<Post> posts = postService.findAll();
        Collections.reverse(posts);
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
    public String createPost(@Valid Post post, Errors errors, Model model, @RequestParam String categoriesString){
        if (errors.hasErrors()) {
            model.addAttribute("errors", errors);
            model.addAttribute("post", post);
            model.addAttribute("categoriesString", categoriesString);
            return "posts/create_post";
        }
        post.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        post.setCreatedAt(postService.today());
        post.setCategories(categoriesService.makeCategoryList(categoriesString));
        Post newPost = postService.save(post);
        return "redirect:/posts/" + newPost.getId();
    }
    @GetMapping("/posts/{id}/edit")
    public String viewEditPost(@PathVariable long id, Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Post post = postService.findOne(id);
        if(user.getId() == post.getUser().getId()) {
            model.addAttribute("categoriesString", categoriesService.makeCategoryString(post.getCategories()));
            model.addAttribute(post);
            return "posts/edit_post";
        }
        return "redirect:/posts";
    }

    @PostMapping("/posts/edit")
    public String editPost(@Valid Post post, Errors errors, Model model, @RequestParam String categoriesString){
        if (errors.hasErrors()) {
            model.addAttribute("errors", errors);
            model.addAttribute("post", post);
            model.addAttribute("categoriesString", categoriesString);
            return "posts/edit_post";
        }
        post.setCategories(categoriesService.makeCategoryList(categoriesString));
        postService.save(post);
        return "redirect:/posts/" + post.getId();
    }

    @PostMapping("/posts/delete")
    public String deletePost(@RequestParam Long id){
        postService.deleteById(id);
//        System.out.println("Deleted post # " + id);
        return "redirect:/posts";
    }
}
