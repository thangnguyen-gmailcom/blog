package model;

public class User {
    private int id;
    private String userName;
    private String email;
    private String password;
    private String imageUser;
    private int isAdmin;
    private int poster;

    public User(int id ) {
        this.id = id;
    }

    public User(String userName,  String imageUser) {
        this.userName = userName;
        this.imageUser = imageUser;
    }

    public User(int id, String userName, String email, String password, String imageUser, int isAdmin) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.imageUser = imageUser;
        this.isAdmin = isAdmin;
    }

    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public String getImageUser() {
        return imageUser;
    }

    public void setImageUser(String imageUser) {
        this.imageUser = imageUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }
}
