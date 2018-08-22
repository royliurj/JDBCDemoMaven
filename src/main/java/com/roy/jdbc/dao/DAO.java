package com.roy.jdbc.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {

    <E> E getForValue(Connection connection, String sql, Object ... args);

    List<T> getForList(Connection connection, String sql, Object ... args);

    T get(Connection connection, String sql, Object ... args) throws SQLException;

    void batch(Connection connection, String sql, Object[] ... args);

    void update(Connection connection,String sql, Object ... args);
}
