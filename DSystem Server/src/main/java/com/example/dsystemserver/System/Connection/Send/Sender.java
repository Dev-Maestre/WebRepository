package com.example.dsystemserver.System.Connection.Send;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Sender {
    @JsonIgnore

    public final ObjectMapper objectMapper;
    private String operation;
    private String status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private JsonNode data;

    public Sender() {
        objectMapper = new ObjectMapper();
        data = objectMapper.createObjectNode();
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public JsonNode getData() {
        return data;
    }

    public void setData(JsonNode data) {
        this.data = data;
    }

    public JsonNode generateFinalData() throws JsonProcessingException {
        //if (getData().isEmpty()) {
            //setData(null);
        //}
        return objectMapper.convertValue(this, JsonNode.class);
    }


}
