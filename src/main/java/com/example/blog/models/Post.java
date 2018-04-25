package com.example.blog.models;


import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="posts")
public class Post {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Post title cannot be blank.")
    @Size(max=150, message = "Title cannot be more than 150 characters.")
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    @NotBlank(message = "Blog post cannot be blank.")
    private String body;

    @Column(nullable=false)
    private String createdAt;

    @ManyToOne (optional = false)
    @JoinColumn (name = "user_id")
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "posts_categories",
            joinColumns = {@JoinColumn(name="post_id")},
            inverseJoinColumns = {@JoinColumn(name="category_id")})
    private List<Category> categories;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<Comment> comments;

    public Post () {}
    public Post(Long id, String title, String body, User user, String createdAt, List<Category> categories, List<Comment> comments) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.user = user;
        this.createdAt = createdAt;
        this.categories = categories;
        this.comments = comments;
    }

    public Post(String title, String body, User user, String createdAt, List<Category> categories, List<Comment> comments) {
        this.title = title;
        this.body = body;
        this.user = user;
        this.createdAt = createdAt;
        this.categories = categories;
        this.comments = comments;
    }
    public Post(String title, String body, User user, String createdAt) {
        this.title = title;
        this.body = body;
        this.user = user;
        this.createdAt = createdAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
