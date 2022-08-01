package com.mySQL;

import com.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * @BelongsProhect:ssss
 * @BelongsPackage:com.mySQL
 * @Auther:Chen
 * @CreateTime:2022--07--31 14--33
 * @Decription:T000
 */
public class dbcpTest {

    @Test
    public void testgetdbcpConnection() throws Exception {
        Connection conn = null;
        try {
            conn = JDBCUtils.getDbcpConnection3();
            System.out.println(conn);
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
