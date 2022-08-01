package com.PropertiesTest;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @BelongsProhect:ssss
 * @BelongsPackage:com.PropertiesTest
 * @Auther:Chen
 * @CreateTime:2022--07--06 11--03
 * @Decription:T000
 */
public class propertiesTest {
    @Test
    public void test1() throws FileNotFoundException {
        FileInputStream fis = null;
        InputStream is = null;
        try {
            Properties pros = new Properties();
            //此时文件默认在当前的module下
            //读取配置文件方式一：
//            fis = new FileInputStream("src\\jdbc.properties");
//            pros.load(fis);//加载对应流文件

            //读取配置文件的方式二：使用ClassLoader
            //配置文件默认识别为：当前module的src下
            ClassLoader classLoader = propertiesTest.class.getClassLoader();
            is = classLoader.getResourceAsStream("jdbc.properties");
            pros.load(is);

            String name = pros.getProperty("name");
            String password = pros.getProperty("password");
            System.out.println(name + "  " + password);



        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
//                fis.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
