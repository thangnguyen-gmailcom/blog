package dao;

import model.Category;
import model.Post;
import model.Tag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TagDAO extends BaseDAO implements IBaseDAO<Tag> {

    @Override
    public List<Tag> selectAll() throws SQLException {
        return null;
    }

    @Override
    public void insert(Tag tag) throws SQLException {
        String sqlInserTags = "";
        sqlInserTags +=" insert into tags ( ";
        sqlInserTags +="         tag_name ";
        sqlInserTags +=" ) select ? ";
        sqlInserTags +=" where ? not in ( ";
        sqlInserTags +="         select tag_name from tags ";
        sqlInserTags +=" ) ";

        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sqlInserTags);
        ) {
            statement.setString(1, tag.getTagName());
            statement.setString(2, tag.getTagName());
            statement.executeUpdate();
        }
    }

    public void insertMany(List<Tag> arr ) throws SQLException {
        for(Tag tag: arr){
            this.insert(tag);
        }
    }

    @Override
    public void update(Tag tag) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public Tag findById(int id) throws SQLException {
        return null;
    }

    public ArrayList<Tag> getAllByPost(int postId) throws SQLException {
        ArrayList<Tag> arr  = new ArrayList<Tag>();
        String sql = "";
        sql += " select tags.* from post_tag ";
        sql += " inner join tags ";
        sql += " on post_tag.id_tag = tags.id ";
        sql += " where post_tag.id_post = ? ";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, postId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                arr.add(new Tag(
                   rs.getInt("id"),
                        rs.getString("tag_name")
                ));
            }
        }
        return arr;
    }
}
