package Application;

import conn.DBConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegisterUser {
    public RegisterUser() {
        JFrame frame = new JFrame("User Registration");
        frame.setSize(400, 300);
        frame.setLayout(null);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 50, 100, 30);
        frame.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(150, 50, 150, 30);
        frame.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 100, 100, 30);
        frame.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 150, 30);
        frame.add(passwordField);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(150, 150, 100, 30);
        registerButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = String.valueOf(passwordField.getPassword());
            if (registerUser(username, password)) {
                JOptionPane.showMessageDialog(frame, "Registration Successful! Wait for Admin Approval.");
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "Registration Failed. Try Again!");
            }
        });
        frame.add(registerButton);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private boolean registerUser(String username, String password) {
        String sql = "INSERT INTO users (username, password, access_permission) VALUES (?, ?, ?)";
        Connection conn = DBConnection.getInstance().getConnection();
        try (
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setBoolean(3, false); // Default access permission to false
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
