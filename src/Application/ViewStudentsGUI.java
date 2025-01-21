package Application;

import DAO.StudentDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewStudentsGUI extends JFrame {
    public ViewStudentsGUI() {
        setTitle("View Students");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Table model with columns: ID, Name, Age, Grade, and Photo
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Age", "Grade", "Photo"}, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 4) {
                    return ImageIcon.class; // Photo column will display images
                }
                return String.class;
            }
        };

        JTable table = new JTable(tableModel);

        // Fetch data from DAO
        try {
            StudentDAO dao = new StudentDAO();
            List<Object[]> students = dao.viewStudents();

            for (Object[] student : students) {
                int id = (int) student[0];
                String name = (String) student[1];
                int age = (int) student[2];
                String grade = (String) student[3];
                byte[] photoBytes = (byte[]) student[4];

                // Convert byte array to ImageIcon
                ImageIcon photoIcon = null;
                if (photoBytes != null) {
                    photoIcon = new ImageIcon(new ImageIcon(photoBytes).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
                }

                // Add row to the table
                tableModel.addRow(new Object[]{id, name, age, grade, photoIcon});
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching data.");
        }

        // Adjust table properties
        table.setRowHeight(100); // Adjust row height for displaying images
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
