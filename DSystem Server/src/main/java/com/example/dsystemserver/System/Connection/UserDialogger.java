package com.example.dsystemserver.System.Connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.sql.SQLException;

import com.example.dsystemserver.Models.Model;
import com.example.dsystemserver.System.Connection.Receive.UserDialogActions;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserDialogger implements Runnable{
    private final Socket socket;
    private final ObjectMapper objectMapper;


    public UserDialogger(Socket socket) {
        this.socket = socket;
        this.objectMapper = new ObjectMapper();
    }

    public boolean isClientConnected() {
        return this.socket.isConnected();
    }

    public void run() {
        InetSocketAddress socketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
        String clientIP = socketAddress.getAddress().getHostAddress();

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            UserDialogActions userDialogActions = new UserDialogActions(clientIP);
            Model.getInstance().getSessionManager().addSession(clientIP);
            String message = null;
            while ((message = in.readLine()) != null) {
                JsonNode jsonNode = objectMapper.readTree(message);
                String prettyJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
                System.out.println("Received message from " + clientIP + ": " + prettyJson);
                String operation = jsonNode.get("operation").asText();
                String response = userDialogActions.chooseAction(operation, message);
                jsonNode = objectMapper.readTree(response);
                prettyJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
                System.out.println("Sending message to " + clientIP + ": " + prettyJson);
                out.println(response);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
