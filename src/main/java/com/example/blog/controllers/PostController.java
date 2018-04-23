package com.example.blog.controllers;

import com.example.blog.models.Post;
import com.example.blog.models.User;
import com.example.blog.repositories.UserRepository;
import com.example.blog.services.CategoriesService;
import com.example.blog.services.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
public class PostController {
    private final PostService postService;
    private final UserRepository userRepo;
    private final CategoriesService categoriesService;

    public PostController(PostService postService, UserRepository userRepo, CategoriesService categoriesService) {
        this.postService = postService;
        this.userRepo = userRepo;
        this.categoriesService = categoriesService;
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
    public String createPost(@ModelAttribute Post post, @RequestParam String categoriesString){
        User user = userRepo.findOne( 1L);
        post.setUser(user);
        post.setCreatedAt(postService.today());
        post.setCategories(categoriesService.makeCategoryList(categoriesString));
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
