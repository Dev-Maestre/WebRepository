package com.example.dsystemserver.Views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;

public class ViewFactory {

    public ViewFactory() {
    }

    public void createStage(FXMLLoader loader, String title) {
        Scene scene = null;
        try {
            scene = new Scene(loader.load(), 600, 400);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }

    public void closeStage(Stage stage) {
        stage.close();
    }

    public void ShowHomeWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/dsystemserver/Home.fxml"));
        createStage(loader, "Server Home");
    }

    public void ShowErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
