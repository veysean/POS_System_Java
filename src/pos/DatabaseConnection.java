package pos;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection {

    public static void main(String[] args) {
        Connection connection = DatabaseConnector.getConnection();
        // Close the connection if it was established
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }

}
