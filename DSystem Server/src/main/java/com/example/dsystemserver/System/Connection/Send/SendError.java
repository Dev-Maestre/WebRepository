package com.example.dsystemserver.System.Connection.Send;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public class SendError extends Sender {
    //public SendError(){
       // super();
    //}

    public SendError(String operation, String status) {
        super();
        setOperation(operation);
        setStatus(status);
    }


    public JsonNode generateErrorData(String operation, String status) throws JsonProcessingException, JsonProcessingException {
        setOperation(operation);
        setStatus(status);
        return generateFinalData();
    }

    public String send(String operation, String status) throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateErrorData(operation, status));
    }
}
