package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pos.DatabaseConnector;

public class CashierDAO {
    private Connection connection;

    public CashierDAO() {
        connection = DatabaseConnector.getConnection();
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try {
            String query = "SELECT p.product_id, p.product_name, p.price, p.description, c.category_id, c.category_name "
                    +
                    "FROM products p JOIN categories c ON p.category_id = c.category_id";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
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
            e.printStackTrace();
        }
        return products;
    }

    public Product getProductById(int productId) {
        try {
            String query = "SELECT p.product_id, p.product_name, p.price, p.description, c.category_id, c.category_name "
                    +
                    "FROM products p JOIN categories c ON p.category_id = c.category_id WHERE p.product_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, productId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                ProductCategory category = new ProductCategory(
                        resultSet.getInt("category_id"),
                        resultSet.getString("category_name"));
                return new Product(
                        resultSet.getInt("product_id"),
                        resultSet.getString("product_name"),
                        resultSet.getDouble("price"),
                        resultSet.getString("description"),
                        category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean processOrder(Order order) {
        String query = "INSERT INTO orders (total_amount) VALUES (?)";
        try {

            PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setDouble(1, order.getTotalAmount());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int orderId = generatedKeys.getInt(1);
                    for (OrderItem item : order.getItems()) {
                        String itemQuery = "INSERT INTO order_items (order_id, product_id, quantity) VALUES (?, ?, ?)";
                        PreparedStatement itemStatement = connection.prepareStatement(itemQuery);
                        itemStatement.setInt(1, orderId);
                        itemStatement.setInt(2, item.getProductId());
                        itemStatement.setInt(3, item.getQuantity());
                        itemStatement.executeUpdate();
                    }
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean saveInvoice(Order order) {
        try {
            String query = "INSERT INTO invoices (order_id, total_amount) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, order.getOrderId());
            statement.setDouble(2, order.getTotalAmount());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
