package com.dayu.jmeter.stun.server;

import com.dayu.jmeter.stun.client.Response;
import org.ice4j.Transport;
import org.ice4j.TransportAddress;
import org.ice4j.message.MessageFactory;
import org.ice4j.socket.IceSocketWrapper;
import org.ice4j.socket.IceUdpSocketWrapper;
import org.ice4j.stack.StunStack;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {

    public static void main(String[] args) {
        try {
            int num = 0;
            // 获取本机地址
            InetAddress localAddress = InetAddress.getByName("0.0.0.0"); // 绑定到所有可用地址

            // 创建 UDP 套接字并绑定到 3478 端口
            DatagramSocket serverSocket = new DatagramSocket(3478, localAddress);
            TransportAddress localTransportAddress = new TransportAddress(localAddress, 3478, Transport.UDP);

            // 创建 STUN Stack
            StunStack stunStack = new StunStack();

            // 创建 IceSocketWrapper 实例
            IceSocketWrapper socketWrapper = new IceUdpSocketWrapper(serverSocket);

            // 将套接字添加到 STUN Stack
            stunStack.addSocket(socketWrapper, localTransportAddress);

            System.out.println("STUN server started on port 3478");

            // 服务器循环运行，接收请求
            while (true) {
                DatagramPacket packet = new DatagramPacket(new byte[512], 512);
                serverSocket.receive(packet);
                InetAddress clientAddress = packet.getAddress();
                int clientPort = packet.getPort();
                num++;
                System.out.println("now total : " + num + ", Received packet from " + clientAddress + ":" + clientPort);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
