package dao;

import model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO extends BaseDAO implements IBaseDAO<Category> {

    private final String checkId = "update categorys set category_name = category_name where id = ?;";

    private final String SELECT_ALL = "SELECT * FROM categorys WHERE categorys.is_deleted = 0;";

    private final String INSERT_CATEGORY = "INSERT INTO categorys(category_name) VALUES(?)";

    private  final String UPDATE_CATEGORY = "UPDATE categorys SET category_name = ? WHERE id = ?;";

    private final String FIND_CATEGORY_BY_ID = "SELECT * FROM categorys WHERE id = ?";

    private final String DELETE_CATEGORY = "{CALL delete_categorys_posts(?)}";

    private final String CHECK_SQL = "SELECT * FROM categorys WHERE category_name = ?";



    @Override
    public List<Category> selectAll() throws SQLException {
        List<Category> categories = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
             ResultSet rs = statement.executeQuery();
        ) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String categoryName = rs.getString("category_name");
                Category category = new Category(id, categoryName);

                categories.add(category);
            }
        }
        return categories;
    }

    @Override
    public void insert(Category category) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_CATEGORY);
        ) {
            statement.setString(1, category.getCategoryName());
            statement.executeUpdate();
        }
    }

    @Override
    public void update(Category category) throws SQLException {
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_CATEGORY);
        ){
            statement.setString(1,category.getCategoryName());
            statement.setInt(2,category.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        try(Connection connection = getConnection();
            CallableStatement statement = connection.prepareCall(DELETE_CATEGORY);
        ){
            statement.setInt(1,id);
            statement.executeUpdate();
        }
    }

    @Override
    public Category findById(int id) throws SQLException {
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_CATEGORY_BY_ID);
        ){
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                String name = rs.getString("category_name");
                return new Category(id,name);
            }
        }
        return null;
    }

    public boolean checkEx(int id) {
        boolean rowUpdate = false;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(checkId);
        ) {
            statement.setInt(1, id);
            rowUpdate = statement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowUpdate;
    }


    public Category checkNameCategory(String name)throws SQLException{
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(CHECK_SQL);
        ){
            statement.setString(1,name);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                return new Category(rs.getInt("id"),
                        rs.getString("category_name"));
            }
        }
        return null;
    }
}
