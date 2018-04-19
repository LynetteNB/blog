package com.example.blog.services;

import com.example.blog.models.Post;
import com.example.blog.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepo;

    public PostService(PostRepository postRepo) {
        this.postRepo = postRepo;
    }

    public Post findOne(long id) {
        return postRepo.findById(id);
    }
    public List<Post> findAll() {
        return (List) postRepo.findAll();
    }
    public Post save(Post post) {
        return postRepo.save(post);
    }
    public void deleteById(Long id) {
        Post post = postRepo.findById(id);
        postRepo.delete(post);
    }

}
