package com.learning.hello;

import java.io.IOException;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import controller.NoticeBoardController;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/notices")
public class NoticeBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 3307492724320416581L;
	
	private JakartaServletWebApplication application;
	private TemplateEngine templateEngine;
	private NoticeBoardController controller;
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		controller = new NoticeBoardController();
		application = JakartaServletWebApplication.buildApplication(getServletContext());
		final WebApplicationTemplateResolver templateResolver = 
		        new WebApplicationTemplateResolver(application);
		    templateResolver.setTemplateMode(TemplateMode.HTML);
		    templateResolver.setPrefix("/WEB-INF/templates/");
		    templateResolver.setSuffix(".html");
		    templateEngine = new TemplateEngine();
		    templateEngine.setTemplateResolver(templateResolver);
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		final IWebExchange webExchange = this.application.buildExchange(req, res);
		controller.processGet(webExchange, templateEngine, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		final IWebExchange webExchange = this.application.buildExchange(req, res);
		controller.processPost(webExchange, templateEngine, res);
	}
}
