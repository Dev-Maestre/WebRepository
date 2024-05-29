package com.example.dsystemserver.System.Connection.Send.Recruiter;

import com.example.dsystemserver.System.Connection.Send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public class SendRegisterRecruiter extends Sender {
    public SendRegisterRecruiter() {
        super();
        setOperation("SIGNUP_RECRUITER");
    }

    public JsonNode generateRegisterRecruiterData(String status) throws JsonProcessingException {
        setStatus(status);
        return generateFinalData();
    }

    public String send(String status) throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateRegisterRecruiterData(status));
    }
}
