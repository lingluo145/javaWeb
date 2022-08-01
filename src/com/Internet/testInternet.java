package com.Internet;

import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @BelongsProhect:ssss
 * @BelongsPackage:com.Internet
 * @Auther:Chen
 * @CreateTime:2022--07--11 11--13
 * @Decription:T000
 */

public class testInternet {
    @Test
    public void test1() {
        InetAddress inet1 = null;
        InetAddress inet2 = null;
        try {
            inet1 = InetAddress.getByName("192.168.10.14");
            System.out.println(inet1);

            inet2 = InetAddress.getByName("www.baidu.com");
            System.out.println(inet2);

            inet2 = InetAddress.getByName("127.0.0.1");
            System.out.println(inet2);
            //获取本机的IP地址
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println(localHost);

            //getHostName( )获取主机的域名
            System.out.println(inet2.getHostName());
            //getHostAddress( )获取主机的IP地址
            System.out.println(inet2.getHostAddress());

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }
}
