package com.mySQL;

import com.util.JDBCUtils;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Field;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.Scanner;


/**
 * @BelongsProhect:ssss
 * @BelongsPackage:com.mySQL
 * @Auther:Chen
 * @CreateTime:2022--07--28 17--29
 * @Decription:T000
 */
public class MySQLtest {
    @Test
    public void testMySQL1() throws SQLException {
        Driver driver = new com.mysql.cj.jdbc.Driver();

        String url = "jdbc:mysql://localhost:3306/dbtest1";
        Properties info = new Properties();
        info.setProperty("user","root");
        info.setProperty("password","123456");
        Connection conn = driver.connect(url,info);

        System.out.println(conn);
    }

    @Test
    public void test2() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        Class clazz = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver)clazz.newInstance();

        String url = "jdbc:mysql://localhost:3306/dbtest1";
        Properties info = new Properties();
        info.setProperty("user","root");
        info.setProperty("password","123456");
        Connection conn = driver.connect(url,info);

        System.out.println(conn);
    }

    @Test
    public void test3() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        String url = "jdbc:mysql://localhost:3306/dbtest1";
        String user = "root";
        String password = "123456";

//        Class clazz =
        Class.forName("com.mysql.cj.jdbc.Driver");//在mysql实现类中静态加载
//        Driver driver = (Driver)clazz.newInstance();

