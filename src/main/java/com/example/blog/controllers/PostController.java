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
import org.springframework.web.bind.annotation.*;

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
        User user = null;
        Object isAnonymous = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(isAnonymous != "anonymousUser") {
            user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        model.addAttribute("isAnonymous", isAnonymous);
        model.addAttribute("user", user);
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String viewCreatePost(Model model){
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser") {
            return "redirect:/login";
        }
        Post post = new Post();
        model.addAttribute(post);
        return "posts/create_post";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post, @RequestParam String categoriesString){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(user);
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
    public String editPost(@ModelAttribute Post post, @RequestParam String categoriesString){
        post.setCategories(categoriesService.makeCategoryList(categoriesString));
        postService.save(post);
        return "redirect:/posts/" + post.getId();
    }

    @PostMapping("/posts/delete")
    public String deletePost(@RequestParam Long id){
        postService.deleteById(id);
        return "redirect:/posts";
    }
}
