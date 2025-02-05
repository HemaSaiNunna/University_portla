package com.DAO;

import com.Model.LoginModel;
import com.Model.RegisterModel;
import java.util.List;

public interface StudentInterface {
	public String insertData(RegisterModel rm, String role); // Insert data based on role (student, admin, teacher)

	public String selectStudentData(LoginModel lm); // Select student data based on login credentials

	public RegisterModel selectById(String username); // Select user by username

	public List<RegisterModel> getAllStudents(); // Get all students from the database

	public String updateUserDetails(RegisterModel rm); // Update user details based on username

	public boolean isUsernameExist(String username); // Check if the username exists

	boolean deleteStudentByUsername(String username);
}
