package view;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginForm extends JFrame {

    private JTextField userNameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JPanel panel;

    public LoginForm() {
        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new JPanel();
        add(panel);
        placeComponents(panel);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("User:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        userNameField = new JTextField(20);
        userNameField.setBounds(100, 20, 165, 25);
        panel.add(userNameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(100, 50, 165, 25);
        panel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);
    }

    public String getPassword() {
        return new String(this.passwordField.getPassword());
    }

    public void addLoginActionListener(ActionListener action) {
        this.loginButton.addActionListener(action);
    }

    public String getUsername() {
        return this.userNameField.getText();
    }

    public JPanel getPanel() {
        return panel;
    }
}
