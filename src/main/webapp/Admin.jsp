<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
</head>
<body>
 <!-- Background Image -->
   
  <img src="<%= request.getContextPath() %>/images/projectimage.jpg" 
         style="position: fixed; top: 0; left: 0; width: 100vw; height: 100vh; object-fit: cover; z-index: -1;" alt="Background Image">


<div style="background-color: #4CAF50; color: white; padding: 15px; text-align: center;">
    <h1>Admin Dashboard</h1>
</div>

<div style="margin: 0; padding: 0; width: 200px; background-color: #333; position: fixed; height: 100%; overflow: auto;">
    <a href="View_Students.jsp" style="display: block; color: white; padding: 16px; text-decoration: none;">View Student</a>
    <a href="View_Teachers.jsp" style="display: block; color: white; padding: 16px; text-decoration: none;">View Teacher</a>
    </div>

<div style="margin-left: 210px; padding: 20px;">
    <h2 style="text-align:center">WELCOME TO ADMIN DASHBOARD</h2>
</div>

</body>
</html>
