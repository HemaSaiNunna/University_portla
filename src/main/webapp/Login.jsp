<!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
 <!-- Background Image -->
   
  <img src="<%= request.getContextPath() %>/images/projectimage.jpg" 
         style="position: fixed; top: 0; left: 0; width: 100vw; height: 100vh; object-fit: cover; z-index: -1;" alt="Background Image">

    <form method="post" action="Login" style="text-align:center;">
    <div style="background-color: #4CAF50; color: white; padding: 15px; text-align: center;">
        <h1>LOGIN PAGE</h1></div>
        <label for="roles">Choose your role:</label>
        <select id="roles" name="roles">
            <option value="student">Student</option>
            <option value="admin">Admin</option>
            <option value="teacher">Teacher</option>
        </select><br><br>
        Enter Username: <input type="text" name="username" required><br><br>
        Enter Password: <input type="password" name="password" required><br><br>
        <button type="submit">Login</button>
    </form>
    <div style="text-align:center;">
        <p>Don't Have an Account? <a href="Registration.jsp">Register Here</a></p>
    </div>
</body>
</html>
