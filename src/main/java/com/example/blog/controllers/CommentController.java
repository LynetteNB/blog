package com.example.blog.controllers;

import com.example.blog.models.Comment;
import com.example.blog.models.Post;
import com.example.blog.repositories.CommentRepository;
import com.example.blog.repositories.PostRepository;
import com.example.blog.services.CommentService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class CommentController {
    private final CommentRepository commentRepo;
    private final CommentService commentService;
    private final PostRepository postRepo;

    public CommentController(CommentRepository commentRepo, CommentService commentService, PostRepository postRepo) {
        this.commentRepo = commentRepo;
        this.commentService = commentService;
        this.postRepo = postRepo;
    }
    @PostMapping("/comment/{commentId}/disable")
    public String disableComment(@PathVariable Long commentId) {
        Comment comment = commentRepo.findOne(commentId);
        comment.disable();
        commentRepo.save(comment);
        Post post = comment.getPost();
        return "redirect:/posts/" + post.getId();
    }

    @PostMapping("/posts/comment")
    public String postComment(@Valid Comment comment, Errors errors, Model model, @RequestParam Long postId) {
        if (errors.hasErrors()) {
            model.addAttribute("errors", errors);
            model.addAttribute("post", postRepo.findById(postId));
            model.addAttribute("comment", comment);
            return "posts/show";
        }
        System.out.println("comment id = " + comment.getId());
        comment.setCreatedAt(commentService.thisMoment());
        comment.setPost(postRepo.findById(postId));
        commentRepo.save(comment);
        return "redirect:/posts/" + postId;
    }
}
