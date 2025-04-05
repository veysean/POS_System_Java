package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class POSYSYTEM extends JFrame {

    private JButton loginButton;

    public POSYSYTEM() {
        setTitle("POS System - Welcome");
        setSize(2000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);
        this.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add some padding

        // Center the welcome label with larger font size
        JLabel welcomeLabel = new JLabel("Welcome to the POS System Bug Coffee!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Set font size to 24
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(welcomeLabel, gbc);

        // Center the login button below the welcome label with larger size
        loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(400, 75)); // Set button size to 400x75
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, gbc);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginForm(); // Navigate to LoginForm
                dispose(); // Close the main page
            }
        });
    }
}
