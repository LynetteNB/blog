package com.example.blog.controllers;

import com.example.blog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    @GetMapping("/posts")
    public String showPosts(Model model){
        List<Post> posts = new ArrayList<>();
        posts.add(new Post("title1", "body1"));
        posts.add(new Post("title2", "body2"));
        posts.add(new Post("title3", "body3"));
        posts.add(new Post("title4", "body4"));
        posts.add(new Post("title5", "body5"));
        model.addAttribute("posts", posts);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String viewIndividualPost(@PathVariable long id, Model model){
        Post post = new Post(id, "title of blog post # " + id, "body of blog post # " + id);
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String viewCreatePost(){

        return "view the form for creating a post";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createPost(@RequestParam String name){

        return "created a new post" + name;
    }
}
