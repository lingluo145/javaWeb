package com.Internet;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @BelongsProhect:ssss
 * @BelongsPackage:com.Internet
 * @Auther:Chen
 * @CreateTime:2022--07--11 16--19
 * @Decription:从客户端发送文件给服务端，服务端保存到本地，并返回“发送成功”给客户端，并关闭相应连接
 */
public class TCPtest3 {
    @Test
    public void client() {
        Socket socket = null;
        OutputStream os = null;
        FileInputStream fis = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            //1、创建Socket对象，指明服务区的IP和端口号
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 8899);
            //2、获取输出流
            os = socket.getOutputStream();
            //3、获取文件输入流
            fis = new FileInputStream("src\\com\\Internet\\露头猫.jpg");
            //4、读取输入流的数据
            byte[] buffer = new byte[1024];
            int len;
            while((len = fis.read(buffer)) != -1){
                os.write(buffer,0,len);
            }
            //关闭数据的输出
            socket.shutdownOutput();

            //5、接受来自服务器端的数据，并显示在控制台上
            is = socket.getInputStream();
            baos = new ByteArrayOutputStream();
            byte[] bufferr = new byte[20];
            int lenn;
            while((lenn = is.read(bufferr)) != -1){
                baos.write(bufferr,0,lenn);
            }
            System.out.println(baos.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //6、关闭资源
            if(fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(is !=null){
                try{
                    is.close();
                }catch (IOException e){
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
        }
    }
    @Test
    public void server() {
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream is = null;
        FileOutputStream fos = null;
        OutputStream os = null;
        try {
            //1、创建服务器端的serverSocket：指明自己的端口号
            serverSocket = new ServerSocket(8899);
            //2、调用accept()表示结构来自客户端的Socket
            socket = serverSocket.accept();
            //3、获取输入流
            is = socket.getInputStream();
            //4、读取输入流的数据
            fos = new FileOutputStream("src/com/Internet/photoTo1.jpg");
            //5、写出数据的操作
            byte[] buffer = new byte[1024];
            int len;
            while((len = is.read(buffer)) != -1){
                fos.write(buffer, 0, len);
            }
            //6、服务器给予客户端反馈
            os = socket.getOutputStream();
            os.write("服务端收到文件并返回".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //7、关闭资源
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(os != null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

