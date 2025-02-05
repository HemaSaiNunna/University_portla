package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Model.LoginModel;
import com.Model.RegisterModel;
import com.utility.DBConnection;

public class Student implements StudentInterface {

    private Connection connection;

    public Student() {
        this.connection = DBConnection.getCon(); // Assuming DBConnection.getCon() provides a valid connection
    }

    @Override
    public String selectStudentData(LoginModel lm) {
        String query = null;

        // Determine the table based on the role
        if ("admin".equals(lm.getRole())) {
            query = "SELECT * FROM admin WHERE username = ? AND password = ?";
        } else if ("student".equals(lm.getRole())) {
            query = "SELECT * FROM user WHERE username = ? AND password = ?";
        } else if ("teacher".equals(lm.getRole())) {
            query = "SELECT * FROM teacher WHERE username = ? AND password = ?";
        } else {
            return "Invalid role";
        }

        try (PreparedStatement ps = connection.prepareStatement(query)) {
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
        String query = "SELECT * FROM user WHERE username = ?";
        RegisterModel user = null;
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new RegisterModel();
                    user.setUsername(rs.getString("username"));
                    user.setName(rs.getString("name"));
                    user.setMailid(rs.getString("mailid"));
                    user.setPhno(rs.getString("phno"));
                    user.setPassword(rs.getString("password"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<RegisterModel> getAllStudents() {
        List<RegisterModel> studentsList = new ArrayList<>();
        String query = "SELECT username, name, mailid, phno FROM user";
        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                RegisterModel student = new RegisterModel();
                student.setUsername(rs.getString("username"));
                student.setName(rs.getString("name"));
                student.setMailid(rs.getString("mailid"));
                student.setPhno(rs.getString("phno"));
                studentsList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentsList;
    }

    @Override
    public String insertData(RegisterModel rm, String role) {
        String query = "";
        if ("student".equals(role)) {
            query = "INSERT INTO user (username, name, mailid, phno, password) VALUES (?, ?, ?, ?, ?)";
        } else if ("admin".equals(role)) {
            query = "INSERT INTO admin (username, name, mailid, phno, password) VALUES (?, ?, ?, ?, ?)";
        } else if ("teacher".equals(role)) {
            query = "INSERT INTO teacher (username, name, mailid, phno, password) VALUES (?, ?, ?, ?, ?)";
        }

        try (PreparedStatement ps = connection.prepareStatement(query)) {
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

    @Override
    public String updateUserDetails(RegisterModel rm) {
        if (rm == null || rm.getUsername() == null || rm.getUsername().isEmpty()) {
            return "Error: Invalid input data";
        }

        // Add password to the update query
        String query = "UPDATE user SET name = ?, mailid = ?, phno = ?, password = ? WHERE username = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {

            // Log the data being passed
            System.out.println("Updating details for username: " + rm.getUsername());
            System.out.println("Name: " + rm.getName() + ", Mail ID: " + rm.getMailid() + ", Phone: " + rm.getPhno() + ", Password: " + rm.getPassword());

            // Set parameters
            ps.setString(1, rm.getName());
            ps.setString(2, rm.getMailid());
            ps.setString(3, rm.getPhno());
            ps.setString(4, rm.getPassword());  // Set password
            ps.setString(5, rm.getUsername());

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
            	System.out.println("Updating password for: " + rm.getUsername());
            	System.out.println("Password: " + rm.getPassword());

                return "success";
            } else {
                return "No user found with the provided username";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: Unable to update user details";
        }
    }

    public boolean isUsernameExist(String username) {
        String query = "SELECT COUNT(*) FROM user WHERE username = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;  // If count is greater than 0, username exists
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
 
        @Override
        public boolean deleteStudentByUsername(String username) {
            String query = "DELETE FROM user WHERE username = ?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, username);
                int rowsAffected = ps.executeUpdate();
                return rowsAffected > 0; // Return true if deletion was successful
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }
    


}
