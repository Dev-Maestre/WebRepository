package com.example.dsystemserver.System.Connection.Send.Candidate;

import com.example.dsystemserver.System.Connection.Send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public class SendUpdateAccountCandidate extends Sender {
    public SendUpdateAccountCandidate() {
        super();
        setOperation("UPDATE_ACCOUNT_CANDIDATE");
        setStatus("SUCCESS");
    }

    public JsonNode generateUpdateAccountCandidateData() throws JsonProcessingException {
        return generateFinalData();
    }

    public String send() throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateUpdateAccountCandidateData());
    }


}
