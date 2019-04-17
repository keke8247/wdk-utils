package com.wdk.util.nio;

import java.util.Scanner;

/**
 * @Description:TODO
 * @Author:wang_dk
 * @Date:2019/4/17 0017 20:45
 * @Version: v1.0
 **/

public class NioClient {
    private static final String ip = "127.0.0.1";

    private static final int port = 10086;

    private static ClientHandler clientHandler;

    public static void start(){
        start(ip,port);
    }

    public static void start(String ip,int port){
        if(null != clientHandler){
            clientHandler.stop();
        }

        clientHandler = new ClientHandler(ip,port);

        new Thread(clientHandler,"NioClient").start();
    }

    //向服务器发送消息
    public static boolean sendMsg(String msg) throws Exception{
        if(msg.equals("q")){
            clientHandler.stop();
            return false;
        }
        clientHandler.sendMsg(msg);
        return true;
    }

    public static void main(String[] args) throws Exception {
        start();
        System.out.println("请输入.....");
        while (sendMsg(new Scanner(System.in).nextLine()));
    }

}
