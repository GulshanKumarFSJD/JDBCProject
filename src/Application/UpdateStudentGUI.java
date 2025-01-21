package Application;

import DAO.StudentDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class UpdateStudentGUI extends JFrame {
    public UpdateStudentGUI() {
        setTitle("Update Student");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create form fields
        JLabel idLabel = new JLabel("Student ID:");
        JTextField idField = new JTextField(20);

        JLabel nameLabel = new JLabel("New Name:");
        JTextField nameField = new JTextField(20);

        JLabel ageLabel = new JLabel("New Age:");
        JTextField ageField = new JTextField(20);

        JLabel gradeLabel = new JLabel("New Grade:");
        JTextField gradeField = new JTextField(20);

        JButton updateButton = new JButton("Update Student");

        // Update button action
        updateButton.addActionListener((ActionEvent e) -> {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String grade = gradeField.getText();

            try {
                StudentDAO dao = new StudentDAO();
                dao.updateStudent(id, name, age, grade);
                JOptionPane.showMessageDialog(this, "Student updated successfully!");
                dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error updating student.");
            }
        });

        // Layout
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(idLabel);
        add(idField);
        add(nameLabel);
        add(nameField);
        add(ageLabel);
        add(ageField);
        add(gradeLabel);
        add(gradeField);
        add(updateButton);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

