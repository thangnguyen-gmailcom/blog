package dao;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAO extends BaseDAO implements IBaseDAO<User> {

    private final String LOGIN_SQL = "SELECT * FROM users WHERE gmail = ? AND password = ? AND is_delete=0";

    private final String CHECK_SQL = "SELECT * FROM users WHERE gmail = ?";

    private final String INSERT_USER_SQL = "INSERT INTO users(name, gmail, password, isAdmin) \n" +
            "VALUES(?,?,?,0)";

    @Override
    public List<User> selectAll() throws SQLException {
        return null;
    }

    @Override
    public void insert(User user) throws SQLException {
        try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_USER_SQL);
        ){
            statement.setString(1,user.getUserName());
            statement.setString(2,user.getEmail());
            statement.setString(3, user.getPassword());
            statement.executeUpdate();
        }
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
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(LOGIN_SQL);
        ) {
            statement.setString(1, user);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                return new User(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("gmail"),
                        rs.getString("password"),
                        rs.getString("image"),
                        rs.getInt("isAdmin"));
            }
        }
        return null;
    }

    public User checkUser(String username) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(CHECK_SQL)){
            statement.setString(1,username);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                return new User(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("gmail"),
                        rs.getString("password"),
                        rs.getString("image"),
                        rs.getInt("isAdmin"));
            }
        }
        return null;
    }
}