//        DriverManager.registerDriver(driver);
        Connection conn = DriverManager.getConnection(url,user,password);

        System.out.println(conn);
    }

    /**
     * @description:增操作（无DAO修正）
     * @author: Chen
     * @date: 2022/7/28 22:57
     * @param: []
     * @return: void
     **/
    @Test
    public void test5(){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
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
            conn = DriverManager.getConnection(url,user,password);
//            System.out.println(conn);
            //4、预编译sql指令，返回PreparedStatement的实例
            String sql = "insert into customers(name,email,birth) values(?,?,?)";//?：占位符
            ps = conn.prepareStatement(sql);
            //5、填充占位符
            ps.setString(1,"零落");
            ps.setString(2,"Lingluo@163.com");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = sdf.parse("2001-02-24");
            ps.setDate(3,new Date(date.getTime()));
            //6、执行操作
            ps.execute();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }finally {
            //7、资源关闭
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * @description:改操作
     * @author: Chen
     * @date: 2022/7/28 22:57
     * @param: 
     * @return: 
     **/
    @Test
    public void testUpdate() throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //1、获取数据库连接
            conn = JDBCUtils.getConnection();
            //2、预编译sql语句，返回PreparedmentStatement的实例
            String sql = "update customers set email=? where name=?";
            ps = conn.prepareStatement(sql);
            //3、填充站位符
            ps.setString(1,"lingluo@qq.com");
            ps.setString(2,"零落");
            //4、执行
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                //5、资源的关闭
                JDBCUtils.cloaseResourse(conn,ps,null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testSelect(){

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "SELECT name,email,birth FROM `customers` WHERE name=?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1,"零落");

            rs = ps.executeQuery();
            if(rs.next()){
                String name = (String)rs.getObject(1);
                String email = (String)rs.getObject(2);
                java.util.Date birth = (java.util.Date)rs.getObject(3);

                order order = new order(name, email, birth);
                System.out.println(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtils.cloaseResourse(conn,ps,rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public <T> T orderForQuery(Class<T> clazz,String sql, Object ...args){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            for(int i=0; i<args.length; i++){
                ps.setObject(i+1, args[i]);
            }
            //结果集
            rs = ps.executeQuery();
            //结果集的元数据
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            if(rs.next()){
                T t = clazz.newInstance();
                for(int i = 0; i < columnCount; i++){
                    Object columnValue = rs.getObject(i + 1);
//                    String columnName = rsmd.getColumnName(i+1);
                    String columnLabel = rsmd.getColumnLabel(i+1);
                    System.out.println("进行到第"+ i +"次");
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }
                System.out.println("前");
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtils.cloaseResourse(conn,ps,rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Test
    public void testOrderForQuery(){
        String sql = "SELECT name name,email email,birth birth FROM `customers` WHERE name=?";
        order order = orderForQuery(order.class , sql, "零落");
        System.out.println(order);
    }

    @Test
    public void testInsert(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入姓名：");
        String user = scanner.next();
        System.out.println("请输入邮箱：");
        String password = scanner.next();
        System.out.println("请输入生日日期：");
        String birthday = scanner.next();

        String sql = "insert customers(name,email,birth) values(?,?,?)";
        int ret = insert(sql,user,password,birthday);
        if(ret>0){
            System.out.println("添加成功");
        }else{
            System.out.println("添加失败");
        }
    }

    public int insert(String sql,Object ...args){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            for(int i = 0;i < args.length;i++){
                ps.setObject(i+1,args[i]);
            }
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Test
    public void testInsertPhoto() throws Exception {
        Connection conn = JDBCUtils.getConnection();
        String sql="insert customers(name,email,birth,photo) values(?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setObject(1,"肆");
        ps.setObject(2,"si@163.com");
        ps.setObject(3,"2000-04-15");
        FileInputStream is = new FileInputStream(new File("src/qiu02.jpg"));
        ps.setBlob(4,is);

        ps.execute();

        is.close();
        JDBCUtils.cloaseResourse(conn,ps);

    }

    @Test
    public void testReadPhoto() throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        FileOutputStream fos = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "select name,email,birth,photo from customers where name=?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1,"肆");

            rs = ps.executeQuery();
            if(rs.next()){
                String name = rs.getString("name");
                String email = rs.getString("email");
                Date birth = rs.getDate("birth");
                order order = new order(name, email, birth);
                System.out.println(order);

                Blob photo = rs.getBlob("photo");
                InputStream is = photo.getBinaryStream();
                fos = new FileOutputStream("src/qieTo02.jpg");
                byte[] buffer = new byte[1024];
                int len;
                while((len = is.read(buffer)) != -1){
                    fos.write(buffer,0,len);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(fos != null){
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            JDBCUtils.cloaseResourse(conn,ps,rs);
        }

    }

    @Test
    public void testInsertLarge(){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            long start = System.currentTimeMillis();

            conn = JDBCUtils.getConnection();
            String sql = "insert into goods values(?)";
            ps = conn.prepareStatement(sql);
            for(int i = 1; i<20000; i++){
                ps.setObject(1,"name_"+i);
                ps.execute();
            }
            long end = System.currentTimeMillis();
            System.out.println("用时："+(end - start));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.cloaseResourse(conn,ps);
        }
    }
    
    /*
    1、addBatch()、executeBatch()、clearBatch()
    2、mysql服务器默认是关闭批处理的，所以需要一个参数进行开启，
        将  ?rewriteBatchedStatements=true  写在配置文件的url后面
    3、使用更新的mysql驱动：mysql-connector-java-5.1.37-bin,jar
     */
    @Test
    public void testBatchInsert(){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            long start = System.currentTimeMillis();
            String sql = "insert into goods values(?)";
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            for(int i=1; i<=1000000; i++){
                ps.setObject(1,"name_" + i);
                //1、攒sql
                ps.addBatch();

                if(i%500 == 0){
                    //2、执行batch
                    ps.executeBatch();
                    //3、清空batch
                    ps.clearBatch();
                }
            }
            long end = System.currentTimeMillis();
            System.out.println("用时：" + (end-start));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.cloaseResourse(conn,ps);
        }
    }

    public void update(Connection conn, String sql, Object ...args){
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for(int i = 0; i<args.length; i++){
                ps.setObject(i+1, args[i]);
            }
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtils.cloaseResourse(null,ps, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testAutoCommit(){
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            System.out.println(conn.getAutoCommit());
            System.out.println(conn.getTransactionIsolation());
            //设置事务隔离级别
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            //设置不允许自动提交
            conn.setAutoCommit(false);

            String sql1 = "update goods set money = money+100 where name=?";
            update(conn, sql1,"壹");

            String sql2 = "update goods set money = money-100 where name=?";
            update(conn, sql2, "贰");

            System.out.println("转账成功");

            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                //操作完要将自动提交操作改为原始状态
                conn.setAutoCommit(true);
                JDBCUtils.cloaseResourse(conn,null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}










