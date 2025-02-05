<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.DAO.Student, com.Model.RegisterModel" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Students</title>
</head>
<body>
    <img src="<%= request.getContextPath() %>/images/projectimage.jpg"
         style="position: fixed; top: 0; left: 0; width: 100vw; height: 100vh; object-fit: cover; z-index: -1;" alt="Background Image">

    <div style="background-color: #4CAF50; color: white; padding: 15px; text-align: center;">
        <h1>Student List</h1>
    </div>

    <table border="1" style="width: 80%; text-align: center; margin: auto;">
        <tr>
            <th>Username</th>
            <th>Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Action</th>
        </tr>
        <%
            Student studentDAO = new Student();
            List<RegisterModel> students = studentDAO.getAllStudents();

            if (students != null && !students.isEmpty()) {
                for (RegisterModel student : students) {
        %>
        <tr>
            <td><%= student.getUsername() %></td>
            <td><%= student.getName() %></td>
            <td><%= student.getMailid() %></td>
            <td><%= student.getPhno() %></td>
            <td>
                <form action="DeleteStudentServlet" method="post" style="display: inline;">
                    <input type="hidden" name="username" value="<%= student.getUsername() %>">
                    <input type="submit" value="Delete" onclick="return confirm('Are you sure you want to delete this student?');">
                </form>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="5">No students found.</td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>
