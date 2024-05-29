package com.example.dsystem.System.Send.Candidate;

import com.example.dsystem.Models.Connection;
import com.example.dsystem.Models.Model;
import com.example.dsystem.System.Send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SendLoginCandidate extends Sender {

    public SendLoginCandidate() {
        super();
        setOperation("LOGIN_CANDIDATE");
    }

    public JsonNode generateLoginCandidateData(String email, String password) throws JsonProcessingException {
        ((ObjectNode) this.getData()).put("email", email);
        ((ObjectNode) this.getData()).put("password", password);
        return generateFinalData();
    }

    public JsonNode send(String email, String password) throws JsonProcessingException {
        String response = null;
        try {
           Connection connection = Model.getInstance().getConnection();
           response = connection.send(objectMapper.writeValueAsString(generateLoginCandidateData(email, password)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toJsonNode(response);
    }
}
