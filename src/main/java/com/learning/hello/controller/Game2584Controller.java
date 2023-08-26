package com.learning.hello.controller;

import java.io.IOException;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;

import com.learning.hello.controller.exception.UnsupportedActionException;
import com.learning.hello.model.game2584.Game2584;

import jakarta.servlet.http.HttpServletResponse;


public class Game2584Controller implements IController {
	private Game2584 game;

	public Game2584Controller() {
		game = new Game2584();
	}

	@Override
	public void processGet(TemplateEngine templateEngine, IWebExchange webExchange, HttpServletResponse res)
			throws UnsupportedActionException, IOException {
		final WebContext ctx = new WebContext(webExchange);
		ctx.setVariable("game", game);
		templateEngine.process("2584", ctx, res.getWriter());
	}
	
	@Override
	public void processPost(TemplateEngine templateEngine, IWebExchange webExchange, HttpServletResponse res)
			throws UnsupportedActionException, IOException {
		var request = webExchange.getRequest();
		String key = request.getParameterValue("key");
		switch(key) {
			case "up": {game.up();}; break;
			case "down": {game.down();}; break;
			case "left": {game.left();}; break;
			case "right": {game.right();};
		}
		res.sendRedirect(request.getRequestPath());
	}
}
