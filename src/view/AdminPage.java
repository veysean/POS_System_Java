package view;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.event.ActionListener;
import java.util.List;
import model.ProductCategory;

public class AdminPage extends JFrame {
    private JTable userTable;
    private JTable productTable;
    private JButton addUserButton;
    private JButton deleteUserButton;
    private JButton updateUserButton;
    private JButton addProductButton;
    private JButton deleteProductButton;
    private JButton backButton;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField roleField;
    private JTextField userIdField;
    private JTextField productIdField;
    private JTextField productNameField;
    private JTextField productPriceField;
    private JTextField productDescriptionField;
    private JComboBox<ProductCategory> productCategoryComboBox;
    private JPanel panel;

    public AdminPage() {
        setTitle("Admin Page");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new JPanel();
        add(panel);
        placeComponents(panel);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        backButton = new JButton("Back");
        backButton.setBounds(10, 680, 120, 25);
        panel.add(backButton);

        placeUserComponents(panel);
        placeProductComponents(panel);
    }

    private void placeUserComponents(JPanel panel) {
        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(100, 20, 165, 25);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(100, 50, 165, 25);
        panel.add(passwordField);

        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setBounds(10, 80, 80, 25);
        panel.add(roleLabel);

        roleField = new JTextField(20);
        roleField.setBounds(100, 80, 165, 25);
        panel.add(roleField);

        JLabel userIdLabel = new JLabel("User ID:");
        userIdLabel.setBounds(10, 110, 80, 25);
        panel.add(userIdLabel);

        userIdField = new JTextField(20);
        userIdField.setBounds(100, 110, 165, 25);
        panel.add(userIdField);

        addUserButton = new JButton("Add User");
        addUserButton.setBounds(10, 140, 120, 25);
        panel.add(addUserButton);

        deleteUserButton = new JButton("Delete User");
        deleteUserButton.setBounds(140, 140, 120, 25);
        panel.add(deleteUserButton);

        updateUserButton = new JButton("Update User");
        updateUserButton.setBounds(270, 140, 120, 25);
        panel.add(updateUserButton);

        userTable = new JTable();
        JScrollPane userScrollPane = new JScrollPane(userTable);
        userScrollPane.setBounds(10, 180, 760, 150);
        panel.add(userScrollPane);
    }

    private void placeProductComponents(JPanel panel) {
        JLabel productNameLabel = new JLabel("Product Name:");
        productNameLabel.setBounds(10, 340, 100, 25);
        panel.add(productNameLabel);

        productNameField = new JTextField(20);
        productNameField.setBounds(120, 340, 165, 25);
        panel.add(productNameField);

        JLabel productPriceLabel = new JLabel("Product Price:");
        productPriceLabel.setBounds(10, 370, 100, 25);
        panel.add(productPriceLabel);

        productPriceField = new JTextField(20);
        productPriceField.setBounds(120, 370, 165, 25);
        panel.add(productPriceField);

        JLabel productDescriptionLabel = new JLabel("Description:");
        productDescriptionLabel.setBounds(10, 400, 100, 25);
        panel.add(productDescriptionLabel);

        productDescriptionField = new JTextField(20);
        productDescriptionField.setBounds(120, 400, 165, 25);
        panel.add(productDescriptionField);

        JLabel productCategoryLabel = new JLabel("Category:");
        productCategoryLabel.setBounds(10, 430, 100, 25);
        panel.add(productCategoryLabel);

        productCategoryComboBox = new JComboBox<>();
        productCategoryComboBox.setBounds(120, 430, 165, 25);
        panel.add(productCategoryComboBox);

        JLabel productIdLabel = new JLabel("Product ID:");
        productIdLabel.setBounds(10, 460, 100, 25);
        panel.add(productIdLabel);

        productIdField = new JTextField(20);
        productIdField.setBounds(120, 460, 165, 25);
        panel.add(productIdField);

        addProductButton = new JButton("Add Product");
        addProductButton.setBounds(10, 490, 120, 25);
        panel.add(addProductButton);

        deleteProductButton = new JButton("Delete Product");
        deleteProductButton.setBounds(140, 490, 120, 25);
        panel.add(deleteProductButton);

        productTable = new JTable();
        JScrollPane productScrollPane = new JScrollPane(productTable);
        productScrollPane.setBounds(10, 520, 760, 150);
        panel.add(productScrollPane);
    }

    // Getters and setters for user fields
    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public String getRole() {
        return roleField.getText();
    }

    public int getUserId() {
        return Integer.parseInt(userIdField.getText());
    }

    // Getters and setters for product fields
    public String getProductName() {
        return productNameField.getText();
    }

    public double getProductPrice() {
        return Double.parseDouble(productPriceField.getText());
    }

    public String getProductDescription() {
        return productDescriptionField.getText();
    }

    public int getProductId() {
        return Integer.parseInt(productIdField.getText());
    }

    public ProductCategory getProductCategory() {
        return (ProductCategory) productCategoryComboBox.getSelectedItem();
    }

    // Listener methods for user actions
    public void addAddUserListener(ActionListener listener) {
        addUserButton.addActionListener(listener);
    }

    public void addDeleteUserListener(ActionListener listener) {
        deleteUserButton.addActionListener(listener);
    }

    public void addUpdateUserListener(ActionListener listener) {
        updateUserButton.addActionListener(listener);
    }

    // Listener methods for product actions
    public void addAddProductListener(ActionListener listener) {
        addProductButton.addActionListener(listener);
    }

    public void addDeleteProductListener(ActionListener listener) {
        deleteProductButton.addActionListener(listener);
    }

    // Table model setters
    public void setUserTableModel(TableModel model) {
        userTable.setModel(model);
    }

    public void setProductTableModel(TableModel model) {
        productTable.setModel(model);
    }

    // Back button listener
    public void addBackButtonListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }

    // Methods to get selected IDs
    public int getSelectedUserId() {
        int row = userTable.getSelectedRow();
        System.out.println("Selected Row: " + row); // Debugging line
        if (row != -1) {
            int userId = (int) userTable.getValueAt(row, 0); // Assuming user ID is in the first column
            System.out.println("Selected User ID: " + userId); // Debugging line
            return userId;
        }
        System.err.println("No row selected or invalid row index."); // Debugging line
        return -1;
    }

    public int getSelectedProductId() {
        int row = productTable.getSelectedRow();
        if (row != -1) {
            return (int) productTable.getValueAt(row, 0);
        }
        return -1;
    }

    // Method to set product categories
    public void setProductCategories(List<ProductCategory> categories) {
        for (ProductCategory category : categories) {
            productCategoryComboBox.addItem(category);
        }
    }

    public JPanel getPanel() {
        return panel;
    }
}
