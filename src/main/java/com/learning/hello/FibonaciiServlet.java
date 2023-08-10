package com.learning.hello;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FibonaciiServlet extends HttpServlet {
	
	public String generateFiboSeq(int n) {
		List<String> seq = new ArrayList<>(Arrays.asList("0","1"));
		int t1 = 0, t2 = 1,t3;
		for (int i = 2;i < n; i++) {
			t3 = t1 + t2;
			seq.add(String.valueOf(t3));
			t1 = t2;
			t2 = t3;
		}
		return String.join(", ", seq);
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		int n = Integer.parseInt(req.getParameter("n"));
		try {
			PrintWriter out = res.getWriter();
			out.print(String.format("<h1>fibonacii series: %s</h1>", generateFiboSeq(n)));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServerException,IOException {
		PrintWriter out = res.getWriter();
		if (req.getParameter("formLimit") == null) {
		      out.println("This is not the way to access this resource. Please give a value for the parameter 'n' in your query ");
		      return;
		    } else {
		      int limit = Integer.valueOf(req.getParameter("formLimit"));
		      System.out.println(limit);
		      out.println(String.format("<p>%s</p>", generateFiboSeq(limit)));
		    }
	}
	
}
