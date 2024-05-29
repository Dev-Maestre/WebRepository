package com.example.dsystemserver.System.Connection.Send.Candidate;

import com.example.dsystemserver.System.Connection.Send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public class SendLogoutCandidate extends Sender {
    public SendLogoutCandidate() {
        super();
        setOperation("LOGOUT_CANDIDATE");
        setStatus("SUCCESS");
    }

    public JsonNode generateLogoutData() throws JsonProcessingException {
        return generateFinalData();
    }
    public String send() throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateLogoutData());
    }
}
