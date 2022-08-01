package com.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mySQL.MySQLtest;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @BelongsProhect:获取数据库连接
 * @BelongsPackage:com.util
 * @Auther:Chen
 * @CreateTime:2022--07--28 22--49
 * @Decription:T000
 */
public class JDBCUtils {

    //方法一：直接获取数据库连接
    public static Connection getConnection()throws Exception{
        //1、读取配置文件中的4个基本信息
        InputStream is = MySQLtest.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties pros = new Properties();
        pros.load(is);

        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        String url = pros.getProperty("url");
        String driverClass = pros.getProperty("driverClass");
        //2、加载驱动
        Class.forName(driverClass);
        //3、获取连接
       Connection conn = DriverManager.getConnection(url,user,password);
//            System.out.println(conn);
        return conn;
    }

    //方法二：通过数据库连接池获取数据库连接
    //用户的多个数据库操作只需要提供一个连接池，无需重复实例化
    static ComboPooledDataSource cpds = new ComboPooledDataSource("hellc3p0");
    public static Connection getC3p0Connection() throws SQLException {
        Connection conn = cpds.getConnection();
        return conn;
    }

    public static void cloaseResourse(Connection conn, Statement ps){
        //7、资源关闭
        try {
            if(ps != null){
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(conn != null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void cloaseResourse(Connection conn, Statement ps, ResultSet rs){
        //7、资源关闭
        try {
            if(rs != null){
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(ps != null){
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(conn != null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getDbcpConnection1() throws SQLException {
        BasicDataSource source = new BasicDataSource();
        //设置基本信息
        source.setDriverClassName("com.mysql.cj.jdbc.Driver");
        source.setUrl("jdbc:mysql://localhost:3306/dbtest1?useUnicode=ture&characterEncoding=UTF-8&serverTimezone=GMT");
        source.setUsername("root");
        source.setPassword("123456");
        //其他设计数据库连接池管理的基本信息
        source.setInitialSize(10);
        source.setMaxActive(10);

        Connection conn = source.getConnection();
        System.out.println(conn);
    }

    //使用配置文件的方式一：未考虑线程池创建的问题（练习）
    public static Connection getDbcpConnection2() throws Exception {
        Properties pros = new Properties();
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("dbcp.properties");
//        FileInputStream is = new FileInputStream(new File("src/dbcp.properties"));
        pros.load(is);
        DataSource dataSource = BasicDataSourceFactory.createDataSource(pros);
        Connection conn = dataSource.getConnection();
        return conn;
    }

    //使用配置文件的方式二：考虑线程池的方法
    private static DataSource source;
    static{
        try {
            Properties pros = new Properties();
            FileInputStream is = new FileInputStream(new File("src/dbcp.properties"));
            pros.load(is);
            source = BasicDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getDbcpConnection3() throws SQLException {
        Connection conn = source.getConnection();
        return conn;
    }



    private static DataSource source1;
    static{
        try {
            Properties pros = new Properties();
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
            pros.load(is);
            source1 = DruidDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getDruidConnection() throws SQLException {

        Connection conn = source.getConnection();
        return conn;
    }
}
