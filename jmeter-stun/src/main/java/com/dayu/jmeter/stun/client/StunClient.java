package com.dayu.jmeter.stun.client;

import org.ice4j.Transport;
import org.ice4j.TransportAddress;
import org.ice4j.message.MessageFactory;
import org.ice4j.message.Request;
import org.ice4j.socket.IceSocketWrapper;
import org.ice4j.socket.IceUdpSocketWrapper;
import org.ice4j.stack.StunStack;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class StunClient {

    public static void send() throws Exception{
        // STUN 服务器地址和端口
        String stunServerAddress = "127.0.0.1"; // STUN 服务器地址
        int stunServerPort = 3478; // STUN 服务器端口

        // 获取本机 IP 地址
        InetAddress localAddress = InetAddress.getByName("127.0.0.1"); // 指定本机 IP 地址

        // 创建一个 UDP 套接字
        DatagramSocket socket = new DatagramSocket(0, localAddress);

        // 创建 STUN Stack
        StunStack stunStack = new StunStack();

        // 创建本地地址和服务器地址
        TransportAddress localTransportAddress = new TransportAddress(localAddress, socket.getLocalPort(), Transport.UDP);
        TransportAddress serverAddress = new TransportAddress(InetAddress.getByName(stunServerAddress), stunServerPort, Transport.UDP);

        // 创建 IceSocketWrapper 实例
        IceSocketWrapper socketWrapper = new IceUdpSocketWrapper(socket);

        // 将套接字添加到 STUN Stack
        stunStack.addSocket(socketWrapper, serverAddress);

        // 创建 STUN 请求
        Request bindingRequest = MessageFactory.createBindingRequest();

        // 创建响应处理器
        Response response = new Response();

        // 发送 STUN 请求
        stunStack.sendRequest(bindingRequest, serverAddress, localTransportAddress, response);

        // 等待响应
//        DatagramPacket packet = new DatagramPacket(new byte[512], 512);
//        socket.receive(packet);
    }
}
