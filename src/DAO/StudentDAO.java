package DAO;

import conn.DBConnection;

import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class StudentDAO {
    private static Connection connectToDb() {
        return DBConnection.getInstance().getConnection();
    }


    public void updateStudent(int id, String name, int age, String grade) {
        String query = "UPDATE students SET name = ?, age = ?, grade = ? WHERE id = ?";
        Connection connection = connectToDb();
        try (
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.setString(3, grade);
            stmt.setInt(4, id);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Student updated successfully!");
            } else {
                System.out.println("No student found with ID: " + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(int id) {
        String query = "DELETE FROM students WHERE id = ?";
        Connection connection = connectToDb();
        try (
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("No student found with ID: " + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addStudentWithPhoto(String name, int age, String grade, InputStream photoStream) {
        String query = "INSERT INTO students (name, age, grade, photo) VALUES (?, ?, ?, ?)";
        Connection connection = connectToDb();
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.setString(3, grade);
            stmt.setBlob(4, photoStream);
            stmt.executeUpdate();
            System.out.println("Student with photo added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Object[]> viewStudents() {
        List<Object[]> students = new ArrayList<>();
        String query = "SELECT id, name, age, grade, photo FROM students";
        Connection connection = connectToDb();
        try (
             PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String grade = rs.getString("grade");
                byte[] photoBytes = rs.getBytes("photo");

                Object[] student = {id, name, age, grade, photoBytes};
                students.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return students;
    }

}

