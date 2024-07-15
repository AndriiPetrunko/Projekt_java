package GUI.Controllers.DataSet;

import javafx.scene.control.Button;

import java.util.function.DoubleUnaryOperator;

public class Products {
    public String ProductName;
    public Button increaseB;

    public Products() {

    }

    public Button getIncreaseB() {
        return increaseB;
    }

    public void setIncreaseB(Button increaseB) {
        this.increaseB = increaseB;
    }

    public Button getDecreaseB() {
        return decreaseB;
    }

    public void setDecreaseB(Button decreaseB) {
        this.decreaseB = decreaseB;
    }

    public Button decreaseB;


    public Integer getAmount() {
        return Amount;
    }

    public void setAmount(Integer amount) {
        Amount = amount;
    }

    public Integer Amount;

    public Products(String productName, String pricePerUnit, Integer amount) {
        ProductName = productName;
        PricePerUnit = pricePerUnit;
        Amount = amount;
        increaseB = new Button();
        decreaseB = new Button();

    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getPricePerUnit() {
        return PricePerUnit;
    }

    public void setPricePerUnit(String pricePerUnit) {
        PricePerUnit = pricePerUnit;
    }

    public String PricePerUnit;
}
