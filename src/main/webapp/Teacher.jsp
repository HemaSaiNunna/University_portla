<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.Model.RegisterModel" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<%
    if (session == null || session.getAttribute("name") == null) {
        response.sendRedirect("Login.jsp");
        return;
    }

    RegisterModel userDetails = (RegisterModel) session.getAttribute("userdetails");
    String role = (String) session.getAttribute("role"); // Either "Student" or "Teacher"
%>

<!DOCTYPE html>
<html>
<head>
    <title>Teacher Page</title>
</head>
<body>
 <!-- Background Image -->
   
  <img src="<%= request.getContextPath() %>/images/projectimage.jpg" 
         style="position: fixed; top: 0; left: 0; width: 100vw; height: 100vh; object-fit: cover; z-index: -1;" alt="Background Image">

    <div style="background-color: #4CAF50; color: white; padding: 15px; text-align: center;">
        <h1>Welcome, <%= session.getAttribute("name") %> </h1>
    </div><br><br><br><br>

    <% if (userDetails != null) { %>
        <table border="1" style="width:70%; border-collapse:collapse; text-align: center; margin: auto;">
            <tr><th>Field</th><th>Details</th></tr>
            <tr><td>Name</td><td><%= userDetails.getName() %></td></tr>
            <tr><td>Username</td><td><%= userDetails.getUsername() %></td></tr>
            <tr><td>Email</td><td><%= userDetails.getMailid() %></td></tr>
            <tr><td>Phone</td><td><%= userDetails.getPhno() %></td></tr>
        </table>

        <form action="EditTeacher.jsp" method="get">
            <div style="text-align: center;">
                <br><br><button type="submit">EDIT</button>
            </div>
        </form>
            <% } else { %>
        <p>User details not found.</p>
    <% } %>
    <form action="Login.jsp" method="get">
         <div style="text-align: center;">
                <br><br><button type="submit"> Logout</button>
            </div>
        </form>
    
</body>
</html>
