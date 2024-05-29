package com.example.dsystemserver.System.Connection.Send.Candidate;

import com.example.dsystemserver.System.Connection.Send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public class SendLogoutRecruiter extends Sender {
    public SendLogoutRecruiter() {
        super();
        setOperation("LOGOUT_RECRUITER");
    }

    public JsonNode generateLogoutData() throws JsonProcessingException {
        return generateFinalData();
    }
    public String send() throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateLogoutData());
    }
}
