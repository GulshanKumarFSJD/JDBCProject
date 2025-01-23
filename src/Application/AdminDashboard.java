package Application;

import conn.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDashboard extends JFrame {
    private final JTable userTable;

    public AdminDashboard() {
        setTitle("Admin Dashboard");
        setSize(600, 400);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        userTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(userTable);
        JPanel buttonPanel = new JPanel();

        JButton grantAccessButton = new JButton("Grant Access");
        JButton revokeAccessButton = new JButton("Revoke Access");

        buttonPanel.add(grantAccessButton);
        buttonPanel.add(revokeAccessButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        loadUsers();

        grantAccessButton.addActionListener(e -> updateAccess(true));
        revokeAccessButton.addActionListener(e -> updateAccess(false));

        setVisible(true);
    }

    private void loadUsers() {
        Connection conn = DBConnection.getInstance().getConnection();
        try {
            String sql = "SELECT user_id, username, access_permission FROM users";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            // Get column count and build table model
            int columnCount = rs.getMetaData().getColumnCount();

            // Prepare column names
            String[] columnNames = new String[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                columnNames[i - 1] = rs.getMetaData().getColumnName(i);
            }

            // Prepare data
            java.util.List<Object[]> data = new java.util.ArrayList<>();
            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                data.add(row);
            }

            // Convert data list to array
            Object[][] tableData = data.toArray(new Object[0][]);

            // Set the table model
            userTable.setModel(new javax.swing.table.DefaultTableModel(tableData, columnNames));

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while loading users.");
        }
    }


    private void updateAccess(boolean grant) {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a user first.");
            return;
        }

        int userId = (int) userTable.getValueAt(selectedRow, 0);
        Connection conn = DBConnection.getInstance().getConnection();
        try {
            String sql = "UPDATE users SET access_permission = ? WHERE user_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setBoolean(1, grant);
            stmt.setInt(2, userId);
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Access updated successfully.");
            loadUsers();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred.");
        }
    }
}
