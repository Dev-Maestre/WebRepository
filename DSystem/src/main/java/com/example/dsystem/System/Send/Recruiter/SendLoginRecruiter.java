package com.example.dsystem.System.Send.Recruiter;

import com.example.dsystem.Models.Connection;
import com.example.dsystem.Models.Model;
import com.example.dsystem.System.Send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SendLoginRecruiter extends Sender {

    public SendLoginRecruiter() {
        super();
        setOperation("LOGIN_RECRUITER");
    }

    public JsonNode generateLoginRecruiterData(String email, String password) throws JsonProcessingException {
        ((ObjectNode) this.getData()).put("email", email);
        ((ObjectNode) this.getData()).put("password", password);
        return generateFinalData();
    }

    public JsonNode send(String email, String password) throws JsonProcessingException {
        String response = null;
        try {
            Connection connection = Model.getInstance().getConnection();
            response = connection.send(objectMapper.writeValueAsString(generateLoginRecruiterData(email, password)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toJsonNode(response);
    }
}
