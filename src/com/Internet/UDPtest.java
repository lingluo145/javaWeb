package com.Internet;

import org.junit.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @BelongsProhect:UDP协议的网络编程
 * @BelongsPackage:com.Internet
 * @Auther:Chen
 * @CreateTime:2022--07--11 17--00
 * @Decription:T000
 */
public class UDPtest {
    //发送端
    @Test
    public void sender() throws IOException {
        DatagramSocket socket = new DatagramSocket();
        java.lang.String str = "UDP数据爆破";
        byte[] data = str.getBytes();
        InetAddress inet = InetAddress.getLocalHost();
        DatagramPacket packet = new DatagramPacket(data,0,data.length,inet,9090);

        socket.send(packet);
        socket.close();
    }
    //接收端
    @Test
    public void recevier() throws IOException {
        DatagramSocket socket = new DatagramSocket(9090);

        byte[] data = new byte[100];
        DatagramPacket packet = new DatagramPacket(data,0,data.length);

        socket.receive(packet);

        java.lang.String string = new java.lang.String(packet.getData(),0,packet.getLength());
        System.out.println(string);
        socket.close();
    }
}
