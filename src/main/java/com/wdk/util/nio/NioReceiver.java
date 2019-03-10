package com.wdk.util.nio;

import java.net.InetSocketAddress;  
import java.net.ServerSocket;  
import java.nio.ByteBuffer;  
import java.nio.channels.SelectionKey;  
import java.nio.channels.Selector;  
import java.nio.channels.ServerSocketChannel;  
import java.nio.channels.SocketChannel;  
import java.util.Iterator;  
import java.util.Set;  
  
public class NioReceiver {  
    @SuppressWarnings("null")  
    public static void main(String[] args) throws Exception {  
        ByteBuffer echoBuffer = ByteBuffer.allocate(1024);
        ServerSocketChannel ssc = ServerSocketChannel.open();  
        Selector selector = Selector.open();  
        ssc.configureBlocking(false);  
        ServerSocket ss = ssc.socket();  
        InetSocketAddress address = new InetSocketAddress(8080);  
        ss.bind(address);  
        SelectionKey key = ssc.register(selector, SelectionKey.OP_ACCEPT);  
        System.out.println("开始监听……");  
        while (true) {  
            int num = selector.select();  
            Set selectedKeys = selector.selectedKeys();  
            Iterator it = selectedKeys.iterator();  
            while (it.hasNext()) {  
                SelectionKey sKey = (SelectionKey) it.next();  
                SocketChannel channel = null;  
                if (sKey.isAcceptable()) {  
                    ServerSocketChannel sc = (ServerSocketChannel) key.channel();  
                    channel = sc.accept();// 接受连接请求  
                    channel.configureBlocking(false);  
                    channel.register(selector, SelectionKey.OP_READ);  
                    it.remove();  
                } else if (sKey.isReadable()) {  
                    channel = (SocketChannel) sKey.channel();

                    //创建ByteBuffer 开辟1M的缓冲区
                    ByteBuffer buffer = ByteBuffer.allocate(1024);

                    int readNums = channel.read(buffer);

                    if(readNums >0){
                        buffer.flip();

                        byte[] bytes = new byte[buffer.remaining()];

                        buffer.get(bytes);

                        String expression = new String(bytes,"UTF-8");
                        System.out.println("服务器收到消息：" + expression);

                        buffer.clear();

                        buffer.put("wocaonima".getBytes());

                        buffer.flip();

                        channel.write(buffer);
                    }else{
                        channel.close();
                        System.out.println("接收完毕，断开连接");
                        break;
                    }
                    it.remove();
                } else {  
                    channel.close();  
                }  
            }  
        }  
  
    }  
  
}  
