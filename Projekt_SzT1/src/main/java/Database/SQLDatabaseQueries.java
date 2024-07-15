package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLDatabaseQueries {
    public static ResultSet getData(String SQLQuery) {
        ResultSet resultSet;
        Connection connection;
        Statement statement;
        try {
            connection = SQLDatabaseConnection.getConn();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQLQuery);
            return resultSet;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public static void updateData(String SQLUpdate){
        Connection connection;
        Statement statement;
        try {
            connection = SQLDatabaseConnection.getConn();
            statement = connection.createStatement();
            statement.executeUpdate(SQLUpdate);
            if (!connection.isClosed()) connection.close();}
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    }


