package com.wdk.util.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Description:TODO
 * @Author:wang_dk
 * @Date:2019/4/17 0017 20:47
 * @Version: v1.0
 **/

public class ClientHandler implements Runnable{
    private String ip;

    private int port;

    private Selector selector;

    private SocketChannel socketChannel;

    private volatile static boolean flag;

    public ClientHandler(String ip,int port){
        this.ip = ip;

        this.port = port;

        try {
            selector = Selector.open();

            socketChannel = SocketChannel.open();

            socketChannel.configureBlocking(false);
        } catch (IOException e) {
            e.printStackTrace();
        }

        flag = true;

        System.out.println("客户端已启动......");

    }

    public void stop(){
        flag = false;
    }

    private void doConnection() throws IOException {
        if(socketChannel.connect(new InetSocketAddress(ip,port))){

        }else{
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }
    }

    @Override
    public void run() {
        try {
            doConnection();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        while (flag){
            try {
                int readySelectionKeys = selector.select(1000);

                if(readySelectionKeys == 0){
                    continue;
                }

                Set<SelectionKey> keys = selector.selectedKeys();

                Iterator<SelectionKey> iterator = keys.iterator();

                SelectionKey key = null;

                while (iterator.hasNext()){
                    key = iterator.next();

                    iterator.remove();

                    doHandler(key);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    private void doHandler(SelectionKey key) throws IOException {

        if(key.isValid()){
            SocketChannel sc = (SocketChannel) key.channel();

            if(key.isConnectable()){ //
                if(sc.finishConnect()){

                }else{
                    System.exit(1);
                }
            }

            //读消息
            if(key.isReadable()){
                //创建ByteBuffer，并开辟一个1M的缓冲区
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                //读取请求码流，返回读取到的字节数
                int readBytes = sc.read(buffer);
                //读取到字节，对字节进行编解码
                if(readBytes>0){
                    //将缓冲区当前的limit设置为position=0，用于后续对缓冲区的读取操作
                    buffer.flip();
                    //根据缓冲区可读字节数创建字节数组
                    byte[] bytes = new byte[buffer.remaining()];
                    //将缓冲区可读字节数组复制到新建的数组中
                    buffer.get(bytes);
                    String result = new String(bytes,"UTF-8");
                    System.out.println("客户端收到消息：" + result);
                }
                //没有读取到字节 忽略
//              else if(readBytes==0);
                //链路已经关闭，释放资源
                else if(readBytes<0){
                    key.cancel();
                    sc.close();
                }
            }

        }else{
            key.cancel();
        }
    }

    private void doWrite(String msg){
        //将消息编码为字节数组
        byte[] bytes = msg.getBytes();
        //根据数组容量创建ByteBuffer
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        //将字节数组复制到缓冲区
        writeBuffer.put(bytes);
        //flip操作
        writeBuffer.flip();
        //发送缓冲区的字节数组
        try {
            socketChannel.write(writeBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //****此处不含处理“写半包”的代码
    }

    public void sendMsg(String msg){
        try {
            socketChannel.register(selector,SelectionKey.OP_READ);
            doWrite(msg);
        } catch (ClosedChannelException e) {
            e.printStackTrace();
        }

    }
}
