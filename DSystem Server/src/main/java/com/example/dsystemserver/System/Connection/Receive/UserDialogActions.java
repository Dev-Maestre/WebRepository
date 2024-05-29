package com.example.dsystemserver.System.Connection.Receive;

import com.example.dsystemserver.Models.Model;
import com.example.dsystemserver.Models.User;
import com.example.dsystemserver.System.Connection.JWTManager;
import com.example.dsystemserver.System.Connection.Send.*;
import com.example.dsystemserver.System.Connection.Send.Candidate.*;
import com.example.dsystemserver.System.Connection.Send.Recruiter.SendRecruiterLogin;
import com.example.dsystemserver.System.Connection.Send.Recruiter.SendRegisterRecruiter;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.sql.SQLException;

public class UserDialogActions {
    public final String ip;

    public UserDialogActions(String ip) {
        this.ip = ip;
    }

    public String chooseAction(String operation, String data) throws JsonProcessingException, SQLException {
        String response = null;
        switch (operation) {
            case "LOGIN_CANDIDATE":
                return response = manageLoginCandidate(data);
            case "LOGOUT_CANDIDATE":
                return response = manageLogoutCandidate(data);
            case "LOGIN_RECRUITER":
                return response = manageLoginRecruiter(data);
            case "LOGOUT_RECRUITER":
                return response = manageLogoutRecruiter(data);
            case "SIGNUP_CANDIDATE":
                return response = manageRegisterCandidate(data);
            case "SIGNUP_RECRUITER":
                return response = manageRegisterRecruiter(data);
            case "LOOKUP_ACCOUNT_CANDIDATE":
                return response = manageLookup(data);
            case "UPDATE_ACCOUNT_CANDIDATE":
                return response = manageUpdateAccountCandidate(data);
            case "DELETE_ACCOUNT_CANDIDATE":
                return response = manageDeleteAccountCandidate(data);
            default:
                return response = manageError(operation, "INVALID_OPERATION");
        }
    }

    private String manageRegisterRecruiter(String data) throws JsonProcessingException, SQLException {
        Receiver request = new Receiver(Receiver.stringToMap(data));
        String response = null;
        if (request.getName() == null || request.getEmail() == null || request.getPassword() == null || request.getIndustry() == null || request.getDesc() == null ||request.getName().isEmpty() || request.getEmail().isEmpty() || request.getPassword().isEmpty() || request.getIndustry().isEmpty() || request.getDesc().isEmpty())
        {
            response = manageError(request.getOperation(), "INVALID_FIELD");
            return response;
        }
        SendRegisterRecruiter sender = new SendRegisterRecruiter();
        User existingUser = Model.getInstance().getDatabaseDriver().getRecruiterByEmail(request.getEmail());
        if (existingUser != null){
            response = manageError(request.getOperation(), "USER_EXISTS");
            return response;
        }
        User user = new User(request.getName(), request.getEmail(), JWTManager.hashPassword(request.getPassword()), request.getIndustry(), request.getDesc(), request.getUserID());
        Model.getInstance().getDatabaseDriver().addRecruiter(user);
        response = sender.send("SUCCESS");
        return response;
    }

    private String manageDeleteAccountCandidate(String data) throws JsonProcessingException, SQLException {
        Receiver request = new Receiver(Receiver.stringToMap(data));
        String response = null;
        if (request.getToken() == null || request.getToken().isEmpty())
        {
            response = manageError(request.getOperation(), "INVALID_TOKEN");
            return response;
        }
        User user = Model.getInstance().getDatabaseDriver().getCandidateByToken(request.getToken());
        if (user != null) {
            Model.getInstance().getDatabaseDriver().deleteCandidate(user);
            SendDeleteAccountCandidate sender = new SendDeleteAccountCandidate();
            response = sender.send("SUCCESS");
        } else {
            throw new RuntimeException("Invalid User");
        }
        return response;
    }

    private String manageUpdateAccountCandidate(String data) throws JsonProcessingException, SQLException {
        Receiver request = new Receiver(Receiver.stringToMap(data));
        String response = null;
        if (request.getToken() == null || request.getToken().isEmpty())
        {
            response = manageError(request.getOperation(), "INVALID_TOKEN");
            return response;
        }
        if (request.getName() == null || request.getName().isEmpty() || request.getEmail() == null || request.getEmail().isEmpty() || request.getPassword() == null || request.getPassword().isEmpty())
        {
            response = manageError(request.getOperation(), "INVALID_FIELD");
            return response;
        }
        User user = Model.getInstance().getDatabaseDriver().getCandidateByToken(request.getToken());

        if (user != null) {
            User existingUser = Model.getInstance().getDatabaseDriver().getCandidateByEmail(request.getEmail());
            if (existingUser != null && existingUser.getID() != user.getID()){
                response = manageError(request.getOperation(), "INVALID_EMAIL");
                return response;
            }
            User newUser = new User(request.getName(), request.getEmail(), JWTManager.hashPassword(request.getPassword()), "CANDIDATE", user.getID());
            Model.getInstance().getDatabaseDriver().updateCandidate(newUser);
            SendUpdateAccountCandidate sender = new SendUpdateAccountCandidate();
            response = sender.send();
        } else {
            response = manageError(request.getOperation(), "INVALID_EMAIL");
        }
        return response;
    }

