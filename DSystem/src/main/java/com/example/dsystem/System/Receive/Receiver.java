package com.example.dsystem.System.Receive;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Receiver {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private String operation;
    private String status;
    private String token;
    private JsonNode data;

    public Receiver(JsonNode response) {
        if (response != null) {
            if (response.has("operation")) {
                this.operation = response.get("operation").asText();
            }
            this.status = response.get("status").asText();
            if (response.has("data")) {
                this.data = response.get("data");
            }
            if (response.has("token")) {
                this.token = response.get("token").asText();
            }
        }
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setData(JsonNode data) {
        this.data = data;
    }

    public String getOperation() {
        return operation;
    }

    public String getStatus() {
        return status;
    }

    public String getEmail() {
        if (data.has("email")) {
            return data.get("email").asText();
        } else
            return null;
    }

    public String getPassword() {
        if (data.has("password")) {
            return data.get("password").asText();
        } else
            return null;
    }

    public String getToken() {
        if (data.has("token"))
            return data.get("token").asText();
        else
            return null;
    }

    public JsonNode getData() {
        return data;
    }
}
