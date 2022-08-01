package com.mySQL;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;
import com.util.JDBCUtils;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @BelongsProhect:ssss
 * @BelongsPackage:com.mySQL
 * @Auther:Chen
 * @CreateTime:2022--07--30 23--45
 * @Decription:T000
 */
public class c3p0Test {
    @Test
    public void testGetConnection() throws PropertyVetoException, SQLException {
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass("com.mysql.cj.jdbc.Driver");
        cpds.setJdbcUrl("jdbc:mysql://localhost:3306/dbtest1?useUnicode=ture&characterEncoding=UTF-8&serverTimezone=GMT");
        cpds.setUser("root");
        cpds.setPassword("123456");

        cpds.setInitialPoolSize(10);
        Connection conn = cpds.getConnection();
        System.out.println(conn);

        DataSources.destroy(cpds);
    }

    /**
     * @description:通过XML文件进行数据库连接池配置
     * @author: Chen
     * @date: 2022/7/31 13:47
     * @param: []
     * @return: void
     **/
    @Test
    public void testGetConnection1() throws SQLException {
        ComboPooledDataSource cpds = new ComboPooledDataSource("hellc3p0");
        Connection conn = cpds.getConnection();
        System.out.println(conn);

    }

    @Test
    public void testC3p0Utils(){
        Connection conn = null;
        try {
            conn = JDBCUtils.getC3p0Connection();
            MySQLtest ms = new MySQLtest();
            String sql1 = "update goods set money = money+100 where name=?";
            ms.update(conn,sql1,"贰");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.cloaseResourse(conn,null);
        }

    }

}
