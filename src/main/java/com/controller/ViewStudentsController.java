package com.controller;

import java.io.IOException;
import java.util.List;

import com.DAO.Student;
import com.Model.RegisterModel;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/View_Students")
public class ViewStudentsController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Student studentDAO = new Student();

        // Get all students
        List<RegisterModel> studentsList = studentDAO.getAllStudents();

        request.setAttribute("students", studentsList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("View_Students.jsp");
        dispatcher.forward(request, response);
    }
}
