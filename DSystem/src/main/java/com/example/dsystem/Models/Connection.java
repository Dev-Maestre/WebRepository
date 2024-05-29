package com.example.dsystem.Models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.Socket;

public class Connection {
    private final ObjectMapper objectMapper = new ObjectMapper();
    Socket socket;
    OutputStream out = null;
    InputStream in = null;

    public void connect(String ip) {
        try {
            socket = new Socket(ip, 21234);
            out = socket.getOutputStream();
            in = socket.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String send(String message) {
        String response = null;
        while (true) {
            try {
                PrintWriter writer = new PrintWriter(out, true);
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                JsonNode jsonNode = objectMapper.readTree(message);
                String prettyJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
                writer.println(message);
                System.out.println("Sending to Server: \n" + prettyJson);
                if ((response = reader.readLine()) != null) {
                    jsonNode = objectMapper.readTree(response);
                    prettyJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
                    System.out.println("Received from Server: \n" + prettyJson);
                    break;
                    }

                }
            catch (JsonMappingException e) {
                throw new RuntimeException(e);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return response;
    }

    public void close() {
        try {
            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
