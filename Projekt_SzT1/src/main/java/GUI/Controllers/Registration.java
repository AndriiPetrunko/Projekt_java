package GUI.Controllers;


import Validation.TextFieldValidation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.example.projekt_szt1.Shop;

import java.io.IOException;

import static Database.SQLDatabaseQueries.updateData;

public class Registration {
    @FXML
     Button SignUp;
    @FXML
     TextField LoginTField;
    @FXML
    PasswordField PasswordTField;
    @FXML
    Button BackB;
    @FXML
    public void initialize(){
        LoginTField.addEventFilter(KeyEvent.KEY_TYPED, TextFieldValidation.loginValidation(16));
        PasswordTField.addEventFilter(KeyEvent.KEY_TYPED, TextFieldValidation.passwordValidation(16));
    }


    public void signUpUser(ActionEvent actionEvent) {
        try{

            updateData("INSERT INTO LoginData(ULogin, UPassword) VALUES ("+
                    "'"+LoginTField.getText()+"'"+", "
                    + "'"+PasswordTField.getText()+"'" +
                    ")");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Account created!");
            alert.setHeaderText("You may go back to login screen");
            alert.setResizable(false);
            alert.showAndWait();
            Shop shop = new Shop();
            shop.changeScene(actionEvent,"Ivern Shop","login.fxml");
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("An error occurred");
            alert.setHeaderText("Something went wrong, try entering other data");
            alert.setResizable(false);
            alert.showAndWait();

        }
    }
    public void backToLogin(ActionEvent actionEvent) throws IOException {
        Shop shop = new Shop();
        shop.changeScene(actionEvent, "Ivern Shop", "login.fxml");

    }

}
