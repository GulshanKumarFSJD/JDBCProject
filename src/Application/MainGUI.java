package Application;

import javax.swing.*;

public class MainGUI {
    public MainGUI() {
        JFrame frame = new JFrame("Main Application");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JButton adminLoginButton = new JButton("Admin Login");
        adminLoginButton.setBounds(120, 50, 150, 30);
        adminLoginButton.addActionListener(e -> new AdminLogin());
        frame.add(adminLoginButton);

        JButton userLoginButton = new JButton("User Login");
        userLoginButton.setBounds(120, 100, 150, 30);
        userLoginButton.addActionListener(e -> new UserLogin());
        frame.add(userLoginButton);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(120, 150, 150, 30);
        registerButton.addActionListener(e -> new RegisterUser());
        frame.add(registerButton);

        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new MainGUI();
    }
}
