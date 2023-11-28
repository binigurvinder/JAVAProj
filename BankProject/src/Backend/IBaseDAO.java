package Backend;

import java.sql.SQLException;

import Models.Account;


public interface IBaseDAO<T> {
    int create(T t) throws SQLException;
    int update(T t) throws SQLException;
    T find(Object id) throws SQLException;
    int delete(T t) throws SQLException;
}


