package service;

import dao.TagDAO;
import model.Tag;

import java.sql.SQLException;
import java.util.List;

public class TagService implements IBaseService<Tag>{

    private TagDAO tagDAO = new TagDAO();

    @Override
    public List<Tag> selectAll() throws SQLException {
        return null;
    }

    @Override
    public void insert(Tag tag) throws SQLException {

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

    public List<Tag> getAllTagsByPost(int postId) throws SQLException{
        return tagDAO.getAllByPost(postId);
    }
}
