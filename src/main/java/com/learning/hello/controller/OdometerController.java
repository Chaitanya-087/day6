package com.learning.hello.controller;

import java.io.IOException;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;

import com.learning.hello.controller.exception.UnsupportedActionException;
import com.learning.hello.model.odometer.Odometer;

import jakarta.servlet.http.HttpServletResponse;

public class OdometerController implements IController{

	private Odometer odometer;
	
	public OdometerController() {
		odometer = new Odometer(5);
	}
	
	@Override
	public void processGet(TemplateEngine templateEngine, IWebExchange webExchange, HttpServletResponse res)
			throws UnsupportedActionException, IOException {
	    final WebContext ctx = new WebContext(webExchange);
	    ctx.setVariable("reading", odometer.getReading());
	    templateEngine.process("odometer", ctx, res.getWriter());
	}

	@Override
	public void processPost(TemplateEngine templateEngine, IWebExchange webExchange, HttpServletResponse res)
			throws UnsupportedActionException, IOException {
		var req = webExchange.getRequest();
		String action = req.getParameterValue("action");
		String size = req.getParameterValue("size");
					
		if (action.equals("reset")) {
			odometer.reset();
		} else if (action.equals("prev")) {
			odometer.decrement();
		} else if (action.equals("next")) {
			odometer.increment();
		} else if (action.equals("resize")) {
			int temp = size.isEmpty() ? 4 : Integer.parseInt(size);
			odometer = new Odometer(temp);
		}
		res.sendRedirect(req.getRequestPath());
	}
}
