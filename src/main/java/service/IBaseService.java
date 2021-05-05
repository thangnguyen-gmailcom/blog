package service;

import java.sql.SQLException;
import java.util.List;

public interface IBaseService<T> {
    List<T> selectAll() throws SQLException;

    void insert(T t) throws SQLException;

    void update(T t) throws SQLException;

    void delete(int id) throws SQLException;

    T findById(int id) throws SQLException;
}
