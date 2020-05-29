package tcp;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args){
        Socket socket = null;
        BufferedReader in = null;
        BufferedWriter out = null;
        Integer count = 1;
        try {
            //创建服务器端套接字：指定监听端口
            ServerSocket server = new ServerSocket(8888);
            System.out.println("服务端已启动，等待信息：");
            //监听客户端的连接
            socket = server.accept();
            //获取socket的输入输出流接收和发送信息
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            while (count <= 100) {
                //接收客户端发送的信息
                String str = in.readLine();
                System.out.println("服务端获取到客户端信息：" + str);
                count++;
                //如果客户端发送的是“end”则终止连接
                //否则，发送反馈信息
            }

            count = 1;
            //创建Socket对象，指定服务器端的IP与端口
            socket = new Socket(InetAddress.getLocalHost(), 8889);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            while (count <= 100) {
                //发送信息
                System.out.println("向客户端发生信息：" + count);
                out.write(count.toString() + "\n");
                out.flush();
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(out != null){
                try {
                    out.close();
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

}
