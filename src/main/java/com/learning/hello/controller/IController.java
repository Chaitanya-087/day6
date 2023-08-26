package com.learning.hello.controller;

import java.io.IOException;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.web.IWebExchange;

import com.learning.hello.controller.exception.UnsupportedActionException;

import jakarta.servlet.http.HttpServletResponse;

public interface IController {
	public void processGet(TemplateEngine templateEngine, IWebExchange WebExchange, HttpServletResponse res) throws UnsupportedActionException, IOException;
	public void processPost(TemplateEngine templateEngine, IWebExchange webExchange, HttpServletResponse res) throws UnsupportedActionException, IOException;
}
