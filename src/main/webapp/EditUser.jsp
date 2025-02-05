<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.Model.RegisterModel"%>
<%@ page import="com.DAO.Student"%>

<%
    // Check session validity
    if (session == null || session.getAttribute("name") == null) {
        response.sendRedirect("Login.jsp");
        return;
    }

    // Retrieve user details from the session
    RegisterModel userDetails = (RegisterModel) session.getAttribute("userdetails");
    if (userDetails == null) {
        response.sendRedirect("Login.jsp");
        return;
    }

    // Pre-fill fields
    String username = userDetails.getUsername();
    String name = userDetails.getName();
    String mailid = userDetails.getMailid();
    String phno = userDetails.getPhno();
    String password = userDetails.getPassword();

    // Handle POST request for updating details
    if ("POST".equalsIgnoreCase(request.getMethod())) {
        name = request.getParameter("name");
        mailid = request.getParameter("mailid");
        phno = request.getParameter("phno");
        password = request.getParameter("password");

        // Update student details in the database
        RegisterModel updatedDetails = new RegisterModel();
        updatedDetails.setUsername(username);
        updatedDetails.setName(name);
        updatedDetails.setMailid(mailid);
        updatedDetails.setPhno(phno);
        updatedDetails.setPassword(password);

        // Use Student DAO for updating student details
        Student studentDAO = new Student();
        String status = studentDAO.updateUserDetails(updatedDetails);

        if ("success".equals(status)) {
            session.setAttribute("userdetails", updatedDetails); // Update session with new details
            response.sendRedirect("Student.jsp"); // Redirect to Student.jsp after success
            return;
        } else {
            request.setAttribute("error", "Failed to update details. Please try again.");
        }
    }
%>

<!DOCTYPE html>
<html>
<head>
<title>Edit Student Details</title>
</head>
<body>
 <!-- Background Image -->
   
  <img src="<%= request.getContextPath() %>/images/projectimage.jpg" 
         style="position: fixed; top: 0; left: 0; width: 100vw; height: 100vh; object-fit: cover; z-index: -1;" alt="Background Image">

	<div
		style="background-color: #4CAF50; color: white; padding: 15px; text-align: center;">
		<h1>Edit Student Details</h1>
	</div>
	<br>
	<br>
	<br>
	<br>

	<% if (request.getAttribute("error") != null) { %>
	<p style="color: red; text-align: center;"><%= request.getAttribute("error") %></p>
	<% } %>

	<form action="EditUser.jsp" method="post">
		<table
			style="border: 1; width: 70%; text-align: center; margin: auto;">
			<tr>
				<td style="padding: 10px;">Name:</td>
				<td><input type="text" name="name" value="<%= name %>"
					style="border: none; outline: none;" required></td>
			</tr>
			<tr>
				<td style="padding: 10px;">Username:</td>
				<td><input type="text" name="username" value="<%= username %>"
					readonly style="border: none; outline: none;"></td>
			</tr>
			<tr>
				<td style="padding: 10px;">Email:</td>
				<td><input type="email" name="mailid" value="<%= mailid %>"
					style="border: none; outline: none;" required></td>
			</tr>
			<tr>
				<td style="padding: 10px;">Phone:</td>
				<td><input type="text" name="phno" value="<%= phno %>"
					style="border: none; outline: none;" required></td>
			</tr>
			<tr>
				<td style="padding: 10px;">Password:</td>
				<td><input type="password" name="password"
					value="<%= password %>" style="border: none; outline: none;"
					required></td>
			</tr>
		</table>
		<div style="text-align: center; margin-top: 20px;">
			<button type="submit">Update</button>
		</div>
	</form>
</body>
</html>
