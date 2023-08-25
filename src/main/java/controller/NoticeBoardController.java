package controller;

import java.io.IOException;
import java.io.Writer;
import java.util.Date;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import model.Contact;
import model.NoticeBoard;
import model.Notice;

public class NoticeBoardController {
	private NoticeBoard model;
	public NoticeBoardController() {
		model = NoticeBoard.get();
		model.init();
	}
	
	public void processGet(IWebExchange webExchange,TemplateEngine templateEngine,HttpServletResponse res) throws ServletException, IOException {
		final WebContext ctx = new WebContext(webExchange);
		Writer out = res.getWriter();
		ctx.setVariable("model", model);
		templateEngine.process("noticeBoard", ctx, out);
	}
	
	public void processPost(IWebExchange webExchange,TemplateEngine templateEngine,HttpServletResponse res) throws ServletException, IOException {
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
