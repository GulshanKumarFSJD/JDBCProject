package Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ApplicationDashboard {
    private final JFrame frame;

    public ApplicationDashboard(String username) {
        // Create the main frame
        frame = new JFrame("Dashboard - Welcome " + username);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Create buttons for operations
        JButton addButton = new JButton("Add Student");
        JButton viewButton = new JButton("View Students");
        JButton updateButton = new JButton("Update Student");
        JButton deleteButton = new JButton("Delete Student");
        JButton logoutButton = new JButton("Logout");

        // Add Action Listeners to buttons
        addButton.addActionListener((ActionEvent e) -> new AddStudentGUI());
        viewButton.addActionListener((ActionEvent e) -> new ViewStudentsGUI());
        updateButton.addActionListener((ActionEvent e) -> new UpdateStudentGUI());
        deleteButton.addActionListener((ActionEvent e) -> new DeleteStudentGUI());
        logoutButton.addActionListener((ActionEvent e) -> logout());

        // Layout for buttons
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(logoutButton);

        // Add a title label
        JLabel titleLabel = new JLabel("Student Management System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add components to the frame
        frame.setLayout(new BorderLayout());
        frame.add(titleLabel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);

        // Center the frame and make it visible
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void logout() {
        int confirmation = JOptionPane.showConfirmDialog(frame, "Are you sure you want to logout?", "Logout Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            frame.dispose(); // Close the current dashboard
            SwingUtilities.invokeLater(() -> new UserLogin()); // Redirect to the login screen
        }
    }
}
