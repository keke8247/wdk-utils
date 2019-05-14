package com.wdk.util.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Description:TODO
 * @Author:wang_dk
 * @Date:2019/4/17 0017 20:19
 * @Version: v1.0
 **/

public class ServerHandler implements Runnable{

    private Selector selector; //多路复用器

    private ServerSocketChannel serverSocketChannel;

    private volatile static boolean flag;

    public ServerHandler(int port) {
        try {
            selector = Selector.open();

            serverSocketChannel = ServerSocketChannel.open();

            serverSocketChannel.configureBlocking(false);//非阻塞

            serverSocketChannel.socket().bind(new InetSocketAddress(port),1024);

            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);//注册到多路复用器  监听事件类型为接入

            flag = true;//服务启动标记

            System.out.println("服务端启动....端口:"+port);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop(){
        flag = false;
    }


    @Override
    public void run() {
        while (flag){
            try {

                //每隔一秒 轮训一下是否有key准备就绪
                int readySelectionKey = selector.select(1000);

                if(readySelectionKey == 0){
                    continue;
                }

                Set<SelectionKey> keys = selector.selectedKeys();

                Iterator<SelectionKey> iterator = keys.iterator();

                SelectionKey key = null;

                while (iterator.hasNext()){
                    key = iterator.next();

                    iterator.remove(); //移除Key 一直放在Set里面会重复分发

                    //业务处理

                    doHandler(key);
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void doHandler(SelectionKey key){
        //判断key是否可用
        if(key.isValid()){

            //判断key的事件类型
            if(key.isAcceptable()){ //连接事件
                try {
                    //TCP连接建立
                    SocketChannel sc = serverSocketChannel.accept(); //有客户端请求连接到服务端

                    sc.configureBlocking(false);

                    //注册到selector 为读事件
                    sc.register(selector,SelectionKey.OP_READ);

                    System.out.println("~~~~~~~~~~~有客户端建立连接成功~~~~~~~~~~~~~");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(key.isReadable()){ //读事件
                SocketChannel sc = (SocketChannel) key.channel();

                //接受客户端传送的数据
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                try {
                    int readBytes = sc.read(byteBuffer);

                    if(readBytes > 0){

                        byteBuffer.flip();

                        byte [] bytes = new byte[byteBuffer.remaining()]; //存放数据

                        byteBuffer.get(bytes);

                        String msg = new String(bytes);

                        System.out.println("客户端输入数据为:"+ new String(msg));

                        //回写数据到 client

                        doWrite(sc,msg);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }else{
            key.cancel(); //把Key失效
        }
    }

    public void doWrite(SocketChannel sc,String msg){
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        byteBuffer.put(("你好:"+msg).getBytes());

        byteBuffer.flip();

        try {
            sc.write(byteBuffer);
        } catch (IOException e) {
            try {
                sc.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
