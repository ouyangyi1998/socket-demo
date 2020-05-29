package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
    public static void main(String[] args)throws IOException {
        String str_send = "Hello UDPclient";
        byte[] buf = new byte[1024];
        //服务端在3000端口监听接收到的数据
        DatagramSocket ds = new DatagramSocket(3000);
        //接收从客户端发送过来的数据
        DatagramPacket dp_receive = new DatagramPacket(buf, 1024);
        System.out.println("服务端已启动，等待信息：");
        Integer count=1;
        while(count <= 100){
            //服务器端接收来自客户端的数据
            ds.receive(dp_receive);
            System.out.print("服务端获取到客户端信息：");
            String str_receive = new String(dp_receive.getData(),0,dp_receive.getLength());
            System.out.println(str_receive);
            count++;
        }
        count=1;
        while(count <= 100){
            System.out.println("向客户端发生信息："+count);
            InetAddress loc = InetAddress.getLocalHost();
            DatagramPacket dp_send= new DatagramPacket(count.toString().getBytes(),count.toString().length(),loc,9000);
            ds.send(dp_send);
            count++;
        }
        ds.close();
    }
}
