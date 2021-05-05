package model;

import java.time.LocalDateTime;

public class Comment {
    private int id;
    private String content;
    private LocalDateTime dateComment;
    private String name;
    private String gmail;
    private Post post;

    public Comment(int id, String content, LocalDateTime dateComment, String name, String gmail, Post post) {
        this.id = id;
        this.content = content;
        this.dateComment = dateComment;
        this.name = name;
        this.gmail = gmail;
        this.post = post;
    }

    public Comment() {
    }

    public Comment(String content, LocalDateTime dateComment, String name, String gmail) {
        this.content = content;
        this.dateComment = dateComment;
        this.name = name;
        this.gmail = gmail;
    }

    public Comment(String content, String name, String gmail, Post post) {
        this.content = content;
        this.name = name;
        this.gmail = gmail;
        this.post = post;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDateComment() {
        return dateComment;
    }

    public void setDateComment(LocalDateTime dateComment) {
        this.dateComment = dateComment;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }
}
