package com.example.blog.models;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="posts")
public class Post {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
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

    public Post () {}
    public Post(Long id, String title, String body, User user, String createdAt, List<Category> categories) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.user = user;
        this.createdAt = createdAt;
        this.categories = categories;
    }

    public Post(String title, String body, User user, String createdAt, List<Category> categories) {
        this.title = title;
        this.body = body;
        this.user = user;
        this.createdAt = createdAt;
        this.categories = categories;
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
}
