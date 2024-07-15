package GUI.Controllers;

import GUI.Controllers.DataSet.Order;
import GUI.Controllers.DataSet.Products;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.projekt_szt1.Shop;
import GUI.Controllers.Login;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Database.SQLDatabaseQueries.getData;

public class MainPage {
    @FXML
    Button LogOutB;
    @FXML
    Button EditB;
    @FXML
    Button COrderB;
    @FXML
    TableView<Order> MOrders;
    @FXML
    TableColumn<Order,String> nameCol;
    @FXML
    TableColumn<Order,String> priceCol;
    @FXML
    Label welcomeL;

    String userId = Login.userId;
    @FXML
    public void initialize() throws SQLException {
        ResultSet nameSet = getData("SELECT UFName, ULName FROM LoginData WHERE ULogin = '" + userId + "';");
        if(nameSet.next()){
            if(nameSet.getString("UFName") != null || nameSet.getString("ULName") != null){
                welcomeL.setText("Welcome, " + nameSet.getString("UFName") + " " + nameSet.getString("ULName") + "!");
            }else {
                welcomeL.setText("Welcome, user!");
            }
        }


        ResultSet ordertSet = getData("SELECT * FROM Orders WHERE UserID = '" + userId +"';");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("OrderName"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("OrderPrice"));
        ObservableList<Order> ordersList = FXCollections.observableArrayList();
        while (ordertSet.next()){
            Order order = new Order();
            order.setOrderName(ordertSet.getString("OrderName"));
            order.setOrderPrice(ordertSet.getString("OrderPrice"));
            ordersList.add(order);
        }
        MOrders.getItems().addAll(ordersList);


    }

    public void logOut(ActionEvent actionEvent) throws IOException {
        Shop shop = new Shop();
        shop.changeScene(actionEvent, "Ivern Shop","login.fxml");
    }
    public void createOrder(ActionEvent actionEvent) throws IOException {
        Shop shop = new Shop();
        shop.changeScene(actionEvent, "Create order", "createorder.fxml");
    }



    public void editProfile(ActionEvent actionEvent) throws IOException {
        Shop shop = new Shop();
        shop.changeScene(actionEvent, "Edit Profile", "editprofile.fxml");
    }
}
