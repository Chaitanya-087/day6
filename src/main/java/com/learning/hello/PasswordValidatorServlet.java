package com.learning.hello;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.ServerException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PasswordValidatorServlet extends HttpServlet{
	
	@Override
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServerException, IOException{
		Path path = Paths.get("/home/chaitanya/eclipse-workspace/learning-web/src/main/java/com/learning/hello/bannedPasswords.txt");
		String matchString = String.join("", Files.readAllLines(path));
		String pwd = req.getParameter("password");
		PrintWriter out = res.getWriter();
		
		if (matchString.indexOf(pwd) == -1) {
			out.print("successfull");
		} else {
			out.print("not a valid passwords");
		}
	}
}
