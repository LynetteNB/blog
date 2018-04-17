package com.example.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String showPosts(){
        return "posts index page";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String viewIndividualPost(@PathVariable long id){
        return "viewing post # " + id;
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
