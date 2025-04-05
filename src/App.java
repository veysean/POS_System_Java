import javax.swing.*;

import controller.AdminController;
import controller.CashierController;
import controller.UserAuthentication;
import model.AdminModel;
import model.CashierDAO;
import model.UserDAO;
import view.AdminPage;
import view.CashierPage;
import view.LoginForm;

public class App {
    private JFrame frame;
    private LoginForm loginForm;
    private AdminPage adminPage;
    private CashierPage cashierPage;
    private AdminController adminController;
    private CashierController cashierController;
    private UserAuthentication userAuthentication;

    public App() {
        // Initialize the main frame
        frame = new JFrame("POS System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        // Initialize pages
        loginForm = new LoginForm();
        adminPage = new AdminPage();
        cashierPage = new CashierPage();

        // Initialize controllers
        AdminModel adminModel = new AdminModel();
        adminController = new AdminController(adminModel, adminPage);

        CashierDAO cashierDAO = new CashierDAO();
        cashierController = new CashierController(cashierDAO, cashierPage);

        // Initialize user authentication
        UserDAO userDAO = new UserDAO();
        userAuthentication = new UserAuthentication(userDAO, loginForm);

        // Set up login page action
        loginForm.addLoginActionListener(e -> {
            String username = loginForm.getUsername();
            String password = loginForm.getPassword();
            String role = userDAO.authenticate(username, password);
            if (role != null) {
                if (role.equals("admin")) {
                    adminPage.setVisible(true);
                    loginForm.dispose();
                } else if (role.equals("cashier")) {
                    cashierPage.setVisible(true);
                    loginForm.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "Welcome " + role, "Login Successful",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                loginForm.dispose(); // Close the login form
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid credentials", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Show login page initially
        frame.setContentPane(loginForm.getPanel());
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(App::new);
    }
}