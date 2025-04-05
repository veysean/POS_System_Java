package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pos.DatabaseConnector;

public class AdminModel {
    private Connection connection;

    public AdminModel() {
        connection = DatabaseConnector.getConnection();
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                String userName = resultSet.getString("user_name");
                String role = resultSet.getString("role");
                User user = new User(userId, userName, role);
                users.add(user);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching users: " + e.getMessage());
        }
        return users;
    }

    public boolean addUser(User user) {
        String query = "INSERT INTO users (user_name, password, role) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error adding user: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteUser(int userId) {
        System.out.println("Attempting to delete user with ID: " + userId); // Debugging line
        String query = "DELETE FROM users WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User with ID " + userId + " deleted successfully.");
                return true;
            } else {
                System.err.println("No user found with ID " + userId + ".");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Error deleting user: " + e.getMessage());
            return false;
        }
    }

    public boolean updateUser(User user) {
        String query = "UPDATE users SET user_name = ?, password = ?, role = ? WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole());
            statement.setInt(4, user.getUserId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating user: " + e.getMessage());
            return false;
        }
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT p.product_id, p.product_name, p.price, p.description, c.category_id, c.category_name " +
                "FROM products p JOIN categories c ON p.category_id = c.category_id";
        try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                ProductCategory category = new ProductCategory(
                        resultSet.getInt("category_id"),
                        resultSet.getString("category_name"));
                Product product = new Product(
                        resultSet.getInt("product_id"),
                        resultSet.getString("product_name"),
                        resultSet.getDouble("price"),
                        resultSet.getString("description"),
                        category);
                products.add(product);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching products: " + e.getMessage());
        }
        return products;
    }

    public boolean addProduct(Product product) {
        String query = "INSERT INTO products (product_name, price, description, category_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setString(3, product.getDescription());
            statement.setInt(4, product.getCategory().getCategoryId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error adding product: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteProduct(int productId) {
        String query = "DELETE FROM products WHERE product_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, productId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting product: " + e.getMessage());
            return false;
        }
    }

    public List<ProductCategory> getAllCategories() {
        List<ProductCategory> categories = new ArrayList<>();
        String query = "SELECT * FROM categories";
        try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                ProductCategory category = new ProductCategory(
                        resultSet.getInt("category_id"),
                        resultSet.getString("category_name"));
                categories.add(category);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching categories: " + e.getMessage());
        }
        return categories;
    }
}
