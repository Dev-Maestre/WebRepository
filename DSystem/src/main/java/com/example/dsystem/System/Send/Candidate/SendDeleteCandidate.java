package com.example.dsystem.System.Send.Candidate;

import com.example.dsystem.Models.Connection;
import com.example.dsystem.Models.Model;
import com.example.dsystem.System.Send.Sender;
import com.example.dsystem.System.TokenManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public class SendDeleteCandidate extends Sender {
    public SendDeleteCandidate () {
        super();
        setOperation("DELETE_ACCOUNT_CANDIDATE");
    }

    public JsonNode generateDeleteCandidateData() throws JsonProcessingException {
        this.setToken(TokenManager.getToken());
        return generateFinalData();
    }

    public JsonNode send() throws JsonProcessingException {
        String response = null;
        try {
            Connection connection = Model.getInstance().getConnection();
            response = connection.send(objectMapper.writeValueAsString(generateDeleteCandidateData()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return toJsonNode(response);
    }
}
