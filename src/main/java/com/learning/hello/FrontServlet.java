package com.learning.hello;

import java.io.IOException;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import com.learning.hello.controller.ControllerFactory;
import com.learning.hello.controller.IController;
import com.learning.hello.controller.exception.UnsupportedActionException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/service/*")
public class FrontServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private JakartaServletWebApplication application;
	private TemplateEngine templateEngine;
	
	@Override
	public void init() throws ServletException {
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
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String path = req.getPathInfo();
		IController controller = ControllerFactory.get(path);
		if (controller  == null) {
			res.sendError(404, path + "not supported");
			return;
		}
		final IWebExchange webExchange = this.application.buildExchange(req, res);
		try {
			controller.processGet(templateEngine, webExchange, res);
		} catch(UnsupportedActionException e) {
			throw new ServletException(e);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getPathInfo();
		IController controller = ControllerFactory.get(action);
		final IWebExchange webExchange = this.application.buildExchange(req, res);
		try {
			controller.processPost(templateEngine, webExchange, res);
		} catch(UnsupportedActionException e) {
			throw new ServletException(e);
		}
	}
	
}
