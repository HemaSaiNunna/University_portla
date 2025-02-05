package com.DAO;

import com.Model.RegisterModel;
import com.Model.LoginModel;
import com.utility.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Teacher implements TeacherInterface {

    @Override
    public String selectTeacherData(LoginModel lm) {
        String query = "SELECT * FROM teacher WHERE username = ? AND password = ?";
        try (Connection conn = DBConnection.getCon();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, lm.getUsername());
            ps.setString(2, lm.getPassword());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return "success";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "failure";
    }

    @Override
    public RegisterModel selectById(String username) {
        String query = "SELECT * FROM teacher WHERE username = ?";
        RegisterModel teacher = null;
        try (Connection conn = DBConnection.getCon();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    teacher = new RegisterModel();
                    teacher.setUsername(rs.getString("username"));
                    teacher.setName(rs.getString("name"));
                    teacher.setMailid(rs.getString("mailid"));
                    teacher.setPhno(rs.getString("phno"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacher;
    }


    @Override
    public List<RegisterModel> getAllTeachers() {
        List<RegisterModel> teachersList = new ArrayList<>();
        String query = "SELECT username, name, mailid, phno FROM teacher";

        try (Connection conn = DBConnection.getCon();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                RegisterModel teacher = new RegisterModel();
                teacher.setUsername(rs.getString("username"));
                teacher.setName(rs.getString("name"));
                teacher.setMailid(rs.getString("mailid"));
                teacher.setPhno(rs.getString("phno"));
                teachersList.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teachersList;
    }

    @Override
    public String insertData(RegisterModel rm) {
        String query = "INSERT INTO teacher (username, name, mailid, phno, password) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getCon();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, rm.getUsername());
            ps.setString(2, rm.getName());
            ps.setString(3, rm.getMailid());
            ps.setString(4, rm.getPhno());
            ps.setString(5, rm.getPassword());

            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                return "success";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "failure";
    }

    public String updateTeacherDetails(RegisterModel rm) {
        if (rm == null || rm.getUsername() == null || rm.getUsername().isEmpty()) {
            return "Error: Invalid input data";
        }

        String query = "UPDATE teacher SET name = ?, mailid = ?, phno = ?, password = ? WHERE username = ?";
        try (Connection conn = DBConnection.getCon();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, rm.getName());
            ps.setString(2, rm.getMailid());
            ps.setString(3, rm.getPhno());
            ps.setString(4, rm.getPassword());
            ps.setString(5, rm.getUsername());

            // Debugging: Log the query and values being used
            System.out.println("Executing update query: " + query);
            System.out.println("Name: " + rm.getName());
            System.out.println("Mail: " + rm.getMailid());
            System.out.println("Phone: " + rm.getPhno());
            System.out.println("Password: " + rm.getPassword());
            System.out.println("Username: " + rm.getUsername());

            int rowsUpdated = ps.executeUpdate();
            System.out.println("Rows updated: " + rowsUpdated);

            if (rowsUpdated > 0) {
                return "success";
            } else {
                return "failure";  // If no rows are updated, it might mean that the username doesn't exist
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: Unable to update teacher details";
        }
    }
    public boolean isUsernameExist(String username) {
        String query = "SELECT COUNT(*) FROM teacher WHERE username = ?";
        try (Connection conn = DBConnection.getCon();
             PreparedStatement ps = conn.prepareStatement(query)) {
             
            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;  // If count is greater than 0, username exists
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // Default to false if no match
    }
    public boolean deleteTeacherByUsername(String username) {
        String query = "DELETE FROM teacher WHERE username = ?";
        try (Connection conn = DBConnection.getCon();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, username);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

	
}
