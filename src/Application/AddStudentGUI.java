package Application;

import DAO.StudentDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;

public class AddStudentGUI extends JFrame {
    public AddStudentGUI() {
        setTitle("Add Student");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create form fields
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(20);

        JLabel ageLabel = new JLabel("Age:");
        JTextField ageField = new JTextField(20);

        JLabel gradeLabel = new JLabel("Grade:");
        JTextField gradeField = new JTextField(20);

        JLabel photoLabel = new JLabel("Photo:");
        JTextField photoField = new JTextField(20);
        JButton browseButton = new JButton("Browse");

        browseButton.addActionListener((ActionEvent e) -> {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                photoField.setText(fileChooser.getSelectedFile().getAbsolutePath());
            }
        });

        JButton submitButton = new JButton("Add Student");

        // Submit button action
        submitButton.addActionListener((ActionEvent e) -> {
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String grade = gradeField.getText();
            String photoPath = photoField.getText();

            try (FileInputStream photoStream = new FileInputStream(photoPath)) {
                StudentDAO dao = new StudentDAO();
                dao.addStudentWithPhoto(name, age, grade, photoStream);
                JOptionPane.showMessageDialog(this, "Student added successfully!");
                dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error adding student.");
            }
        });

        // Layout
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(nameLabel);
        add(nameField);
        add(ageLabel);
        add(ageField);
        add(gradeLabel);
        add(gradeField);
        add(photoLabel);
        add(photoField);
        add(browseButton);
        add(submitButton);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

