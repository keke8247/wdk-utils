package com.wdk.util.nio.test0219;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable{
    private Socket clientSocket;
    private RequestHandler requestHandler;

    public ClientHandler(Socket clientSocket, RequestHandler requestHandler) {
        this.clientSocket = clientSocket;
        this.requestHandler = requestHandler;
    }

    @Override
    public void run() {
        while (true){

            try {
            System.out.println("Connection from "+clientSocket.getRemoteSocketAddress());

            Scanner scanner = new Scanner(clientSocket.getInputStream());

            String request = scanner.nextLine();

            if("quit".equals(request)){
                clientSocket.close();
                break;
            }

            System.out.println("client input "+request);

            String response = requestHandler.doHandler(request);

            clientSocket.getOutputStream().write(response.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
