package com.example.dsystem.Views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewFactory {

    public static void hideWindow(Stage stage) {
        stage.hide();
    }

    public void showLoginWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/dsystem/Login.fxml"));

        Scene scene = new Scene(loader.load(),600,400);
        Stage stage = new Stage();
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();

    }

    public void createStage(FXMLLoader loader, String title) {
        Scene scene = null;
        try {
            scene = new Scene(loader.load(), 600, 400);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public void showRegisterWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/dsystem/Register.fxml"));

        Scene scene = new Scene(loader.load(),600,400);
        Stage stage = new Stage();
        stage.setTitle("Register");
        stage.setScene(scene);
        stage.show();

    }

    public void closeStage(Stage stage) {
        stage.close();
    }

    public void showAdminWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/dsystem/Admin.fxml"));
        createStage(loader, "Bem-Vindo !");
    }

    public void showCandidateWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/dsystem/Candidate.fxml"));
        createStage(loader, "Bem-Vindo !");
    }

    public void showCandidateEditWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/dsystem/UpdateCandidate.fxml"));
        createStage(loader, "Editar !");

    }

    public void showRecruiterWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/dsystem/Recruiter.fxml"));
        createStage(loader, "Bem-Vindo !");
    }
}
