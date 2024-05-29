package com.example.dsystemserver.System.Connection.Send;

import com.example.dsystemserver.Models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SendProfile extends Sender{
    public SendProfile() {
        super();
        setOperation("LOOKUP_ACCOUNT_CANDIDATE");
        setStatus("SUCCESS");
    }

    public JsonNode generateProfileData(User user) throws JsonProcessingException {
        ((ObjectNode)getData()).put("email", user.getEmail());
        ((ObjectNode)getData()).put("password", user.getPassword());
        ((ObjectNode)getData()).put("name", user.getName());

        return generateFinalData();
    }

    public String send(User userData) throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateProfileData(userData));
    }
}
