/**
 *
 */
module org.example.projektSzT {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires com.microsoft.sqlserver.jdbc;

    opens org.example.projekt_szt1 to javafx.fxml;
    opens Database to javafx.fxml;
    opens GUI.Controllers to javafx.fxml;
    opens GUI.Controllers.DataSet to javafx.fxml;
    opens Validation to javafx.fxml;
    exports  Validation;
    exports GUI.Controllers.DataSet;
    exports GUI.Controllers;
    exports org.example.projekt_szt1;
    exports Database;


}