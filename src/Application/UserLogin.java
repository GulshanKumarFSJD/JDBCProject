package Application;

import conn.DBConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserLogin {
    public UserLogin() {
        JFrame frame = new JFrame("User Login");
        frame.setSize(400, 250);
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

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(150, 150, 100, 30);
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = String.valueOf(passwordField.getPassword());

            if (loginUser(username, password)) {
                JOptionPane.showMessageDialog(frame, "Login Successful!");
                frame.dispose();
                // Open application dashboard or main application
                new ApplicationDashboard(username); // Replace this with your application's dashboard
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid credentials or access not approved!");
            }
        });
        frame.add(loginButton);

        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);
    }

    private boolean loginUser(String username, String password) {
        String sql = "SELECT access_permission FROM users WHERE username = ? AND password = ?";
        Connection conn = DBConnection.getInstance().getConnection();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getBoolean("access_permission"); // Login only if access_permission is true
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
