package com.learning.hello;

import java.io.IOException;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import controller.FibonacciGameController;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/fiboGame")
public class FibonacciGameServlet extends HttpServlet {
	private static final long serialVersionUID = 5704232074526414123L;

	private JakartaServletWebApplication application;
	private TemplateEngine templateEngine;
	private FibonacciGameController controller;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		controller =  new FibonacciGameController();
		
		application = JakartaServletWebApplication.buildApplication(getServletContext());
		final WebApplicationTemplateResolver templateResolver = 
		        new WebApplicationTemplateResolver(application);
		    templateResolver.setTemplateMode(TemplateMode.HTML);
		    templateResolver.setPrefix("/WEB-INF/templates/");
		    templateResolver.setSuffix(".html");
		    templateEngine = new TemplateEngine();
		    templateEngine.setTemplateResolver(templateResolver);
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		final IWebExchange webExchange = this.application.buildExchange(req, res);
		controller.processGet(webExchange, templateEngine, res);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		final IWebExchange webExchange = this.application.buildExchange(req, res);
		controller.processPost(webExchange, templateEngine, res);
	}
}
