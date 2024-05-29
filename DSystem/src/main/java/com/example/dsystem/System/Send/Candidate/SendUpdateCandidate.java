package com.example.dsystem.System.Send.Candidate;

import com.example.dsystem.Models.Connection;
import com.example.dsystem.Models.Model;
import com.example.dsystem.System.Send.Sender;
import com.example.dsystem.System.TokenManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SendUpdateCandidate extends Sender {

    public SendUpdateCandidate() {
        super();
        setOperation("UPDATE_ACCOUNT_CANDIDATE");
    }

    public JsonNode generateUpdateCandidateData(String name, String email, String password) throws JsonProcessingException {
        this.setToken(TokenManager.getToken());
        ((ObjectNode) this.getData()).put("email", email);
        ((ObjectNode) this.getData()).put("password", password);
        ((ObjectNode) this.getData()).put("name", name);

        return generateFinalData();
    }

    public JsonNode send(String newName, String newEmail, String newPassword) throws JsonProcessingException {
        String response = null;
        try {
            Connection connection = Model.getInstance().getConnection();
            response = connection.send(objectMapper.writeValueAsString(generateUpdateCandidateData(newName, newEmail, newPassword)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toJsonNode(response);
    }
}