    private String manageLookup(String data) throws JsonProcessingException, SQLException {
        Receiver request = new Receiver(Receiver.stringToMap(data));
        //System.out.println(request.getToken());
        String response = null;
        if (request.getToken() == null || request.getToken().isEmpty())
        {
            response = manageError(request.getOperation(), "INVALID_TOKEN");
            return response;
        }
        User user = Model.getInstance().getDatabaseDriver().getCandidateByToken(request.getToken());
        if (user != null) {
            SendProfile sender = new SendProfile();
            response = sender.send(user);
        } else {
            throw new SQLException("Invalid token");
        }
        return response;
    }


    private String manageLoginCandidate(String data) throws JsonProcessingException, SQLException {
        Receiver request = new Receiver(Receiver.stringToMap(data));
        String response = null;
        if (request.getEmail() == null || request.getPassword() == null || request.getEmail().isEmpty() || request.getPassword().isEmpty())
        {
            response = manageError(request.getOperation(), "INVALID_FIELD");
            return response;
        }
        User user = Model.getInstance().getDatabaseDriver().getCandidateLogin(request.getEmail(), request.getPassword());
        if (user == null)
        {
            response = manageError(request.getOperation(), "INVALID_LOGIN");
            return response;
        }
        boolean isValid = JWTManager.checkPassword(request.getPassword(), user.getPassword());
        if (user != null) {
            if (isValid) {
                SendLoginCandidate sender = new SendLoginCandidate();
                String token = JWTManager.generateToken(user.getID(), "CANDIDATE");
                response = sender.send(token, "SUCCESS");
                Model.getInstance().getSessionManager().updateSession(ip, token, user);
            } else {
                response = manageError(request.getOperation(), "INVALID_LOGIN");
            }
        } else {
            response = manageError(request.getOperation(), "INVALID_LOGIN");
        }

        return response;
    }

    private String manageLogoutCandidate(String data) throws JsonProcessingException, SQLException {
        Receiver request = new Receiver(Receiver.stringToMap(data));
        String token = request.getToken();

        if (Model.getInstance().getSessionManager().validateSession(token))
        {
            Model.getInstance().getSessionManager().removeLoginSessions(ip);
            SendLogoutCandidate sender = new SendLogoutCandidate();
            return sender.send();
        }
        return manageError(request.getOperation(), "INVALID_TOKEN");
    }

    private String manageLoginRecruiter(String data) throws SQLException, JsonProcessingException {
        Receiver request = new Receiver(Receiver.stringToMap(data));
        String response = null;
        User user = Model.getInstance().getDatabaseDriver().getRecruiterLogin(request.getEmail(), request.getPassword());
        if (user != null) {
            if (JWTManager.checkPassword(request.getPassword(), user.getPassword())) {
                SendRecruiterLogin sender = new SendRecruiterLogin();
                String token = JWTManager.generateToken(user.getID(), "RECRUITER");
                response = sender.send(token, "SUCCESS");
                Model.getInstance().getSessionManager().updateSession(ip, token, user);
            } else {
                response = manageError(request.getOperation(), "INVALID_LOGIN");
            }
        } else {
            response = manageError(request.getOperation(), "INVALID_LOGIN");
        }
        return response;
    }

    private String manageLogoutRecruiter(String data) throws JsonProcessingException, SQLException {
        Receiver request = new Receiver(Receiver.stringToMap(data));
        String token = request.getToken();

        if (Model.getInstance().getSessionManager().validateSession(token))
        {
            Model.getInstance().getSessionManager().removeLoginSessions(ip);
            SendLogoutRecruiter sender = new SendLogoutRecruiter();
            return sender.send();
        }
        return manageError(request.getOperation(), "");
    }

    private String manageRegisterCandidate(String data) throws JsonProcessingException, SQLException {
        Receiver request = new Receiver(Receiver.stringToMap(data));
        String response = null;
        if (request.getName() == null || request.getEmail() == null || request.getPassword() == null || request.getName().isEmpty() || request.getEmail().isEmpty() || request.getPassword().isEmpty())
        {
            response = manageError(request.getOperation(), "INVALID_FIELD");
            return response;
        }
        SendRegisterCandidate sender = new SendRegisterCandidate();
        User existingUser = Model.getInstance().getDatabaseDriver().getCandidateByEmail(request.getEmail());
        if (existingUser != null){
            response = manageError(request.getOperation(), "USER_EXISTS");
            return response;
        }
        User user = new User(request.getName(), request.getEmail(), JWTManager.hashPassword(request.getPassword()));
        Model.getInstance().getDatabaseDriver().addCandidate(user);
        response = sender.send("SUCCESS");
        return response;
    }

    private String manageError(String operation, String message) throws JsonProcessingException {
        SendError sender = new SendError(operation, message);
        return sender.send(operation, message);
    }
}

