package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
    public class SQLDatabaseConnection {
    public static Connection getConn() throws SQLException {
         return DriverManager.getConnection("jdbc:sqlserver://localhost;DatabaseName=LocalDB;integratedSecurity=true;trustServerCertificate=true;");
    }}

























