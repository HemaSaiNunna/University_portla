package com.controller;

import java.io.IOException;

import com.DAO.Teacher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteTeacherServlet")
public class DeleteTeacherServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        Teacher teacherDAO = new Teacher();
        boolean isDeleted = teacherDAO.deleteTeacherByUsername(username);

        if (isDeleted) {
            response.sendRedirect("View_Teachers.jsp"); // Refresh after deletion
        } else {
            response.getWriter().println("Failed to delete teacher.");
        }
    }
}
