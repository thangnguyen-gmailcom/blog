package service;

import dao.CommentDAO;
import model.Comment;

import java.sql.SQLException;
import java.util.List;

public class CommentService implements IBaseService<Comment> {

    CommentDAO commentDAO = new CommentDAO();

    @Override
    public List<Comment> selectAll() throws SQLException {
        return null;
    }

    @Override
    public void insert(Comment comment) throws SQLException {
        commentDAO.insert(comment);
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

    public List<Comment> selectCommentsInPost(int id) throws SQLException{
        return commentDAO.selectCommentsInPost(id);
    }
}
