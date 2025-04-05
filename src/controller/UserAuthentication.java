package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.UserDAO;
import view.LoginForm;

public class UserAuthentication {

    UserDAO user = new UserDAO(); // Assuming UserDAO is a class that handles user authentication
    LoginForm loginForm = new LoginForm(); // Assuming LoginForm is a class that handles the login UI

    public UserAuthentication(UserDAO user, LoginForm loginForm) {
        this.user = user;
        this.loginForm = loginForm;

        this.loginForm.addLoginActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Login button clicked"); // Debugging statement
                String username = loginForm.getUsername();
                String password = loginForm.getPassword();
                String role = user.authenticate(username, password);
                if (role != null) {
                    System.out.println("Login successful! Welcome " + role);
                } else {
                    System.out.println("Login failed!");
                }
            }
        });
    }
}
