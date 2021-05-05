package model;

public class Category {
    private int id ;
    private String categoryName;
    private String isDelete;

    public Category(int id) {
        this.id = id;
    }

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category(int id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public Category(int id, String categoryName, String isDelete) {
        this.id = id;
        this.categoryName = categoryName;
        this.isDelete = isDelete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
