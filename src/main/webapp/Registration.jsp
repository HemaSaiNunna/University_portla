<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration Page</title>
    <style>
        .error {
            color: red;
            font-size: 12px;
            margin-top: 4px;
        }

        .field {
            margin-bottom: 20px;
            position: relative;
        }

        .field input, .field select {
            width: 200px;
            padding: 8px;
        }

        .field label {
            margin-right: 10px;
        }

        .error-message {
          
            right: 0;
            top: 20px;
            font-size: 12px;
            color: red;
        }
    </style>
    <script>
        function showHint(field) {
            const id = field.id;
            const errorField = document.getElementById(id + "-error");
            errorField.textContent = ""; // Clear error message on focus
        }

        function validateField(field) {
            const id = field.id;
            const value = field.value.trim();
            const errorField = document.getElementById(id + "-error");

            if (id === "username") {
                const usernamePattern = /^[a-zA-Z0-9._%+-]+$/;
                if (!usernamePattern.test(value)) {
                    errorField.textContent = "Username should only contain alphanumeric characters.";
                    return false;
                }
            } else if (id === "name") {
                const namePattern = /^[A-Za-z\s]+$/;
                if (!namePattern.test(value)) {
                    errorField.textContent = "Full name should only contain alphabets.";
                    return false;
                }
            } else if (id === "mailid") {
                const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
                if (!emailPattern.test(value)) {
                    errorField.textContent = "Please enter a valid email address.";
                    return false;
                }
            } else if (id === "phno") {
                const phonePattern = /^[0-9]{10}$/;
                if (!phonePattern.test(value)) {
                    errorField.textContent = "Phone number should be 10 digits long.";
                    return false;
                }
            } else if (id === "password") {
                if (value.length < 5 || value.length > 12) {
                    errorField.textContent = "Password must be between 5 and 12 characters.";
                    return false;
                }
            } else if (id === "confirmpassword") {
                const password = document.getElementById("password").value;
                if (value !== password) {
                    errorField.textContent = "Passwords do not match.";
                    return false;
                }
            }

            errorField.textContent = ""; // Clear error message if valid
            return true;
        }

        function checkUsernameAvailability() {
            const username = document.getElementById("username").value;
            const role = document.getElementById("roles").value; // Get the role value
            const errorMessage = document.getElementById("username-error"); // Get the element to display error

            const xhr = new XMLHttpRequest();
            xhr.open("POST", "checkUsername", true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

            xhr.onreadystatechange = function () {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    const result = xhr.responseText;

                    // Show the error message under the username field
                    if (result === "exists") {
                        errorMessage.textContent = "Username already taken!";
                        errorMessage.style.color = "red"; // Set text color to red
                    } else if (result === "available") {
                        errorMessage.textContent = "Username is  Good!";
                        errorMessage.style.color = "green"; // Set text color to green
                    }
                }
            };

            // Send username and role via POST request
            xhr.send("username=" + username + "&role=" + role);
        }


        function validateForm() {
            let isValid = true;
            const fields = document.querySelectorAll("input, select");
            fields.forEach((field) => {
                if (!validateField(field)) {
                    isValid = false;
                }
            });
            return isValid;
        }
    </script>
</head>
<body>
 <!-- Background Image -->
   
  <img src="<%= request.getContextPath() %>/images/projectimage.jpg" 
         style="position: fixed; top: 0; left: 0; width: 100vw; height: 100vh; object-fit: cover; z-index: -1;" alt="Background Image">

    <form method="post" action="register" style="text-align:center;" onsubmit="return validateForm()">
        <div style="background-color: #4CAF50; color: white; padding: 15px; text-align: center;">
            <h1>REGISTRATION FORM</h1>
        </div>
        
        <div class="field">
            <label for="roles">Select Role:</label>
            <select name="roles" id="roles">
                <option value="student">Student</option>
                <option value="admin">Admin</option>
                <option value="teacher">Teacher</option>
            </select>
        </div>

        <div class="field">
            <label for="username">Enter Username:</label>
            <input type="text" name="username" id="username" placeholder="USERNAME" 
                   onfocus="showHint(this)" onblur="checkUsernameAvailability()">
            <div id="username-error" class="error-message"></div>
        </div>

        <div class="field">
            <label for="name">Enter Full Name:</label>
            <input type="text" name="name" id="name" placeholder="NAME" 
                   onfocus="showHint(this)" onblur="validateField(this)">
            <div id="name-error" class="error-message"></div>
        </div>

        <div class="field">
            <label for="mailid">Enter the Mail ID:</label>
            <input type="text" name="mailid" id="mailid" placeholder="MAILID" 
                   onfocus="showHint(this)" onblur="validateField(this)">
            <div id="mailid-error" class="error-message"></div>
        </div>

        <div class="field">
            <label for="phno">Enter the Phone Number:</label>
            <input type="text" name="phno" id="phno" placeholder="PHNO" 
                   onfocus="showHint(this)" onblur="validateField(this)">
            <div id="phno-error" class="error-message"></div>
        </div>

        <div class="field">
            <label for="password">Enter Password:</label>
            <input type="password" name="password" id="password" placeholder="PASSWORD" 
                   onfocus="showHint(this)" onblur="validateField(this)">
            <div id="password-error" class="error-message"></div>
        </div>

        <div class="field">
            <label for="confirmpassword">Confirm Password:</label>
            <input type="password" name="confirmpassword" id="confirmpassword" placeholder="CONFIRM PASSWORD" 
                   onfocus="showHint(this)" onblur="validateField(this)">
            <div id="confirmpassword-error" class="error-message"></div>
        </div>

        

        <button type="submit">Submit</button>
    </form>
</body>
</html>
