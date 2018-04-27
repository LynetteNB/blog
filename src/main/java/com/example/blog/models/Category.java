package com.example.blog.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="categories")
public class Category {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable=false)
    private String category;

    @ManyToMany(mappedBy = "categories")
    private List<Post> posts;

    public Category() {
    }

    public Category(String category, List<Post> posts) {
        this.category = category;
        this.posts = posts;
    }
    public Category(String category, List<Post> posts, long id) {
        this.category = category;
        this.posts = posts;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
