package com.controller;

import java.io.IOException;

import com.DAO.Student;
import com.DAO.Teacher;
import com.Model.LoginModel;
import com.Model.RegisterModel;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("roles");

        // Debugging: Log the received role and form data
        System.out.println("Received username: " + username);
        System.out.println("Received password: " + password);
        System.out.println("Received role: " + role);

        // Validate if the role is being received correctly
        if (role == null || role.isEmpty()) {
            request.setAttribute("error", "Role  is not selected.");
            RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
            rd.forward(request, response);
            return;
        }

        // Validate username and password based on role
        LoginModel lm = new LoginModel(username, password, role);

        if ("student".equalsIgnoreCase(role)) {
            // Student role handling
            Student s = new Student();
            String status = s.selectStudentData(lm);

            if ("success".equals(status)) {
                // Fetch user details for session after login
                RegisterModel userDetails = s.selectById(username);
                HttpSession session = request.getSession();
                session.setAttribute("name", username);
                session.setAttribute("userdetails", userDetails);

                // Redirect to student page
                response.sendRedirect("Student.jsp");  // Adjust path as needed
            } else {
                request.setAttribute("error", "Invalid username or password.");
                RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
                rd.forward(request, response);
            }
        } else if ("teacher".equalsIgnoreCase(role)) {
            // Teacher role handling
            Teacher teacherDAO = new Teacher();
            String status = teacherDAO.selectTeacherData(lm);

            if ("success".equals(status)) {
                // Fetch teacher details for session after login
                RegisterModel userDetails = teacherDAO.selectById(username);
                HttpSession session = request.getSession();
                session.setAttribute("name", username);
                session.setAttribute("userdetails", userDetails);

                // Redirect to teacher page
                response.sendRedirect("Teacher.jsp");  // Adjust path as needed
            } else {
                request.setAttribute("error", "Invalid username or password.");
                RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
                rd.forward(request, response);
            }
        } else if ("admin".equalsIgnoreCase(role)) {
            // Admin role handling (assuming no validation for admin credentials)
            // This part needs to be implemented as per your requirements
            HttpSession session = request.getSession();
            session.setAttribute("name", username);
            response.sendRedirect("Admin.jsp");  // Adjust path as needed
        } else {
            // Invalid role, send back to login
            request.setAttribute("error", "Invalid role selected.");
            RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
            rd.forward(request, response);
        }
    }
}
