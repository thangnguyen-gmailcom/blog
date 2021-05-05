package service;

import dao.BaseDAO;
import dao.CategoryDAO;
import dao.IBaseDAO;
import model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryService implements IBaseService<Category> {

    CategoryDAO categoryDAO = new CategoryDAO();

    @Override
    public List<Category> selectAll() throws SQLException {
        return categoryDAO.selectAll();
    }

    @Override
    public void insert(Category category) throws SQLException {
        categoryDAO.insert(category);
    }

    @Override
    public void update(Category category) throws SQLException {
        categoryDAO.update(category);
    }

    @Override
    public void delete(int id) throws SQLException {
        categoryDAO.delete(id);
    }

    @Override
    public Category findById(int id) throws SQLException {
        return categoryDAO.findById(id);
    }

    public boolean checkEx(int id){
        return categoryDAO.checkEx(id);
    }

    public Category checkCategoryName(String name) throws SQLException {
        return categoryDAO.checkNameCategory(name);
    }
}
