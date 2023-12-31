package com.learning.hello;

import java.io.IOException;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import com.learning.hello.controller.HiLoController;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/hilo")
public class HighLowGameServlet extends HttpServlet {
	  
	private static final long serialVersionUID = 1436083217177831521L;
	private HiLoController hlc;
	private JakartaServletWebApplication application;
	private TemplateEngine templateEngine;

	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	    hlc = new HiLoController();
	    hlc.reset();
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
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
	    final IWebExchange webExchange = this.application.buildExchange(req, res);
	    final WebContext ctx = new WebContext(webExchange);
	    templateEngine.process("hilo", ctx, res.getWriter());
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		hlc.setGuess(Integer.parseInt(req.getParameter("guess")));
		var out = res.getWriter();
	    final IWebExchange webExchange = 
	            this.application.buildExchange(req, res);
	    final WebContext ctx = new WebContext(webExchange);
	    ctx.setVariable("feedback", hlc.feedback());
	    templateEngine.process("hilo", ctx, out);
	    if (hlc.judge() == 0)
	      hlc.reset();
	}

}
