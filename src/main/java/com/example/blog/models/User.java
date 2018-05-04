package com.example.blog.models;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

import static javax.persistence.TemporalType.DATE;

@Entity
@Table(name="users")
public class User {
    @Id@GeneratedValue
    private long id;

    @Column(nullable=false, unique=true)
    @NotBlank(message = "Username field cannot be blank.")
    @Size(max=50, message = "Username cannot be more than 50 characters.")
    private String username;

    @Column(nullable=false, unique=true)
    @NotBlank(message = "Email field cannot be blank.")
    @Size(max=150, message = "Email cannot be more than 150 characters.")
    private String email;

    @Column(nullable=false)
    @NotBlank(message = "Password field cannot be blank.")
    @Size(max=150, message = "Password cannot be more than 150 characters.")
    private String password;

    @Column
    @Size(max=150, message = "First name cannot be more than 150 characters.")
    private String firstName;

    @Column
    @Size(max=150, message = "Last name cannot be more than 150 characters.")
    private String lastName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Post> posts;

    public User() {
    }

    public User(User copy) {
        id = copy.id; // This line is SUPER important! Many things won't work if it's absent
        email = copy.email;
        username = copy.username;
        password = copy.password;
    }

    public User(String username, String email, String password, String firstName, String lastName, List<Post> posts, long id) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.posts = posts;
        this.id = id;
    }

    public User(String username, String email, String password, List<Post> posts) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.posts = posts;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
