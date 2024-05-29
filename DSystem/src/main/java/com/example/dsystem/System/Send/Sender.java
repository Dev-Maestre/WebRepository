package com.example.dsystem.System.Send;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class Sender {
    @JsonIgnore
    public static ObjectMapper objectMapper = null;
    private String operation;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String token;
    private JsonNode data;

    public Sender() {
        objectMapper = new ObjectMapper();
        data = objectMapper.createObjectNode();
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken () {
        return token;
    }

    // vo la na sua casa ve um negocio hj

    public void setData(JsonNode data) {
        this.data = data;
    }

    public String getOperation() {
        return operation;
    }

    public JsonNode getData() {
        return data;
    }

    public JsonNode generateFinalData()  throws JsonProcessingException {

        return objectMapper.convertValue(this, JsonNode.class);
    }

    public static JsonNode toJsonNode(String json) throws JsonProcessingException {
        if (json == null || json.isEmpty()) return null;
        return objectMapper.readTree(json);
    }
}
