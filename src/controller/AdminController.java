package controller;

import model.AdminModel;
import model.Product;
import model.ProductCategory;
import model.User;
import view.AdminPage;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdminController {
    private AdminModel adminModel;
    private AdminPage adminPage;

    public AdminController(AdminModel adminModel, AdminPage adminPage) {
        this.adminModel = adminModel;
        this.adminPage = adminPage;

        this.adminPage.addAddUserListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = adminPage.getUsername();
                String password = adminPage.getPassword();
                String role = adminPage.getRole();
                User user = new User(0, username, password, role);
                if (adminModel.addUser(user)) {
                    System.out.println("User added successfully!");
                    refreshUserTable();
                } else {
                    System.err.println("Failed to add user.");
                }
            }
        });

        this.adminPage.addDeleteUserListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int userId = adminPage.getSelectedUserId();
                if (userId != -1 && adminModel.deleteUser(userId)) {
                    System.out.println("User deleted successfully!");
                    refreshUserTable();
                } else {
                    System.err.println("Failed to delete user.");
                }
            }
        });

        this.adminPage.addUpdateUserListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int userId = adminPage.getSelectedUserId();
                if (userId != -1) {
                    String username = adminPage.getUsername();
                    String password = adminPage.getPassword();
                    String role = adminPage.getRole();
                    User user = new User(userId, username, password, role);
                    if (adminModel.updateUser(user)) {
                        System.out.println("User updated successfully!");
                        refreshUserTable();
                    } else {
                        System.err.println("Failed to update user.");
                    }
                }
            }
        });

        this.adminPage.addAddProductListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String productName = adminPage.getProductName();
                double productPrice = adminPage.getProductPrice();
                String productDescription = adminPage.getProductDescription();
                ProductCategory productCategory = adminPage.getProductCategory();
                Product product = new Product(0, productName, productPrice, productDescription, productCategory);
                if (adminModel.addProduct(product)) {
                    System.out.println("Product added successfully!");
                    refreshProductTable();
                } else {
                    System.err.println("Failed to add product.");
                }
            }
        });

        this.adminPage.addDeleteProductListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int productId = adminPage.getSelectedProductId();
                if (productId != -1 && adminModel.deleteProduct(productId)) {
                    System.out.println("Product deleted successfully!");
                    refreshProductTable();
                } else {
                    System.err.println("Failed to delete product.");
                }
            }
        });

        this.adminPage.addBackButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminPage.dispose(); // Close the admin page
            }
        });

        refreshUserTable();
        refreshProductTable();
        populateProductCategories();
    }

    private void refreshUserTable() {
        List<User> users = adminModel.getAllUsers();
        DefaultTableModel model = new DefaultTableModel(new String[] { "User ID", "Username", "Password", "Role" }, 0);
        for (User user : users) {
            model.addRow(new Object[] { user.getUserId(), user.getUserName(), user.getPassword(), user.getRole() });
        }
        adminPage.setUserTableModel(model);
    }
    

    private void refreshProductTable() {
        List<Product> products = adminModel.getAllProducts();
        DefaultTableModel model = new DefaultTableModel(
                new String[] { "Product ID", "Product Name", "Price", "Description", "Category" }, 0);
        for (Product product : products) {
            model.addRow(new Object[] { product.getProductId(), product.getName(), product.getPrice(),
                    product.getDescription(), product.getCategory().getCategoryName() });
        }
        adminPage.setProductTableModel(model);
    }

    private void populateProductCategories() {
        List<ProductCategory> categories = adminModel.getAllCategories();
        adminPage.setProductCategories(categories);
    }
    
}