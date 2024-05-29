package com.example.dsystemserver.System.Connection.Send.Recruiter;

import com.example.dsystemserver.System.Connection.Send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SendRecruiterLogin extends Sender {
    public SendRecruiterLogin() {
        super();
        setOperation("LOGIN_RECRUITER");
    }

    public JsonNode generateLoginData(String token, String status) throws JsonProcessingException {
        ((ObjectNode) getData()).put("token", token);
        setStatus(status);
        return generateFinalData();
    }

    public String send(String token, String status) throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateLoginData(token, status));
    }
}
