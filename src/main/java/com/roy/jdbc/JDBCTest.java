package com.roy.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCTest {

    @Test
    public void testC3P0() throws PropertyVetoException, SQLException {

        System.out.println(JdbcTools.getConnection());

    }

    @Test
    public void testC3P0WithConfig() throws SQLException {

        ComboPooledDataSource ds = new ComboPooledDataSource("helloc3p0");

        Connection connection = ds.getConnection();

        System.out.println(connection);

    }



}
