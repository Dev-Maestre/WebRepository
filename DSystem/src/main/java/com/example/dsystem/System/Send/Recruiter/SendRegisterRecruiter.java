package com.example.dsystem.System.Send.Recruiter;

import com.example.dsystem.Models.Connection;
import com.example.dsystem.Models.Model;
import com.example.dsystem.System.Send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SendRegisterRecruiter extends Sender {

    public SendRegisterRecruiter() {
        super();
        setOperation("SIGNUP_RECRUITER");
    }

    public JsonNode generateRegisterRecruiterData(String email, String password, String name, String industry, String desc) throws JsonProcessingException {

        ((ObjectNode) this.getData()).put("email", email);
        ((ObjectNode) this.getData()).put("password", password);
        ((ObjectNode) this.getData()).put("name", name);
        ((ObjectNode) this.getData()).put("industry", industry);
        ((ObjectNode) this.getData()).put("description", desc);
        return generateFinalData();
    }

    public JsonNode send(String email, String password, String name, String industry, String desc) throws JsonProcessingException {
        String response = null;
        try {
            Connection connection = Model.getInstance().getConnection();
            response = connection.send(objectMapper.writeValueAsString(generateRegisterRecruiterData(name, email, password, industry, desc)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toJsonNode(response);
    }

}
