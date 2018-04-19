package com.example.blog.controllers;

import com.example.blog.models.Post;
import com.example.blog.services.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
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
        postService.save(post);
        return "redirect:/posts";
    }
    @GetMapping("/posts/{id}/edit")
    public String viewEditPost(@PathVariable long id, Model model){
        Post post = postService.findOne(id);
        model.addAttribute(post);
        return "posts/edit_post";
    }

    @PostMapping("/posts/edit")
    public String editPost(@ModelAttribute Post post){
        System.out.println(post.getId());
        return "redirect:/posts";
    }
}
