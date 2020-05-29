package udp;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    public static void main(String args[])throws IOException {
        byte[] buf = new byte[1024];
        Integer count = 1;
        //客户端在9000端口监听接收到的数据
        DatagramSocket ds = new DatagramSocket(9000);
        //定义用来发送数据的DatagramPacket实例
        //定义用来接收数据的DatagramPacket实例
        DatagramPacket dp_receive = new DatagramPacket(buf, 1024);
        //数据发向本地3000端口
        while(count <= 100){
            System.out.println("向服务端发生信息："+count);
            InetAddress loc = InetAddress.getLocalHost();
            DatagramPacket dp_send= new DatagramPacket(count.toString().getBytes(),count.toString().length(),loc,3000);
            ds.send(dp_send);
            count++;
        }
        count = 1;
        while(count <= 100){
            //服务器端接收来自客户端的数据
            ds.receive(dp_receive);
            System.out.print("客户端获取到服务端信息：");
            String str_receive = new String(dp_receive.getData(),0,dp_receive.getLength());
            System.out.println(str_receive);
            count++;
        }
        ds.close();
    }
}
