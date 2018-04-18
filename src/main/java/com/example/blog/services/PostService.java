package com.example.blog.services;

import com.example.blog.models.Post;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private List<Post> posts;

    public PostService() {
        posts = new ArrayList<>();
        createPosts();
    }
    public Post findOne(long id) {
        return posts.get((int)id - 1);
    }
    public List<Post> findAll() {
        return posts;
    }
    public Post save(Post post) {
        post.setId((long) posts.size() + 1);
        posts.add(post);
        return post;
    }
    private void createPosts() {
        save(new Post("title1", "body1"));
        save(new Post("title2", "body2"));
        save(new Post("title3", "body3"));
        save(new Post("title4", "body4"));
        save(new Post("title5", "body5"));
    }

}
