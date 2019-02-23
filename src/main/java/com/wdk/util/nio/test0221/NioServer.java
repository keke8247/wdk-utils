package com.wdk.util.nio.test0221;

import com.wdk.util.design.pattern.chainResponsibility.Request;
import com.wdk.util.nio.test0219.RequestHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioServer {
    private static Selector selector;

    private static ByteBuffer byteBuffer ;

    private static RequestHandler requestHandler = new RequestHandler();

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();

        serverChannel.configureBlocking(false);
        serverChannel.bind(new InetSocketAddress(9999));

        System.out.println("NIO Server has started,listening on port:"+serverChannel.getLocalAddress());

        selector = Selector.open();

        //将代表服务器的chennel注册到selector上
        serverChannel.register(selector,SelectionKey.OP_ACCEPT);

        //开辟1K的缓冲区
        byteBuffer = ByteBuffer.allocate(1024);

        //轮训
        while(true){
            int readyChannelNums = selector.select(1000);//最多阻塞1秒
            if(readyChannelNums == 0) continue; //如果没有已经就绪的channel  跳出本次循环

            Set<SelectionKey> selectionKeySet = selector.selectedKeys();

            //迭代已经就绪的channel
            Iterator<SelectionKey> iterator = selectionKeySet.iterator();

            while (iterator.hasNext()){
                SelectionKey key = iterator.next();

                handler(key);

                iterator.remove();
            }
        }
    }

    private static void handler(SelectionKey key) throws IOException {
        if(key.isAcceptable()){ //等待连接就绪状态   此channel是ServerSocketChannel

            ServerSocketChannel channel = (ServerSocketChannel) key.channel();

            SocketChannel socketChannel = channel.accept();

            System.out.println("Connection from :"+socketChannel.getLocalAddress());

            //把socketChannel 设置为非阻塞
            socketChannel.configureBlocking(false);
            //把socketChannel 注册到Selector
            socketChannel.register(selector,SelectionKey.OP_READ);//注册监听读事件
        }

        if(key.isReadable()){ //可读状态
            SocketChannel socketChannel = (SocketChannel) key.channel();

            //把数据读到buffer里面
            socketChannel.read(byteBuffer);

            String request = new String(byteBuffer.array(),"UTF8").trim();

            //buffer公用 读完之后清空buffer  这里只是把游标还原到初始值
            byteBuffer.clear();

            System.out.println(String.format("From %s : %s",socketChannel.getLocalAddress(),request));

            String responce = requestHandler.doHandler(request);

            socketChannel.write(ByteBuffer.wrap(responce.getBytes()));
        }
    }
}
