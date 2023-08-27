package com.learning.hello.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TennisDB {
	private Connection cnx;
	private static final String URI = "jdbc:mysql://localhost:3306/tennis";
	private static final String USERNAME = "chaitanya";
	private static final String PASSWORD = "chaitanya";

	public TennisDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cnx = DriverManager.getConnection(URI, USERNAME, PASSWORD);
			System.out.println("tennis db connection successful :)");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
