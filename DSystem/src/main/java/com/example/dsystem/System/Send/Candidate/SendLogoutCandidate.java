package com.example.dsystem.System.Send.Candidate;

import com.example.dsystem.Models.Connection;
import com.example.dsystem.Models.Model;
import com.example.dsystem.System.Send.Sender;
import com.example.dsystem.System.TokenManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SendLogoutCandidate extends Sender {

    public SendLogoutCandidate() {
        super();
        setOperation("LOGOUT_CANDIDATE");
    }

    public JsonNode generateLogoutCandidateData() throws JsonProcessingException {
        this.setToken(TokenManager.getToken());
        //((ObjectNode) this.getData()).put("token", TokenManager.getToken());
        return generateFinalData();
    }

    public JsonNode send() throws JsonProcessingException {
        String response = null;
        try {
            Connection connection = Model.getInstance().getConnection();
            response = connection.send(objectMapper.writeValueAsString(generateLogoutCandidateData()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toJsonNode(response);
    }
}
