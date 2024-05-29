package com.example.dsystem.System.Send.Candidate;

import com.example.dsystem.Models.Connection;
import com.example.dsystem.Models.Model;
import com.example.dsystem.System.Send.Sender;
import com.example.dsystem.System.TokenManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;


public class SendLookupCandidate extends Sender {
    public SendLookupCandidate() {
        super();
        setOperation("LOOKUP_ACCOUNT_CANDIDATE");
    }

    public JsonNode generateLookupCandidateData() throws JsonProcessingException {
        this.setToken(TokenManager.getToken());
        return generateFinalData();
    }


    public JsonNode send(String token) throws JsonProcessingException {
        String response = null;
        try {
            Connection connection = Model.getInstance().getConnection();
            response = connection.send(objectMapper.writeValueAsString(generateLookupCandidateData()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toJsonNode(response);
    }
}
