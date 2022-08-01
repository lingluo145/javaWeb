package com.Internet;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @BelongsProhect:实现TCP的网络编程
 * @BelongsPackage:com.Internet
 * @Auther:Chen
 * @CreateTime:2022--07--11 13--02
 * @Decription:实现客户端发送信息给服务端，服务端将数据显示在控制台上
 */
public class TCPtest1 {
    //客户端
    @Test
    public void client(){
        Socket socket = null;
        OutputStream outputStream = null;
        try {
            //1、创建Socket对象，指明服务器端的ip和端口号
            InetAddress inet = InetAddress.getByName("172.17.81.209");
            socket = new Socket(inet,8899);
            //2、获取一个输出流，用于输出数据
            outputStream = socket.getOutputStream();
            //3、写出数据的操作
            outputStream.write("你好，我是Chen".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4、资源的关闭
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



    }
    //服务端
    @Test
    public void server() throws IOException {
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream inputStream = null;
        ByteArrayOutputStream baos = null;
        try {
            //1、创建服务器端的serverSocket：指明自己的端口号
            serverSocket = new ServerSocket(8899);
            //2、调用accept()表示结构来自与客户端的Socket
            socket = serverSocket.accept();
            //3、获取输入流
            inputStream = socket.getInputStream();
            //不建议这种写法
//        byte[] buffer = new byte[1024];
//        int len;
//        while((len = inputStream.read(buffer)) != -1){
//            String str = new String(buffer, 0, len);
//            System.out.println(str);
//        }
            //4、读取输入流的数据
            baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[20];
            int len;
            while((len = inputStream.read(buffer)) != -1){
                baos.write(buffer,0,len);
            }
            System.out.println(baos.toString());
            System.out.println("收到了来自于：" + socket.getInetAddress().getHostAddress()+ "的数据");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //5、关闭资源
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(socket!=null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(serverSocket!=null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
