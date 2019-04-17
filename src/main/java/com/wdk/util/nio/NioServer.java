package com.wdk.util.nio;

/**
 * @Description:TODO
 * @Author:wang_dk
 * @Date:2019/4/17 0017 20:18
 * @Version: v1.0
 **/

public class NioServer {
    private static final int default_port = 10086;

    //业务处理器
    private static ServerHandler serverHandler;

    public static void start(){
        start(default_port);
    }

    public static void start(int port){
        if(serverHandler != null){
            serverHandler.stop();
        }

        new Thread(new ServerHandler(port),"NioServer").start();
    }

    public static void main(String[] args) {
        start();
    }
}
