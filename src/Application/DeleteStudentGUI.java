package Application;

import DAO.StudentDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DeleteStudentGUI extends JFrame {
    public DeleteStudentGUI() {
        setTitle("Delete Student");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create form fields
        JLabel idLabel = new JLabel("Student ID:");
        JTextField idField = new JTextField(20);

        JButton deleteButton = new JButton("Delete Student");

        // Delete button action
        deleteButton.addActionListener((ActionEvent e) -> {
            int id = Integer.parseInt(idField.getText());

            try {
                StudentDAO dao = new StudentDAO();
                dao.deleteStudent(id);
                JOptionPane.showMessageDialog(this, "Student deleted successfully!");
                dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error deleting student.");
            }
        });

        // Layout
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(idLabel);
        add(idField);
        add(deleteButton);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

