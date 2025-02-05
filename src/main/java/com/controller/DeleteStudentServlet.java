package com.controller;

import java.io.IOException;
import com.DAO.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteStudentServlet")
public class DeleteStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        System.out.println("Attempting to delete: " + username); // Debugging

        Student studentDAO = new Student();
        boolean isDeleted = studentDAO.deleteStudentByUsername(username);

        if (isDeleted) {
            response.sendRedirect("View_Students.jsp"); // Refresh page after deletion
        } else {
            response.getWriter().println("Failed to delete student.");
        }
    }
}
