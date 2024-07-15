package GUI.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.example.projekt_szt1.Shop;

import java.io.IOException;
import java.sql.SQLException;

import static Database.SQLDatabaseQueries.updateData;

public class EditProfile {
    public String UID = Login.userId;
    @FXML
    TextField nameTF;
    @FXML
    TextField snameTF;
    @FXML
    Button saveB;
    @FXML
    Button backB;
    public void backToMain(ActionEvent actionEvent) throws IOException {
        Shop shop = new Shop();
        shop.changeScene(actionEvent, "Main page", "mainpage.fxml");

    }
    public void saveChanges(ActionEvent actionEvent) throws SQLException, IOException {
        updateData("UPDATE LoginData\n" +
                "SET UFName = '"+nameTF.getText()+"', ULName = '"+snameTF.getText()+"'\n" +
                "where ULogin = '"+UID+"'");
        backToMain(actionEvent);
    }


}
