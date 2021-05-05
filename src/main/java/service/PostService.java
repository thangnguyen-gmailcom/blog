package service;

import dao.IBaseDAO;
import dao.PostDAO;
import model.Post;

import java.sql.SQLException;
import java.util.List;

public class PostService implements IBaseService<Post> {

    PostDAO postDAO = new PostDAO();

    @Override
    public List<Post> selectAll() throws SQLException {
        return postDAO.selectAll();
    }

    @Override
    public void insert(Post post) throws SQLException {
        postDAO.insert(post);
    }

    @Override
    public void update(Post post) throws SQLException {
        postDAO.update(post);
    }

    @Override
    public void delete(int id) throws SQLException {
        postDAO.delete(id);
    }

    @Override
    public Post findById(int id) throws SQLException {
        return postDAO.findById(id);
    }

    public List<Post> top3PostNew() throws SQLException {
        return postDAO.top3PostNew();
    }

    public List<Post> sizePage(int index, int size) throws SQLException{
        return postDAO.sizePage(index,size);
    }

    public int count() throws SQLException{
        return postDAO.countPost();
    }

    public List<Post> listPostInCategory(int id) throws SQLException {
        return postDAO.listPostInCategory(id);
    }

    public List<Post> randomList() throws SQLException{
        return postDAO.randomList();
    }

    public void updateViews(int id) throws SQLException{
        postDAO.updateView(id);
    }

    public List<Post> top3PostInViews()throws SQLException{
        return  postDAO.top3PostInViews();
    }

}
