package com.example.dsystemserver.System.Connection.Send.Candidate;

import com.example.dsystemserver.System.Connection.Send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public class SendRegisterCandidate extends Sender {



    public SendRegisterCandidate() {
        super();
        setOperation("SIGNUP_CANDIDATE");
    }

    public JsonNode generateRegisterCandidateData(String status) throws JsonProcessingException {
        setStatus(status);
        return generateFinalData();
    }

    public String send(String status) throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateRegisterCandidateData(status));
    }
}
