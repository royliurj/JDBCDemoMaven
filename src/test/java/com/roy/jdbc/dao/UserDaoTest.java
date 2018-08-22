package com.roy.jdbc.dao;

import com.roy.jdbc.JdbcTools;
import com.roy.jdbc.domain.User;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class UserDaoTest {

    UserDao userDao = new UserDao();

    @Test
    public void getForValue() {
    }

    @Test
    public void getForList() {
    }

    @Test
    public void get() {

        Connection connection = null;

        try {
            connection = JdbcTools.getConnection();
            String sql = "select * from user where userId = ?";

            User user = userDao.get(connection,sql,1);

            System.out.println(user);


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JdbcTools.release(connection);
        }

    }

    @Test
    public void batch() {
    }

    @Test
    public void update() {
    }
}