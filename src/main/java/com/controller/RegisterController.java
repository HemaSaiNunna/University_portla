package com.controller;

import java.io.IOException;
import com.DAO.Student;
import com.Model.RegisterModel;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String name = request.getParameter("name");
        String mailid = request.getParameter("mailid");
        String phno = request.getParameter("phno");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmpassword");
        String role = request.getParameter("roles");

        // Check if the username already exists in the database
        Student studentDAO = new Student();
        boolean isUsernameTaken = studentDAO.isUsernameExist(username);
        
        if (isUsernameTaken) {
            request.setAttribute("error", "Username is already taken.");
            forward(request, response);
            return;
        }

        // Validate form fields before insertion
        if (!name.matches("^[A-Za-z\\s]+$")) {
            request.setAttribute("error", "Full name should only contain alphabets.");
            forward(request, response);
            return;
        }

        if (!mailid.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            request.setAttribute("error", "Please enter a valid email address.");
            forward(request, response);
            return;
        }

        if (!phno.matches("^[0-9]{10}$")) {
            request.setAttribute("error", "Phone number should be 10 digits long.");
            forward(request, response);
            return;
        }

        if (password.length() < 5 || password.length() > 12) {
            request.setAttribute("error", "Password must be between 5 and 12 characters.");
            forward(request, response);
            return;
        }

        if (!password.equals(confirmPassword)) {
            request.setAttribute("error", "Passwords do not match.");
            forward(request, response);
            return;
        }

        // Proceed with database insertion
        RegisterModel rm = new RegisterModel();
        rm.setUsername(username);
        rm.setName(name);
        rm.setMailid(mailid);
        rm.setPhno(phno);
        rm.setPassword(password);

        String status = studentDAO.insertData(rm, role);  // Insert based on role

        if ("success".equals(status)) {
            response.sendRedirect("Login.jsp");
        } else {
            request.setAttribute("error", "Registration failed. Try again.");
            forward(request, response);
        }
    }

    private void forward(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("Registration.jsp");
        rd.forward(request, response);
    }
}
