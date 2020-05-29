package tcp;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient {
    public static void main(String[] args) {
        Socket socket = null;
        BufferedReader in = null;
        BufferedWriter out = null;
        Integer count = 1;
        try {
            //创建Socket对象，指定服务器端的IP与端口
            socket = new Socket(InetAddress.getLocalHost(), 8888);
            //获取scoket的输入输出流接收和发送信息
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            while (count <= 100) {
                //发送信息
                System.out.println("向服务端发生信息："+count);
                out.write(count.toString() + "\n");
                out.flush();
                count++;
            }

            count = 1;
            ServerSocket server = new ServerSocket(8889);
            socket = server.accept();
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            while (count <= 100) {
                //接收客户端发送的信息
                String str = in.readLine();
                System.out.println("客户端获取到服务端信息：" + str);
                count++;
                //如果客户端发送的是“end”则终止连接
                //否则，发送反馈信息
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (in != null) {
                try {
                    in.close();
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
        }
    }

}
