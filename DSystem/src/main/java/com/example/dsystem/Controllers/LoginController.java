package com.example.dsystem.Controllers;

import com.example.dsystem.Models.Model;
import com.example.dsystem.System.DataValidation;
import com.example.dsystem.System.Send.Candidate.SendLoginCandidate;
import com.example.dsystem.System.Send.Recruiter.SendLoginRecruiter;
import com.example.dsystem.System.Send.Candidate.SendLookupCandidate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.example.dsystem.System.Receive.Receiver;
import com.example.dsystem.System.TokenManager;


public class LoginController implements Initializable {
    @FXML
    private Button button1;
    @FXML
    private TextField loginTxtField;
    @FXML
    private TextField pswTxtField;
    @FXML
    private ChoiceBox<String> choiceBox1;
    @FXML
    private Label registerLabel;
    @FXML
    private Label errorLabel;

    private String selectedOption;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginTxtField.setText("user@user.com");
        pswTxtField.setText("User1234");

        choiceBox1.setItems(FXCollections.observableArrayList("CANDIDATE", "RECRUITER"));

        choiceBox1.setValue("CANDIDATE");
        selectedOption = "CANDIDATE";

        choiceBox1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {selectedOption = newValue;});

        registerLabel.setOnMouseClicked(event -> {
            try {
                Model.getInstance().getViewFactory().closeStage((Stage) registerLabel.getScene().getWindow());
                Model.getInstance().getViewFactory().showRegisterWindow();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        button1.setOnAction(event -> {
            if (selectedOption.equals("CANDIDATE")) {
                try {
                    onLoginCandidate();
                } catch (Exception e) {
                    setErrorLabel("Dados Inválidos");
                    throw new RuntimeException(e);
                }
            } else if (selectedOption.equals("RECRUITER")){
                try{
                    onLoginRecruiter();
                } catch (Exception e) {
                    setErrorLabel("Dados Inválidos");
                    throw new RuntimeException(e);
                }
            }
        });


    }

    private void onLoginCandidate() throws  Exception {
        // NEED TO FIX
        SendLoginCandidate sendLoginCandidate = new SendLoginCandidate();
        String email = loginTxtField.getText();
        String password = pswTxtField.getText();
        if (DataValidation.loginValidation(email, password)) {
            password = DigestUtils.md5Hex(password).toUpperCase();
            JsonNode response = sendLoginCandidate.send(email, password);
            if (response != null) {
                Receiver receiver = new Receiver(response);
                if(receiver.getStatus().equals("SUCCESS")) {
                    //String token = receiver.getToken();
                    //System.out.println(token);
                    TokenManager.saveToken(receiver.getToken());
                    onLookupCandidate(receiver.getToken());
                    if (TokenManager.isTokenAdmin(TokenManager.getToken())) {
                        Model.getInstance().getViewFactory().showAdminWindow();
                    } else {
                        Model.getInstance().getViewFactory().showCandidateWindow();}
                    Model.getInstance().getViewFactory().closeStage((Stage) button1.getScene().getWindow());
                } else {
                    //Model.getInstance().getViewFactory().showErrorWindow(receiver.getErrorMessage());
                }
            }
            return;
        }
        JsonNode response = sendLoginCandidate.send(email, password);
    }

    private void onLookupCandidate(String token) throws JsonProcessingException {
        SendLookupCandidate sendLookupCandidate = new SendLookupCandidate();
        JsonNode response = sendLookupCandidate.send(token);
        if (response != null) {
            Receiver receiver = new Receiver(response);
            if(receiver.getStatus().equals("SUCCESS")) {

            } else {
                System.out.println("Error in lookup candidate");
            }
        }
    }

    private void onLoginRecruiter() throws  Exception {
        SendLoginRecruiter sendLoginRecruiter = new SendLoginRecruiter();
        String email = loginTxtField.getText();
        String password = pswTxtField.getText();
        if (DataValidation.loginValidation(email, password)) {
            password = DigestUtils.md5Hex(password).toUpperCase();
            JsonNode response = sendLoginRecruiter.send(email, password);
            if (response != null) {
                Receiver receiver = new Receiver(response);
                if(receiver.getStatus().equals("SUCCESS")) {
                    String token = receiver.getToken();
                    //System.out.println(token);
                    TokenManager.saveToken(receiver.getToken());
                    if (TokenManager.isTokenAdmin(TokenManager.getToken())) {
                        Model.getInstance().getViewFactory().showAdminWindow();
                    } else {
                        Model.getInstance().getViewFactory().showRecruiterWindow();}
                    Model.getInstance().getViewFactory().closeStage((Stage) button1.getScene().getWindow());
                } else {
                    //Model.getInstance().getViewFactory().showErrorWindow(receiver.getErrorMessage());
                }
            }
            return;
        }
        JsonNode response = sendLoginRecruiter.send(email, password);
    }

    private void setErrorLabel(String message) {
        errorLabel.setText(message);
    }
}

