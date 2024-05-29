package com.example.dsystemserver.System.Connection.Send.Candidate;

import com.example.dsystemserver.System.Connection.Send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SendDeleteAccountCandidate extends Sender {

    public SendDeleteAccountCandidate() {
        super();
        setOperation("DELETE_ACCOUNT_CANDIDATE");
    }

    public JsonNode generateDeleteAccountCandidateData(String status) throws JsonProcessingException {
        setStatus(status);
        return generateFinalData();
    }

    public String send(String status) throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateDeleteAccountCandidateData(status));
    }

}
