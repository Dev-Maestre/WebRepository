package com.example.dsystem.Controllers.Client.Candidate;

import com.example.dsystem.Models.Model;
import com.example.dsystem.System.DataValidation;
import com.example.dsystem.System.Receive.Receiver;
import com.example.dsystem.System.Send.Candidate.SendUpdateCandidate;
import com.fasterxml.jackson.databind.JsonNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateCandidateController implements Initializable {

    @FXML
    private TextField nameTxt;
    @FXML
    private TextField emailTxt;
    @FXML
    private TextField passTxt;
    @FXML
    private Button editButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        editButton.setOnAction(event -> {
            try {
                Model.getInstance().getViewFactory().closeStage((Stage) editButton.getScene().getWindow());
                Model.getInstance().getViewFactory().showCandidateWindow();
                onUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void onUpdate() throws Exception {
        SendUpdateCandidate sender = new SendUpdateCandidate();
        String newName = nameTxt.getText();
        String newEmail = emailTxt.getText();
        String newPassword = passTxt.getText();
        try {
            if (DataValidation.userValidation(newName, newEmail, newPassword)) {
                newPassword = DigestUtils.md5Hex(newPassword).toUpperCase();
                JsonNode response = sender.send(newName, newEmail, newPassword);

                if (response != null) {
                    Receiver receiver = new Receiver(response);
                    if (receiver.getStatus().equals("SUCCESS")) {
                        Model.getInstance().getViewFactory().closeStage((Stage) editButton.getScene().getWindow());
                        Model.getInstance().getViewFactory().showCandidateWindow();
                    }
                    else {
                        throw new Exception("Error updating candidate");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
