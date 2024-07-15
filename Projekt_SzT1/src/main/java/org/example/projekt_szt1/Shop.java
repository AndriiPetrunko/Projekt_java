package org.example.projekt_szt1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class Shop extends Application {
    private  static Stage stage;
    @Override
    public void start(Stage primaryStage) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage = primaryStage;
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Ivern Shop");
        stage.show();
    }
    public void changeScene(ActionEvent actionEvent, String title, String fxml) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml)));
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        currentStage.setScene(new Scene(root));
        currentStage.setResizable(false);
        currentStage.setTitle(title);
        currentStage.show();
        currentStage.centerOnScreen();
    }
    
    
    public static void main(String[] args) {
        launch(args);
    }
}