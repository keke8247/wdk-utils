package com.wdk.util.nio.test0219;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IODemo {
    public static void main(String[] args) {
        RequestHandler requestHandler = new RequestHandler();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        try(ServerSocket serverSocket = new ServerSocket(8888)){

            System.out.println("BioServer start:"+serverSocket.getLocalSocketAddress());

            while (true){
                Socket clientSocket = serverSocket.accept();

                executorService.execute(new ClientHandler(clientSocket,requestHandler));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
