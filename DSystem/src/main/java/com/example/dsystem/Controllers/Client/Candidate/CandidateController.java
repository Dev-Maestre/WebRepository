package com.example.dsystem.Controllers.Client.Candidate;

import com.example.dsystem.Models.Model;
import com.example.dsystem.System.Receive.Receiver;
import com.example.dsystem.System.Send.Candidate.SendDeleteCandidate;
import com.example.dsystem.System.Send.Candidate.SendLogoutCandidate;
import com.fasterxml.jackson.databind.JsonNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class CandidateController implements Initializable {

    @FXML
    private Button logoutButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logoutButton.setOnAction(event -> {
            try {
                onLogout();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        editButton.setOnAction(event -> {
            try {
                Model.getInstance().getViewFactory().closeStage((Stage) editButton.getScene().getWindow());
                Model.getInstance().getViewFactory().showCandidateEditWindow();
                onEdit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        deleteButton.setOnAction(actionEvent -> {
            try {
                onDelete();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

    private void onLogout() throws IOException {
        SendLogoutCandidate sender = new SendLogoutCandidate();
        JsonNode response = sender.send();
        if (response != null) {
            Receiver receiver = new Receiver(response);
            if (receiver.getStatus().equals("SUCCESS")) {
                Model.getInstance().getViewFactory().closeStage((Stage) logoutButton.getScene().getWindow());
                Model.getInstance().getViewFactory().showLoginWindow();
            }
        }
    }

    private void onDelete() throws IOException {
        SendDeleteCandidate sender = new SendDeleteCandidate();
        JsonNode response = sender.send();
        if (response != null) {
            Receiver receiver = new Receiver(response);
            if (receiver.getStatus().equals("SUCCESS")) {
                Model.getInstance().getViewFactory().closeStage((Stage) deleteButton.getScene().getWindow());
                Model.getInstance().getViewFactory().showLoginWindow();
            }
        }

    }

    private void onEdit() {


    }

}
