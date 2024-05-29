package com.example.dsystemserver.System.Connection;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

    private final int PORT;

    public Server(int port) {
        this.PORT = port;
    }

    @Override
    public void run() {
        Socket socket = null;
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(this.PORT);
            System.out.println("PORT: " + PORT);
            System.out.println("Waiting for client connection...");
            while (true) {
                socket = serverSocket.accept();
                System.out.println("Client Connected !");
                Thread clientThread = new Thread(new UserDialogger(socket));
                clientThread.start();

                if (isClientDisconnected(socket)) {
                    System.out.println("Client Disconnected !");
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (socket != null && !socket.isClosed()) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }
    public boolean isClientDisconnected(Socket socket) {
        return socket.isClosed();
    }
}