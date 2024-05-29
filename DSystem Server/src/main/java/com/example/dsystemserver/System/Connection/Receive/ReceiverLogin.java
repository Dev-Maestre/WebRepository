package com.example.dsystemserver.System.Connection.Receive;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReceiverLogin {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private String operation;
    private JsonNode data;

    public ReceiverLogin(JsonNode response) {
        this.operation = response.get("operation").asText();
        if (response.has("data")){
            this.data = response.get("data");
        } else {
            this.data = response;
        }
    }

    public ReceiverLogin (String operation, JsonNode data) {
        this.operation = operation;
        this.data = data;
    }

    public ReceiverLogin() {}

    public static JsonNode stringToMap(String json) throws JsonProcessingException {
        try {
            return objectMapper.readTree(json);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getName() {
        if (data.has("name"))
        {
            return data.get("name").asText();
        }
        else {
            System.out.println("No name found");
            return null;
        }
    }

    public String getEmail() {
        if (data.has("email"))
        {
            return data.get("email").asText();
        }
        else {
            System.out.println("No email found");
            return null;
        }
    }

    public String getPassword() {
        if (data.has("password"))
        {
            return data.get("password").asText();
        }
        else {
            System.out.println("No password found");
            return null;
        }
    }

    public int getUserID() {
        if (data.has("userID"))
        {
            return data.get("userID").asInt();
        }
        else {
            System.out.println("No userID found");
            return 0;
        }
    }

    public String getRole() {
        if (data.has("role"))
        {
            return data.get("role").asText();
        }
        else {
            System.out.println("No role found");
            return null;
        }
    }

    public String getOperation() { return operation; }

}
