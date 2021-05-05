package service;

import dao.UserDAO;
import model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService implements IBaseService<User> {

    UserDAO userDAO = new UserDAO();

    @Override
    public List<User> selectAll() throws SQLException {
        return null;
    }

    @Override
    public void insert(User user) throws SQLException {
        userDAO.insert(user);
    }

    @Override
    public void update(User user) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public User findById(int id) throws SQLException {
        return null;
    }

    public User login(String user, String password) throws SQLException {
        return userDAO.login(user,password);
    }

    public User checkUser(String user) throws SQLException{
        return userDAO.checkUser(user);
    }
}
