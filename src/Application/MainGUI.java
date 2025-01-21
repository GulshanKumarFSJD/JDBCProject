package Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class MainGUI {
    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Student Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Create buttons
        JButton addButton = new JButton("Add Student");
        JButton viewButton = new JButton("View Students");
        JButton updateButton = new JButton("Update Student");
        JButton deleteButton = new JButton("Delete Student");

        // Add Action Listeners to buttons
        addButton.addActionListener((ActionEvent e) -> new AddStudentGUI());
        viewButton.addActionListener((ActionEvent e) -> new ViewStudentsGUI());
        updateButton.addActionListener((ActionEvent e) -> new UpdateStudentGUI());
        deleteButton.addActionListener((ActionEvent e) -> new DeleteStudentGUI());

        // Layout for buttons
        frame.setLayout(new GridLayout(4, 1, 10, 10));
        frame.add(addButton);
        frame.add(viewButton);
        frame.add(updateButton);
        frame.add(deleteButton);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}