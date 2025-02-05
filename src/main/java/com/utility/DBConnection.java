package com.utility;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	static Connection con;

	public static Connection getCon() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
			System.out.println("connection success");
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}
}
