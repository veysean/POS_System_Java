package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pos.DatabaseConnector;

public class UserDAO {
    private Connection connection;

    public UserDAO() {
        connection = DatabaseConnector.getConnection();
    }

    public String authenticate(String username, String password) {
        try {
            String query = "SELECT role FROM users WHERE user_name = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("role"); // Return the user's role
            } else {
                return null; // Authentication failed
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
