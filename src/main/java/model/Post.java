package model;

import java.time.LocalDateTime;
import java.util.List;

public class Post {
    private int id;
    private String title;
    private String shortContent;
    private String fullContent;
    private LocalDateTime publishDate;
    private String image;
    private int views;
    private Category category;
    private List<Tag> tags = null;

    public Post(){}

    public Post(int id) {
        this.id = id;
    }

    public Post(int id, String title, String shortContent, String fullContent, LocalDateTime publishDate, String image, int views, Category category) {
        this.id = id;
        this.title = title;
        this.shortContent = shortContent;
        this.fullContent = fullContent;
        this.publishDate = publishDate;
        this.image = image;
        this.views = views;
        this.category = category;
    }

    public Post(int id, String title, String shortContent, String fullContent, String image) {
        this.id = id;
        this.title = title;
        this.shortContent = shortContent;
        this.fullContent = fullContent;
        this.image = image;
    }

    public Post(int id, String title, String shortContent, String fullContent, String image, Category category) {
        this.id = id;
        this.title = title;
        this.shortContent = shortContent;
        this.fullContent = fullContent;
        this.image = image;
        this.category = category;
    }

    public Post(int id, String title, String shortContent, String fullContent, String image, Category category, List<Tag> tags) {
        this(id, title, shortContent, fullContent, image, category);
        this.setTags(tags);
    }

    public Post(String title, String shortContent, String fullContent, String image, Category category) {
        this.title = title;
        this.shortContent = shortContent;
        this.fullContent = fullContent;
        this.image = image;
        this.category = category;
    }

    public Post(String title, String shortContent, String fullContent, String image, Category category, List<Tag> tags) {
        this(title, shortContent, fullContent, image, category);
        this.tags = tags;
    }


    public Post(int id, String title,  String shortContent,String fullContent, LocalDateTime publishDate, String image, Category category) {
        this.id = id;
        this.title = title;
        this.shortContent = shortContent;
        this.fullContent = fullContent;
        this.publishDate = publishDate;
        this.image = image;
        this.category = category;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFullContent() {
        return fullContent;
    }

    public void setFullContent(String fullContent) {
        this.fullContent = fullContent;
    }

    public String getShortContent() {
        return shortContent;
    }

    public void setShortContent(String shortContent) {
        this.shortContent = shortContent;
    }

    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDateTime publishDate) {
        this.publishDate = publishDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
