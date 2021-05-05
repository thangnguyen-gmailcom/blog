package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {

    private static final String url = "jdbc:mysql://127.0.0.1:3306/blog?useEncoding=true&characterEncoding=UTF-8";

    private static final String user = "root";

    private static final String password = "123123";

    public Connection getConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }
}
