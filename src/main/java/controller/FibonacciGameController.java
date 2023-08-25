package controller;

import java.io.IOException;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import model.Game2584;

public class FibonacciGameController {
	private Game2584 game;

	public FibonacciGameController() {
		game = new Game2584();
	}

	public void processGet(IWebExchange webExchange, TemplateEngine templateEngine, HttpServletResponse res)
			throws ServletException, IOException {
		final WebContext ctx = new WebContext(webExchange);
		var out = res.getWriter();
		ctx.setVariable("game", game);
		templateEngine.process("2584", ctx, out);
	}

	public void processPost(IWebExchange webExchange, TemplateEngine templateEngine, HttpServletResponse res)
			throws ServletException, IOException {
		var request = webExchange.getRequest();
		String key = request.getParameterValue("key");
		switch(key) {
			case "up": {game.up();}; break;
			case "down": {game.down();}; break;
			case "left": {game.left();}; break;
			case "right": {game.right();}; break;
		}
		res.sendRedirect(request.getRequestPath());
	}
}
