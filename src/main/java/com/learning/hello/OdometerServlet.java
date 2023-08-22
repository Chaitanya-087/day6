package com.learning.hello;

import java.io.IOException;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import controller.Odometer;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/odometer")
public class OdometerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Odometer odometer;
	private JakartaServletWebApplication application;
	private TemplateEngine templateEngine;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		odometer = new Odometer(5);
		
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
		String action = req.getParameter("action");
		if (action == null || action.equals("reset")){
			odometer.reset();
		}
		else if (action.equals("prev")) {
			odometer.decrement();
		} else if (action.equals("next")) {
			odometer.increment();
		} 
	    final IWebExchange webExchange = this.application.buildExchange(req, res);
	    final WebContext ctx = new WebContext(webExchange);
	    ctx.setVariable("reading", odometer.getReading());
	    templateEngine.process("odometer", ctx, res.getWriter());
	}
	
	
}
