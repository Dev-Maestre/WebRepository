package com.example.dsystemserver.System.Connection.Receive;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Receiver {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private String operation;
    private String token;
    private JsonNode data;

    public Receiver(JsonNode response) {
        this.operation = response.get("operation").asText();
        if (response.has("token")) {
            this.token = response.get("token").asText();
        }
        if (response.has("data")){
            this.data = response.get("data");
        } else {
            this.data = response;
        }
    }


    public Receiver (String operation, JsonNode data) {
        this.operation = operation;
        this.data = data;
    }

    public Receiver() {}

    public static JsonNode stringToMap(String json) throws JsonProcessingException {
        try {
            return objectMapper.readTree(json);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getToken() {
        return token;
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

    public String getIndustry() {
        if (data.has("industry"))
        {
            return data.get("industry").asText();
        }
        else {
            System.out.println("No industry found");
            return null;
        }
    }

    public String getDesc() {
        if (data.has("description"))
        {
            return data.get("description").asText();
        }
        else {
            System.out.println("No desc found");
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
