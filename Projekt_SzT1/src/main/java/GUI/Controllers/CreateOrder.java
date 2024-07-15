package GUI.Controllers;


import GUI.Controllers.DataSet.Products;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.VBox;
import org.example.projekt_szt1.Shop;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;


import static Database.SQLDatabaseQueries.getData;
import static Database.SQLDatabaseQueries.updateData;
import static GUI.Controllers.Login.userId;
import static javafx.scene.control.cell.TextFieldTableCell.*;

public class CreateOrder {
    public String UID = userId;
    @FXML
    TableView<Products> orderT;
    @FXML
    TableColumn<Products, String> pname;
    @FXML
    TableColumn<Products,Double> priceperunit;
    @FXML
    TableColumn<Products,Integer> amount;
    @FXML
    TableColumn<Products,Button> decreaseCol;
    @FXML
    TableColumn<Products,Button> increaseCol;
    @FXML
    Label totalL;
    @FXML
    Button backB;
    @FXML
    Button saveB;


    @FXML
    public void initialize() throws SQLException {
        ResultSet productSet = getData("SELECT * FROM Products;");
        pname.setCellValueFactory(new PropertyValueFactory<>("ProductName"));
        priceperunit.setCellValueFactory(new PropertyValueFactory<>("PricePerUnit"));
        amount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        decreaseCol.setCellValueFactory(new PropertyValueFactory<>("decreaseB"));
        increaseCol.setCellValueFactory(new PropertyValueFactory<>("increaseB"));
        ObservableList<Products> products = FXCollections.observableArrayList();
        totalL.setText("0.0");


        while (productSet.next()) {
            Products p = new Products();
            Button decreaseB = new Button("-");
            Button increaseB  = new Button("+");
            p.setAmount(0);
            decreaseB.setOnAction(e -> {
               if (p.getAmount() <= 0){
                   return;
               } else {
                   p.setAmount(p.getAmount()-1);
                   totalL.setText(String.valueOf(Math.round((Double.valueOf(totalL.getText())-(Double.valueOf(p.getPricePerUnit())))*100.0)/100.0));
                   orderT.refresh();
               }
            });
            increaseB.setOnAction(e -> {
                p.setAmount(p.getAmount()+1);
                totalL.setText(String.valueOf(Math.round((Double.parseDouble(totalL.getText())+(Double.parseDouble(p.getPricePerUnit())))*100.0)/100.0));
                orderT.refresh();

            });
            p.setProductName(productSet.getString("ProductName"));
            p.setPricePerUnit(productSet.getString("PricePerUnit"));
            p.setIncreaseB(increaseB);
            p.setDecreaseB(decreaseB);
            products.add(p);

        }
        orderT.getItems().addAll(products);
    }
    public void backToMain(ActionEvent actionEvent) throws IOException {
        Shop shop = new Shop();
        shop.changeScene(actionEvent, "Main Page", "mainpage.fxml");
    }
    public void saveOrder(ActionEvent actionEvent) throws  IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Name your order!");
        alert.setHeaderText("Enter order name");
        alert.setContentText("Order name:");

        // Create the TextField
        TextField textField = new TextField();
        textField.setPromptText("Order name");

        // Create a VBox to hold the label and text field
        VBox vbox = new VBox();
        vbox.setSpacing(10);  // Set spacing between elements
        vbox.getChildren().addAll(new Label("Order name:"), textField);

        // Set the expandable content of the alert
        alert.getDialogPane().setContent(vbox);

        // Show the alert and wait for user input
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            updateData("INSERT INTO Orders VALUES ('" + userId + "','" + textField.getText() +"'," + totalL.getText() + ")");
            Shop shop = new Shop();
            shop.changeScene(actionEvent, "Main page", "mainpage.fxml");
        } else {
            System.out.println("Dialog canceled.");
        }
    }

    }

