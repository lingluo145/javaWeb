package com.mySQL;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.util.JDBCUtils;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @BelongsProhect:ssss
 * @BelongsPackage:com.mySQL
 * @Auther:Chen
 * @CreateTime:2022--07--31 15--18
 * @Decription:T000
 */
public class druidTest {

    @Test
    public void getDruidConnection() throws Exception {
        Properties pros = new Properties();
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
        pros.load(is);
        DataSource source = DruidDataSourceFactory.createDataSource(pros);
        Connection conn = source.getConnection();
        System.out.println(conn);
    }

    @Test
    public void getDridConnection1() throws SQLException {
        Connection conn = JDBCUtils.getDruidConnection();
        System.out.println(conn);
    }


}
