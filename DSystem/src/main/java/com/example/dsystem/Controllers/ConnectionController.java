package com.example.dsystem.Controllers;

import com.example.dsystem.Models.Connection;

import com.example.dsystem.Models.Model;
import javafx.application.Application;
import com.example.dsystem.Views.ViewFactory;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ConnectionController implements Initializable {
    @FXML
    private Button Button1;
    @FXML
    private TextField TextField1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TextField1.setText("localhost");
        Button1.setOnAction(event -> {
            try {
                connect(TextField1.getText());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void connect(String ip) throws Exception{
        Platform.runLater(() -> {
            try {
                if (ip == null || ip.isEmpty()) {
                    throw new Exception("Campo IP Obrigatório");
                }

                Model.getInstance().getConnection().connect(ip);
                Model.getInstance().getViewFactory().closeStage((Stage) Button1.getScene().getWindow());
                Model.getInstance().getViewFactory().showLoginWindow();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

    }
}