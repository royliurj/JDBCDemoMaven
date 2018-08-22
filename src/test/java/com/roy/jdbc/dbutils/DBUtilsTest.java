package com.roy.jdbc.dbutils;

import com.roy.jdbc.JDBCTest;
import com.roy.jdbc.JdbcTools;
import com.roy.jdbc.domain.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Test;

import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DBUtilsTest {

    //线程安全，所以可以提取出来
    QueryRunner queryRunner = new QueryRunner();

    class MyResultSetHandler implements ResultSetHandler{
        /**
         * 参数为JDBC返回的ResultSet
         * handle方法可以对结果进行处理操作
         */
        public Object handle(ResultSet resultSet) throws SQLException {

            ResultSetMetaData rsMetadata = resultSet.getMetaData();

            while (resultSet.next()){
                for (int i = 0,length = rsMetadata.getColumnCount(); i < length ; i++) {
                    String name = rsMetadata.getColumnLabel(i+1);
                    Object value = resultSet.getObject(i+1);

                    System.out.println("column Name: " + name + " - " + value);
                }
            }

            return null;
        }
    }

    @Test
    public void testQuery(){

        Connection connection = null;

        try {
            connection = JdbcTools.getConnection();

            String sql = "select * from user";

            Map<String, Object> map = queryRunner.query(connection,sql,new MapHandler());
            System.out.println(map);

            //            String sql = "select count(*) from user";
//
//            long count = queryRunner.query(connection,sql,new ScalarHandler<Long>());
//            System.out.println(count);

//            List<Integer> ids = queryRunner.query(connection,sql, new ColumnListHandler<Integer>("userId"));
//            System.out.println(ids);

//            List<User> result = queryRunner.query(connection,sql, new BeanListHandler<User>(User.class));
//
//            System.out.println(result);
////            System.out.println(result);
//            for (User obj: result) {
//                System.out.println(obj);
//            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JdbcTools.release(connection);
        }


    }

    @Test
    public void testQueryRunnerUpdate() throws SQLException {

        //1,创建QueryRunner实现类
        QueryRunner queryRunner = new QueryRunner();

        //2,使用其update方法
        String sql = "update user set name = ? where userId = ?";

        Connection connection = JdbcTools.getConnection();

        try {
            queryRunner.update(connection,sql,"cc",2);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JdbcTools.release(connection);
        }
    }
    @Test
    public void testQueryRunnerInsert() throws SQLException {

        //1,创建QueryRunner实现类
        QueryRunner queryRunner = new QueryRunner();

        //2,使用其update方法
        String sql = "insert into user (userId, name,password) values (?,?,?)";

        Connection connection = JdbcTools.getConnection();

        try {
            queryRunner.update(connection,sql,3,"dd","dd");

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JdbcTools.release(connection);
        }
    }


    @Test
    public void testCallbaleStatement(){
        Connection conn = null;
        try {
            conn = JdbcTools.getConnection();

            //调用函数： 第1个问号返回值，第2个问号是参数，第3个问号是out参数
            String sql = "{?= call function_name(?,?)}";

            //调用存储过程
            //String sql = "{call procedure_name(?,?)}";


            CallableStatement callableStatement =  conn.prepareCall(sql);

            callableStatement.registerOutParameter(1, Types.NUMERIC);
            callableStatement.registerOutParameter(3,Types.NUMERIC);

            callableStatement.setInt(2,100);

            callableStatement.execute();

            //获取返回值
            double sum = callableStatement.getDouble(1);
            //获取out参数值
            long count = callableStatement.getLong(3);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JdbcTools.release(conn);
        }
    }
}
