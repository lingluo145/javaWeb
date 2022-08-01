package com.mySQL;

import com.util.JDBCUtils;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProhect:commons-dbUtils是开源的JDBC工具类库，封装了对数据库的增删改查操作
 * @BelongsPackage:com.mySQL
 * @Auther:Chen
 * @CreateTime:2022--07--31 16--12
 * @Decription:T000
 */
public class QueryRunnerTest{
    @Test
    public void getConnection() {
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getC3p0Connection();
            String sql = "insert into goods(name,money) values(?,?)";
            int insertCount = runner.update(conn, sql, "仨", 648);
            System.out.println("添加了"+ insertCount+"条记录");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.cloaseResourse(conn,null);
        }
    }

    //BeanHandler：是ResultSetHandler接口的实现类，用于封装表中的一条记录构成的集合
    @Test
    public void testQuery() {
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getC3p0Connection();
            String sql = "select name,email,birth from customers where name=?";
            BeanHandler<Customers> handler = new BeanHandler<>(Customers.class);
            Customers cust = runner.query(conn, sql, handler, "肆");
            System.out.println(cust);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.cloaseResourse(conn,null);
        }

    }

    //BeanListHandler：是ResultSetHandler接口的实现类，用于封装表中的多条记录构成的集合
    @Test
    public void testListQuery() {
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getC3p0Connection();
            String sql = "select name,email,birth from customers where name in (?,?)";
            BeanListHandler<Customers> listHandler = new BeanListHandler<>(Customers.class);
            List<Customers> list = runner.query(conn, sql, listHandler, "灵","贰");
            list.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.cloaseResourse(conn,null);
        }
    }
    //MapHandler：是ResultHandler接口的实现类，用于封装表中的一条记录
    //将字段及相应字段的值作为相应字段的key和value
    @Test
    public void testMapQuery(){
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getC3p0Connection();
            String sql = "select name,email,birth from customers where name in (?,?)";

            MapHandler mapHandler = new MapHandler();
            Map<String, Object> map = runner.query(conn, sql, mapHandler, "灵");
            System.out.println(map);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.cloaseResourse(conn,null);
        }
    }

    //MapListHandler：是ResultHandler接口的实现类，用于封装表中的多条记录
    //将字段及相应字段的值作为相应字段的key和value，将这些map添加到List中
    @Test
    public void testMapListQuery() {
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getC3p0Connection();
            String sql = "select name,email,birth,photo from customers where name in (?,?)";

            MapListHandler map = new MapListHandler();
            List<Map<String, Object>> query = runner.query(conn, sql, map, "灵", "肆");
            query.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.cloaseResourse(conn,null);
        }
    }

    //ScalarHander：用于查询特殊值
    @Test
    public void testQuery5(){
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getC3p0Connection();
            String sql = "select count(*) from customers";
            ScalarHandler handler = new ScalarHandler();
            Long count = (Long)runner.query(conn, sql, handler);
            System.out.println(count);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.cloaseResourse(conn,null);
        }
    }

    @Test
    public void dbUtilsTest() throws SQLException {
        QueryRunner runner = new QueryRunner();
        Connection conn = JDBCUtils.getC3p0Connection();
        String sql = "select name,email,birth from customers where name in (?,?)";
        ResultSetHandler<ArrayList<Customers>> handler = new ResultSetHandler<ArrayList<Customers>>(){

            @Override
            public ArrayList<Customers> handle(ResultSet resultSet) throws SQLException {
                System.out.println("我廖某就是个傻逼！");
                ArrayList<Customers> list = new ArrayList<>();
                while(resultSet.next()){
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    Date birth = resultSet.getDate("birth");
                    Customers cust = new Customers(name, email, birth,null);
                    list.add(cust);
                }
                return list;
            }
        };

        ArrayList<Customers> query = runner.query(conn, sql, handler, "贰", "肆");
        query.forEach(System.out::println);
        JDBCUtils.cloaseResourse(conn,null);

        DbUtils.close(conn);
        DbUtils.closeQuietly(conn);
    }
}
