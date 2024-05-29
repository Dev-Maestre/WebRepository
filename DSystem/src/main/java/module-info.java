module com.example.dsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires com.fasterxml.jackson.databind;
    requires org.apache.commons.codec;


    opens com.example.dsystem to javafx.fxml;
    exports com.example.dsystem;
    exports com.example.dsystem.Controllers;
    opens com.example.dsystem.Controllers to javafx.fxml;
    exports com.example.dsystem.Controllers.Client;
    opens com.example.dsystem.Controllers.Client to javafx.fxml;
    exports com.example.dsystem.System.Send to com.fasterxml.jackson.databind;
    exports com.example.dsystem.System.Send.Candidate to com.fasterxml.jackson.databind;
    exports com.example.dsystem.Controllers.Client.Candidate;
    opens com.example.dsystem.Controllers.Client.Candidate to javafx.fxml;
    exports com.example.dsystem.System.Send.Recruiter to com.fasterxml.jackson.databind;
}