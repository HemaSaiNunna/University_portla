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
    <title>Home Page</title>
</head>
<body>
 <!-- Background Image -->
   
  <img src="<%= request.getContextPath() %>/images/projectimage.jpg" 
         style="position: fixed; top: 0; left: 0; width: 100vw; height: 100vh; object-fit: cover; z-index: -1;" alt="Background Image">

<div style="background-color: #4CAF50; color: white; padding: 15px; text-align: center;">
    <h1>Welcome, <%= session.getAttribute("name") %> (<%= role %>)!</h1>
</div>
    <% if (userDetails != null) { %>
        <table border="1" style="width:50%; border-collapse:collapse;">
            <tr><th>Field</th><th>Details</th></tr>
       		 <tr><td>Name</td><td><%= userDetails.getName() %><br><br></td></tr>
            <tr><td>Username</td><td><%= userDetails.getUsername() %><br><br></td></tr>
            <tr><td>Email</td><td><%= userDetails.getMailid() %><br><br></td></tr>
            <tr><td>Phone</td><td><%= userDetails.getPhno() %><br><br></td></tr>
            <tr><td>Password</td><td><%= userDetails.getPassword() != null ? userDetails.getPassword() : "Not Available" %><br><br></td></tr>
            
        </table>

        <form action="EditUser.jsp" method="get">
            <button type="submit">EDIT</button>
        </form>
    <% } else { %>
        <p>User details not found.</p>
    <% } %>
</body>
</html>
