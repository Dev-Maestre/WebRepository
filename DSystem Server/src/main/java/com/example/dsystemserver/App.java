package com.example.dsystemserver;

import com.example.dsystemserver.Models.Model;
import com.example.dsystemserver.Models.User;
import com.example.dsystemserver.System.Connection.JWTManager;
import com.example.dsystemserver.System.Connection.Server;
import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.SQLException;

public class App extends Application {

    static final int PORT = 21234;

    @Override
    public void start(Stage primaryStage) throws Exception {
        startServer(PORT);
    }

    public void startServer(int port) throws SQLException {
        /*if (Model.getInstance().getDatabaseDriver().getCandidateByID(1) == null)
        {
            System.out.println("Creating user");
            //User user = new User("User", "user@user.com", JWTManager.hashPassword("6edf26f6e0badff12fca32b16db38bf2"), "user");
            User user = new User("user@user.com", JWTManager.hashPassword("6EDF26F6E0BADFF12FCA32B16DB38BF2"), "user", "Candidate", 1);
            Model.getInstance().getDatabaseDriver().addCandidate(user);
        }
        if (Model.getInstance().getDatabaseDriver().getRecruiterByID(1) == null)
        {
            System.out.println("Creating recruiter user");
            //User user = new User("User", "user@user.com", JWTManager.hashPassword("c2b3fa61cf680f72e42edeec4bb9e50f"), "recruiter");
            User user = new User("recruiter@recruiter.com", JWTManager.hashPassword("C2B3FA61CF680F72E42EDEEC4BB9E50F"), "recruiter", "Industria A", "nao tem", "Recruiter", 1);
            Model.getInstance().getDatabaseDriver().addRecruiter(user);
        }
         */
        Thread serverThread = new Thread(new Server(port));
        serverThread.start();
    }


    public static void main(String[] args) {
        launch();
    }
}