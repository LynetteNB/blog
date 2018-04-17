package com.example.blog;

public class Post {
    private String title;
    private String body;
    private Long id;

    public Post () {}
    public Post(Long id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }
    public Post(String title, String body) {
        this.title = title;
        this.body = body;
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
}
