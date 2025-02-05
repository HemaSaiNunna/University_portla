package com.controller;

import java.io.IOException;

import com.DAO.Student;
import com.DAO.Teacher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/checkUsername")
public class CheckUsernameServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String role = request.getParameter("role");

        System.out.println("Received Username: " + username);  // Debugging: Check username received
        System.out.println("Received Role: " + role);  // Debugging: Check role received

        boolean isUsernameTaken = false;

        // Check for username existence based on the role
        if ("student".equalsIgnoreCase(role)) {
            Student studentDAO = new Student();
            isUsernameTaken = studentDAO.isUsernameExist(username);
        } else if ("teacher".equalsIgnoreCase(role)) {
            Teacher teacherDAO = new Teacher(); // Create an instance of Teacher class
            isUsernameTaken = teacherDAO.isUsernameExist(username); // Check username for teacher
        }

        System.out.println("Is Username Taken: " + isUsernameTaken);  // Debugging: Check username availability

        // Send response based on availability
        if (isUsernameTaken) {
            response.getWriter().write("exists");  // Corrected the response to return 'exists' when taken
        } else {
            response.getWriter().write("available");  // If not taken, return 'available'
        }
    }
}
