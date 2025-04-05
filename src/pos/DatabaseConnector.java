package pos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/POS";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            if (conn != null) {
                System.out.println("Connected to the database.");
            } else {
                System.out.println("Failed to make connection!");
            }
            return conn;
        } catch (SQLException var1) {
            throw new RuntimeException("Error" + String.valueOf(var1));
        }
    }

}
