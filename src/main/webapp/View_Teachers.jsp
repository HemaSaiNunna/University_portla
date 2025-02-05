<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.DAO.Teacher, com.Model.RegisterModel" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Teachers</title>
</head>
<body>
    <img src="<%= request.getContextPath() %>/images/projectimage.jpg" 
         style="position: fixed; top: 0; left: 0; width: 100vw; height: 100vh; object-fit: cover; z-index: -1;" alt="Background Image">

    <div style="background-color: #4CAF50; color: white; padding: 15px; text-align: center;">
        <h1>Teacher List</h1>
    </div>

    <table border="1" style="width: 80%; text-align: center;">
        <tr>
            <th>Username</th>
            <th>Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Action</th> <!-- New Action Column -->
        </tr>
        <%
            Teacher teacherDAO = new Teacher();
            List<RegisterModel> teachers = teacherDAO.getAllTeachers();

            if (teachers != null && !teachers.isEmpty()) {
                for (RegisterModel teacher : teachers) {
        %>
        <tr>
            <td><%= teacher.getUsername() %></td>
            <td><%= teacher.getName() %></td>
            <td><%= teacher.getMailid() %></td>
            <td><%= teacher.getPhno() %></td>
            <td>
                <form action="DeleteTeacherServlet" method="post" onsubmit="return confirm('Are you sure you want to delete this teacher?');">
                    <input type="hidden" name="username" value="<%= teacher.getUsername() %>">
                    <input type="submit" value="Delete">
                </form>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="5">No teachers found.</td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>
