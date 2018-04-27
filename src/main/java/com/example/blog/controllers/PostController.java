package com.example.blog.controllers;

import com.example.blog.models.Comment;
import com.example.blog.models.Post;
import com.example.blog.models.User;
import com.example.blog.repositories.CommentRepository;
import com.example.blog.repositories.PostRepository;
import com.example.blog.repositories.UserRepository;
import com.example.blog.services.CategoriesService;
import com.example.blog.services.CommentService;
import com.example.blog.services.PostService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@Controller
public class PostController {
    private final PostService postService;
    private final CategoriesService categoriesService;
    private final CommentRepository commentRepo;
    private final CommentService commentService;
    private final PostRepository postRepo;

    public PostController(PostService postService, CategoriesService categoriesService, CommentRepository commentRepo, CommentService commentService, PostRepository postRepo) {
        this.postService = postService;
        this.categoriesService = categoriesService;
        this.commentRepo = commentRepo;
        this.commentService = commentService;
        this.postRepo = postRepo;
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
        List<Comment> comments = commentRepo.findByPostId(post.getId());
        Comment comment = new Comment();
        model.addAttribute("post", post);
        model.addAttribute("comments", comments);
        model.addAttribute("comment", comment);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String viewCreatePost(Model model){
        Post post = new Post();
        model.addAttribute(post);
        return "posts/create_post";
    }

    @PostMapping("/posts/create")
    public String createPost(@Valid Post post, Errors errors, Model model, @RequestParam String categoriesString, @RequestParam(name = "file") MultipartFile uploadedFile){
        if (errors.hasErrors()) {
            model.addAttribute("errors", errors);
            model.addAttribute("post", post);
            model.addAttribute("categoriesString", categoriesString);
            return "posts/create_post";
        }
        post.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        post.setCreatedAt(postService.today());
        if(!categoriesString.trim().equals("")){
            post.setCategories(categoriesService.makeCategoryList(categoriesString));
        }
        if(uploadedFile != null) {
            post.setImgPath(postService.saveFile(uploadedFile, model));
        }
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
    public String editPost(@Valid Post post, Errors errors, Model model, @RequestParam String categoriesString, @RequestParam(name = "file") MultipartFile uploadedFile){
        if (errors.hasErrors()) {
            model.addAttribute("errors", errors);
            model.addAttribute("post", post);
            model.addAttribute("categoriesString", categoriesString);
            return "posts/edit_post";
        }
        post.setCategories(categoriesService.makeCategoryList(categoriesString));
        if(uploadedFile != null) {
            post.setImgPath(postService.saveFile(uploadedFile, model));
        }
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
