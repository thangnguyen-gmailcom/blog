package dao;

import model.Comment;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO extends BaseDAO implements IBaseDAO<Comment> {

    private final String insertComment = "INSERT INTO comments(content,id_post,`name`,gmail) VALUES(?,?,?,?);";

    private final String SELECT_COMMENT_IN_POST = "SELECT * FROM comments  LEFT JOIN posts ON comments.id_posts = posts.id\n" +
            "WHERE id_posts = ?; ";

    @Override
    public List<Comment> selectAll() throws SQLException {
        return null;
    }

    @Override
    public void insert(Comment comment) throws SQLException {
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(insertComment);
        ){
            statement.setString(1,comment.getContent());
            statement.setInt(2,comment.getPost().getId());
            statement.setString(3,comment.getName());
            statement.setString(4,comment.getGmail());
            statement.executeUpdate();
        }
    }

    @Override
    public void update(Comment comment) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public Comment findById(int id) throws SQLException {
        return null;
    }

    public List<Comment> selectCommentsInPost(int id) throws SQLException {
        List<Comment> commentList = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_COMMENT_IN_POST);
        ){
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                String gmail = rs.getString("gmail");
                String name = rs.getString("name");
                String content = rs.getString("content");
                String date = rs.getString("date_feedback");
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(date, dateTimeFormatter);

                Comment comment = new Comment(content,dateTime,name,gmail);

                commentList.add(comment);
            }
        }
        return commentList;
    }
}
