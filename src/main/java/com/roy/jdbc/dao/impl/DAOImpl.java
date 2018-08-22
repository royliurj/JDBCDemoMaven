package com.roy.jdbc.dao.impl;

import com.roy.jdbc.ReflectionUtils;
import com.roy.jdbc.dao.DAO;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import sun.reflect.Reflection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DAOImpl<T> implements DAO<T> {

    QueryRunner qr = null;
    Class<T> type = null;

    public DAOImpl(){
        qr = new QueryRunner();
        type = ReflectionUtils.getSuperGenericType(getClass());
    }

    public <E> E getForValue(Connection connection, String sql, Object... args) {
        return null;
    }

    public List<T> getForList(Connection connection, String sql, Object... args) {
        return null;
    }

    public T get(Connection connection, String sql, Object... args) throws SQLException {

        return qr.query(connection,sql,new BeanHandler<T>(type),args);


    }

    public void batch(Connection connection, String sql, Object[]... args) {

    }

    public void update(Connection connection, String sql, Object... args) {

    }
}
