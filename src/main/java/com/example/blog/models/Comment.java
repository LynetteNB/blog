package com.example.blog.models;


import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="comments")
public class Comment {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    @NotBlank(message = "Comment cannot be blank.")
    private String comment;

    @Column(nullable = false)
    @NotBlank(message = "Name cannot be blank.")
    @Size(max=150, message = "Name be more than 150 characters.")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "Email cannot be blank.")
    @Size(max=200, message = "Email cannot be more than 200 characters.")
    private String email;

    @Column(nullable=false)
    private String createdAt;

    @ManyToOne (optional = false)
    @JoinColumn (name = "post_id")
    private Post post;

    public Comment () {}

    public Comment(String comment, String name, String email, String createdAt, Post post, Long id) {
        this.comment = comment;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.post = post;
        this.id = id;
    }

    public Comment(String comment, String name, String email, String createdAt, Post post) {
        this.comment = comment;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.post = post;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}

