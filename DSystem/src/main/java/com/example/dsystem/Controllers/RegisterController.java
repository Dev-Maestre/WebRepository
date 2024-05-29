package com.example.dsystem.Controllers;

import com.example.dsystem.Models.Model;
import com.example.dsystem.System.DataValidation;
import com.example.dsystem.System.Receive.Receiver;
import com.example.dsystem.System.Send.Candidate.SendRegisterCandidate;
import com.example.dsystem.System.Send.Recruiter.SendRegisterRecruiter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    @FXML
    private Button Button1;
    @FXML
    private Button toBackButton;
    @FXML
    private TextField nameTxtField;
    @FXML
    private TextField emailTxtField;
    @FXML
    private ChoiceBox<String> ChoiceBox1;
    @FXML
    private TextField PswTxtField;
    @FXML
    private TextField indstTxtField;
    @FXML
    private TextField dscTxtField;

    private String selectedOption;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ChoiceBox1.setItems(FXCollections.observableArrayList("CANDIDATE", "RECRUITER"));

        ChoiceBox1.setValue("CANDIDATE");
        selectedOption = "CANDIDATE";

        indstTxtField.setDisable(true);
        dscTxtField.setDisable(true);

        ChoiceBox1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedOption = newValue;
            if ("CANDIDATE".equals(selectedOption)) {
                indstTxtField.setDisable(true);
                dscTxtField.setDisable(true);
            } else  {
                indstTxtField.setDisable(false);
                dscTxtField.setDisable(false);
            }
        });

        Button1.setOnAction(event -> {
            if ("CANDIDATE".equals((selectedOption)))
            {
                try {
                    onRegisterCandidate();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            else if ("RECRUITER".equals(selectedOption))
            {
                try {
                    onRegisterRecruiter();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        toBackButton.setOnAction(event -> {
            Stage stage = (Stage) toBackButton.getScene().getWindow();
            stage.close();
            try {
                Model.getInstance().getViewFactory().showLoginWindow();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

    private void onRegisterRecruiter() throws Exception {
        SendRegisterRecruiter sender = new SendRegisterRecruiter();
        String name = nameTxtField.getText();
        String email = emailTxtField.getText();
        String password = PswTxtField.getText();
        String industry = indstTxtField.getText();
        String desc = dscTxtField.getText();

        if (DataValidation.registerRecruiterValidation(name, email, password, industry, desc)) {
            password = DigestUtils.md5Hex(password).toUpperCase();
            JsonNode response = sender.send(name, email, password, industry, desc);
            if (response != null) {
                Receiver receiver = new Receiver(response);
                if (receiver.getStatus().equals("SUCCESS")) {
                    nameTxtField.setText("");
                    emailTxtField.setText("");
                    PswTxtField.setText("");
                } else {
                    Exception e = new Exception("Erro ao cadastrar");
                }
            }
        }
    }

    public void onRegisterCandidate() throws Exception {
        SendRegisterCandidate sender = new SendRegisterCandidate();
        String name = nameTxtField.getText();
        String email = emailTxtField.getText();
        String password = PswTxtField.getText();

        if (DataValidation.registerCandidateValidation(name, email, password)) {
            password = DigestUtils.md5Hex(password).toUpperCase();
            JsonNode response = sender.send(name, email, password);
            if (response != null) {
                Receiver receiver = new Receiver(response);
                if (receiver.getStatus().equals("SUCCESS")) {
                    nameTxtField.setText("");
                    emailTxtField.setText("");
                    PswTxtField.setText("");
                } else {
                    Exception e = new Exception("Erro ao cadastrar");
                }
            }
        }
    }
}
