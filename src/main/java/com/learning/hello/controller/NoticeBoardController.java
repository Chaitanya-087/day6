package com.learning.hello.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.Date;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;

import com.learning.hello.controller.exception.UnsupportedActionException;
import com.learning.hello.model.noticeBoard.Contact;
import com.learning.hello.model.noticeBoard.Notice;
import com.learning.hello.model.noticeBoard.NoticeBoard;

import jakarta.servlet.http.HttpServletResponse;

public class NoticeBoardController implements IController{
	private NoticeBoard model;
	public NoticeBoardController() {
		model = NoticeBoard.get();
		model.init();
	}
	
	public void processGet(TemplateEngine templateEngine,IWebExchange webExchange, HttpServletResponse res) throws UnsupportedActionException, IOException {
		final WebContext ctx = new WebContext(webExchange);
		Writer out = res.getWriter();
		ctx.setVariable("model", model);
		templateEngine.process("noticeBoard", ctx, out);
	}
	
	public void processPost(TemplateEngine templateEngine,IWebExchange webExchange, HttpServletResponse res) throws UnsupportedActionException, IOException {
		var request = webExchange.getRequest();
		String title = request.getParameterValue("title");
		String content = request.getParameterValue("content");
		String name = request.getParameterValue("name");
		String number = request.getParameterValue("number");
		
		Contact contact = new Contact(name,number);
		Notice notice = new Notice(title, content,new Date(),contact);
		
		model.addNotice(notice);
		
		res.sendRedirect(request.getRequestPath());
		
	}
	
}
