package com.learning.hello.controller;

import java.io.IOException;
import java.io.Writer;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;

import com.learning.hello.controller.exception.UnsupportedActionException;
import com.learning.hello.model.mangatha.Mangatha;

import jakarta.servlet.http.HttpServletResponse;

public class MangathaController implements IController {
	private Mangatha model;
	public MangathaController() {
		this.model = Mangatha.get();
		model.init();
	}
	
	@Override
	public void processGet(TemplateEngine templateEngine, IWebExchange webExchange, HttpServletResponse res) throws UnsupportedActionException, IOException{
		final WebContext ctx = new WebContext(webExchange);
		Writer out = res.getWriter();
		ctx.setVariable("winner", model.getWinner());	
		ctx.setVariable("isWinner", model.getIsWinnerAvailable());
		ctx.setVariable("isGameStart", model.getIsGameStart());
		ctx.setVariable("inPile", model.getInPile());
		ctx.setVariable("outPile", model.getOutPile());
		ctx.setVariable("players", model.getPlayers());
		templateEngine.process("register", ctx,out);
	}
	
	@Override
	public void processPost(TemplateEngine templateEngine,IWebExchange webExchange,HttpServletResponse res) throws UnsupportedActionException, IOException {
		String action = webExchange.getRequest().getParameterValue("action");
		String name = webExchange.getRequest().getParameterValue("name");
		String bet = webExchange.getRequest().getParameterValue("bet");
		String rank = webExchange.getRequest().getParameterValue("rank");
		String suit = webExchange.getRequest().getParameterValue("suit");
		String pos = webExchange.getRequest().getParameterValue("position");
		
		if (action == null) {
			model.reset();
		}
		else if (action.equals("add")) {
			int intBet = bet != null && !bet.isEmpty() ? Integer.parseInt(bet) : 5;
			model.addPlayer(name, intBet, pos, rank.concat(suit));			
		}
		else if (action.equals("play")) {
			model.setIsGameStart();
		}
		else if (action.equals("draw")) {
			model.performAction();
		}
		else if(action.equals("reset")) {
			model.reset();
		}
		
		res.sendRedirect(webExchange.getRequest().getRequestPath());
	}

}
