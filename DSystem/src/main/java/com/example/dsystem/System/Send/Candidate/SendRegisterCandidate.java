package com.example.dsystem.System.Send.Candidate;

import com.example.dsystem.Models.Connection;
import com.example.dsystem.Models.Model;
import com.example.dsystem.System.Send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SendRegisterCandidate extends Sender {

    public SendRegisterCandidate() {
        super();
        setOperation("SIGNUP_CANDIDATE");
    }

    public JsonNode generateRegisterCandidateData(String name, String email, String password) throws JsonProcessingException {
        ((ObjectNode) this.getData()).put("email", email);
        ((ObjectNode) this.getData()).put("password", password);
        ((ObjectNode) this.getData()).put("name", name);
        return generateFinalData();
    }

    public JsonNode send(String name, String email, String password) throws JsonProcessingException {
        String response = null;
        try {
            Connection connection = Model.getInstance().getConnection();
            response = connection.send(objectMapper.writeValueAsString(generateRegisterCandidateData(name, email, password)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toJsonNode(response);
    }

}
