module com.example.dsystemserver {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;
    requires java.sql;
    requires jjwt.api;
    requires jbcrypt;

    opens com.example.dsystemserver to javafx.fxml;
    exports com.example.dsystemserver;
    exports com.example.dsystemserver.Controllers;
    opens com.example.dsystemserver.Controllers to javafx.fxml;
    exports com.example.dsystemserver.System.Connection.Send to com.fasterxml.jackson.databind;
    exports com.example.dsystemserver.Models;
    exports com.example.dsystemserver.System.Connection.Send.Candidate to com.fasterxml.jackson.databind;
    exports com.example.dsystemserver.System.Connection.Send.Recruiter to com.fasterxml.jackson.databind;
}