package dao;

import model.Category;
import model.Post;
import model.Tag;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PostDAO extends BaseDAO implements IBaseDAO<Post> {
    private final String SELECT_ALL = "SELECT * FROM posts LEFT JOIN categorys ON posts.id_category = categorys.id WHERE posts.is_deleted = 0";

    private final String INSERT_SQL = "INSERT INTO posts(title,short_content,full_content,image,id_category)" +
            "VALUES(?,?,?,?,?)";

    private final String DELETE = "{CALL delete_posts_comments(1,?)}";

    private final String FIND_POST_SQL = "SELECT * FROM posts LEFT JOIN categorys ON posts.id_category = categorys.id WHERE posts.id = ? AND posts.is_deleted = 0;";

    private final String UPDATE_POST_SQL = "update posts set title = ?, short_content = ?, full_content = ?, image = ?, id_category = ?\n" +
            "where id =?;";

    private final String SELECT_TOP3_SQL = "SELECT * FROM posts INNER JOIN categorys ON posts.id_category = categorys.id\n" +
            "WHERE posts.is_deleted = 0 ORDER BY posts.publish_date DESC LIMIT 3;";

    private final String COUNT_POST_SQL = "SELECT COUNT(*) AS count FROM posts WHERE posts.is_deleted = 0";

    private final String PAGINATION_SQL = "WITH x AS( SELECT posts.*,categorys.id AS idCategory,categorys.category_name , ROW_NUMBER() OVER (ORDER BY posts.id) AS rowPosts FROM posts left join categorys on posts.id_category = categorys.id WHERE posts.is_deleted = 0)\n" +
            "SELECT * FROM x WHERE rowPosts BETWEEN ? * ? - (? -1)AND  ? * ? ;";

    private final String LIST_POSTS_IN_CATEGORY = " SELECT * FROM posts INNER JOIN categorys ON posts.id_category = categorys.id\n" +
            "WHERE id_category = ? AND posts.is_deleted = 0";

    private final String LIST_POST_RANDOM = "SELECT * FROM posts INNER JOIN categorys ON posts.id_category = categorys.id WHERE posts.is_deleted = 0 ORDER BY RAND() LIMIT 3 ;";

    private final String UPDATE_VIEWS = "UPDATE posts SET views = views+1 WHERE id = ?";

    private final String TOP3_POST_VIEWS = "SELECT * FROM posts  WHERE posts.is_deleted = 0 ORDER BY views DESC LIMIT 3;";

    @Override
    public List<Post> selectAll() throws SQLException {
        List<Post> listPost = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
             ResultSet rs = statement.executeQuery();
        ) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String shortContent = rs.getString("short_content");
                String fullContent = rs.getString("full_content");
                int views = rs.getInt("views");
                String publishDate = rs.getString("publish_date");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(publishDate, formatter);
                String image = rs.getString("image");
                int idCategory = rs.getInt("id_category");
                String categoryName = rs.getString("category_name");
                Category category = new Category(idCategory, categoryName);
                Post post = new Post(id, title, fullContent, shortContent, dateTime, image, views, category);
                listPost.add(post);
            }

        }

        return listPost;
    }

    @Override
    public void insert(Post post) throws SQLException {

        String sqlUpdatePostTag = "";
        sqlUpdatePostTag += " insert into post_tag ( ";
        sqlUpdatePostTag += "         id_post, ";
        sqlUpdatePostTag += "         id_tag ";
        sqlUpdatePostTag += " ) ";
        sqlUpdatePostTag += " select ";
        sqlUpdatePostTag += " ?, ";
        sqlUpdatePostTag += "         tb.tag_id ";
        sqlUpdatePostTag += " from ( ";
        sqlUpdatePostTag += "         select tags.id tag_id ";
        sqlUpdatePostTag += "         from tags ";
        sqlUpdatePostTag += "         where tags.tag_name in ( ";
        int index = 0;
        for (Tag tag : post.getTags()) {
            index++;
            if (index != 1) {
                sqlUpdatePostTag += ",";
            }
            sqlUpdatePostTag += " ? ";
        }
        sqlUpdatePostTag += "         ) ";
        sqlUpdatePostTag += " ) tb ";
        sqlUpdatePostTag += " left join post_tag tb2 ";
        sqlUpdatePostTag += " on tb2.id_tag = tb.tag_id ";
        sqlUpdatePostTag += " and tb2.id_post = ? ";
        sqlUpdatePostTag += " where tb2.id is null ";

        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
                PreparedStatement stmtUpdatePostTag = connection.prepareStatement(sqlUpdatePostTag);
        ) {
            statement.setString(1, post.getTitle());
            statement.setString(2, post.getShortContent());
            statement.setString(3, post.getFullContent());
            statement.setString(4, post.getImage());
            statement.setInt(5, post.getCategory().getId());
            int cntInsertedPost = statement.executeUpdate();

            // insert tags
            if (post.getTags() != null && cntInsertedPost > 0) {
                (new TagDAO()).insertMany(post.getTags());

                int postId = 0;
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        postId = generatedKeys.getInt(1);
                    }
                }

                // update post tags
                index = 0;
                stmtUpdatePostTag.setInt(++index, postId);
                for (Tag tag : post.getTags()) {
                    stmtUpdatePostTag.setString(++index, tag.getTagName());
                }
                stmtUpdatePostTag.setInt(++index, postId);
                stmtUpdatePostTag.executeUpdate();
            }
        }
    }

    @Override
    public void update(Post post) throws SQLException {
        String sqlUpdatePostTag = "";
        sqlUpdatePostTag += " insert into post_tag ( ";
        sqlUpdatePostTag += "         id_post, ";
        sqlUpdatePostTag += "         id_tag ";
        sqlUpdatePostTag += " ) ";
        sqlUpdatePostTag += " select ";
        sqlUpdatePostTag += " ?, ";
        sqlUpdatePostTag += "         tb.id_tag ";
        sqlUpdatePostTag += " from ( ";
        sqlUpdatePostTag += "         select tags.id id_tag ";
        sqlUpdatePostTag += "         from tags ";
        sqlUpdatePostTag += "         where tags.tag_name in ( ";
        int index = 0;
        for (Tag tag : post.getTags()) {
            index++;
            if (index != 1) {
                sqlUpdatePostTag += ",";
            }
            sqlUpdatePostTag += " ? ";
        }
        sqlUpdatePostTag += "         ) ";
        sqlUpdatePostTag += " ) tb ";
        sqlUpdatePostTag += " left join post_tag tb2 ";
        sqlUpdatePostTag += " on tb2.id_tag = tb.id_tag ";
        sqlUpdatePostTag += " and tb2.id_post = ? ";
        sqlUpdatePostTag += " where tb2.id is null ";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_POST_SQL);
             PreparedStatement stmtUpdatePostTag = connection.prepareStatement(sqlUpdatePostTag);

        ) {
            statement.setString(1, post.getTitle());
            statement.setString(2, post.getShortContent());
            statement.setString(3, post.getFullContent());
            statement.setString(4, post.getImage());
            statement.setInt(5, post.getCategory().getId());
            statement.setInt(6, post.getId());
            statement.executeUpdate();

            // insert tags
            if (post.getTags() != null) {
                (new TagDAO()).insertMany(post.getTags());
                // update post tags
                index = 0;
                stmtUpdatePostTag.setInt(++index, post.getId());
                for (Tag tag : post.getTags()) {
                    stmtUpdatePostTag.setString(++index, tag.getTagName());
                }
                stmtUpdatePostTag.setInt(++index, post.getId());
                stmtUpdatePostTag.executeUpdate();
            }
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        try (Connection connection = getConnection();
             CallableStatement statement = connection.prepareCall(DELETE);
        ) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    @Override
    public Post findById(int id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_POST_SQL);
        ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String title = rs.getString("title");
                String shortContent = rs.getString("short_content");
                String fullContent = rs.getString("full_content");
                String image = rs.getString("image");
                String date = rs.getString("publish_date");
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(date, dateTimeFormatter);
                int idCategory = rs.getInt("id_category");
                String categoryName = rs.getString("category_name");
                Category category = new Category(idCategory, categoryName);
                Post post = new Post(id, title, shortContent, fullContent, dateTime, image, category);
                post.setTags(
                        (new TagDAO()).getAllByPost(id)
                );
                return post;
            }
        }
        return null;
    }



    public List<Post> top3PostNew() throws SQLException {
        List<Post> posts = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_TOP3_SQL);
             ResultSet rs = statement.executeQuery();
        ) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String shortContent = rs.getString("short_content");
                String fullContent = rs.getString("full_content");
                String publishDate = rs.getString("publish_date");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(publishDate, formatter);
                String image = rs.getString("image");
                int idCategory = rs.getInt("id_category");
                String categoryName = rs.getString("category_name");

                Category category = new Category(idCategory, categoryName);

                Post post = new Post(id, title, shortContent, fullContent, dateTime, image, category);

                posts.add(post);
            }
        }
        return posts;
    }

    public int countPost() throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(COUNT_POST_SQL);
             ResultSet rs = statement.executeQuery();
        ) {
            while (rs.next()) {
                return rs.getInt("count");
            }
        }
        return 0;
    }

    public List<Post> sizePage(int index, int size) throws SQLException {

        List<Post> posts = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(PAGINATION_SQL);
        ) {
            statement.setInt(1, index);
            statement.setInt(2, size);
            statement.setInt(3, size);
            statement.setInt(4, index);
            statement.setInt(5, size);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String shortContent = rs.getString("short_content");
                String fullContent = rs.getString("full_content");
                String image = rs.getString("image");
                int categoryId = rs.getInt("id_category");
                String publishDate = rs.getString("publish_date");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(publishDate, formatter);
                String categoryName = rs.getString("category_name");

                Category category = new Category(categoryId, categoryName);
                Post post = new Post(id, title, shortContent, fullContent, dateTime, image, category);

                posts.add(post);

            }
        }
        return posts;
    }

    public List<Post> listPostInCategory(int id) throws SQLException {
        List<Post> postList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(LIST_POSTS_IN_CATEGORY);
        ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int idPost = rs.getInt("id");
                String title = rs.getString("title");
                String shortContent = rs.getString("short_content");
                String fullContent = rs.getString("full_content");
                String image = rs.getString("image");
                String date = rs.getString("publish_date");
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(date, dateTimeFormatter);
                String categoryName = rs.getString("category_name");
                Category category = new Category(id, categoryName);
                Post post = new Post(idPost, title, shortContent, fullContent, dateTime, image, category);

                postList.add(post);
            }
        }
        return postList;
    }

    public List<Post> randomList() throws SQLException {
        List<Post> listPost = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(LIST_POST_RANDOM);
             ResultSet rs = statement.executeQuery();
        ) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String shortContent = rs.getString("short_content");
                String fullContent = rs.getString("full_content");
                String publishDate = rs.getString("publish_date");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(publishDate, formatter);
                String image = rs.getString("image");
                int idCategory = rs.getInt("id_category");
                String categoryName = rs.getString("category_name");
                Category category = new Category(idCategory, categoryName);
                Post post = new Post(id, title, fullContent, shortContent, dateTime, image, category);
                listPost.add(post);
            }

        }

        return listPost;
    }

    public void updateView(int id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_VIEWS);
        ) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public List<Post> top3PostInViews() throws SQLException {
        List<Post> postList = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(TOP3_POST_VIEWS);
             ResultSet rs = statement.executeQuery();
        ) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String shortContent = rs.getString("short_content");
                String fullContent = rs.getString("full_content");
                String image = rs.getString("image");
                int categoryId = rs.getInt("id_category");
                String date = rs.getString("publish_date");
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime localDateTime = LocalDateTime.parse(date, dateTimeFormatter);
                Category category = new Category(categoryId);
                Post post = new Post(id, title, shortContent, fullContent, localDateTime, image, category);

                postList.add(post);
            }
        }
        return postList;
    }
}
