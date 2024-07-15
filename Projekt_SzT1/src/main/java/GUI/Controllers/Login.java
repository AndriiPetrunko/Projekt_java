package GUI.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.example.projekt_szt1.Shop;
import Validation.TextFieldValidation;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.Objects;

import static Database.SQLDatabaseQueries.getData;

public class Login{
    @FXML
    Button LoginB;
    @FXML
    TextField LoginTField;
    @FXML
    PasswordField PasswordTField;
    @FXML
    Button CreateB;
    public static String userId;
    @FXML
    public void initialize(){
        LoginTField.addEventFilter(KeyEvent.KEY_TYPED, TextFieldValidation.loginValidation(50));
        PasswordTField.addEventFilter(KeyEvent.KEY_TYPED, TextFieldValidation.passwordValidation(50));
    }


    public void loginUser(ActionEvent event) {

        try {
            ResultSet UserLoginData = getData("SELECT ULogin, UPassword " +
                    "FROM LoginData " +
                    "WHERE ULogin = " +
                    "'" + LoginTField.getText() + "'");
            if (UserLoginData.next() && Objects.equals(UserLoginData.getString("UPassword"), PasswordTField.getText())) {
                userId = LoginTField.getText();
                Shop shop = new Shop();
                shop.changeScene(event, "Main Page", "mainpage.fxml");
            } else {
                LoginTField.clear();
                PasswordTField.clear();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Login credit nail is incorrect. Please check again");
                alert.setTitle("Login error occurred");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void createAccount(ActionEvent actionEvent) throws IOException {
        Shop shop = new Shop();
        shop.changeScene(actionEvent, "Create account","signup.fxml");
    }
}
