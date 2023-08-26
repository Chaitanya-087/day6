package com.learning.hello.model.noticeBoard;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;



public class Notice {
	private String title;
	private String content;
	private Date createdAt;
	private Contact contact;
	
	public Notice(String title, String content, Date createdAt, Contact contact) {
		this.title = title;
		this.content = content;
		this.createdAt = createdAt;
		this.contact = contact;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public String getCreatedAt() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM, yyyy HH:mm", Locale.ENGLISH);
		return dateFormat.format(createdAt);
	}
	
	public Contact getContact() {
		return contact;
	}
	
	public String toString() {
		return String.format("%s %s %s %s", title, content, getCreatedAt(), contact.getName());
	}
//	public static void main(String[] args) {
//		Notice n1 = new Notice("holiday", "wkuegkwbkjwbc", new Date(), new Contact("chaitanya", "9963335976"));
//		System.out.println(n1.getCreatedAt());
//	}
}
