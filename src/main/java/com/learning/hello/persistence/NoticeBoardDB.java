package com.learning.hello.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.learning.hello.model.noticeBoard.Contact;
import com.learning.hello.model.noticeBoard.Notice;

public class NoticeBoardDB {
	private Connection cnx;
	private static final String URI = "jdbc:mysql://localhost:3306/mydb";
	private static final String USERNAME = "chaitanya";
	private static final String PASSWORD = "chaitanya";

	public NoticeBoardDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cnx = DriverManager.getConnection(URI, USERNAME, PASSWORD);
			System.out.println("db connection successful :)");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Notice> getNotices(int limit) {
		List<Notice> notices = new ArrayList<>();
		try {
			String query = "SELECT * FROM notices ORDER BY created_at DESC LIMIT ?";
			PreparedStatement pstmt = cnx.prepareStatement(query);
			pstmt.setInt(1, limit);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String title = rs.getString("title");
				String content = rs.getString("content");
				Timestamp timeStamp = rs.getTimestamp("created_at");
				Date createdAt = new Date(timeStamp.getTime());
				Contact contact = getContactById(rs.getInt("contact_id"));
				Notice notice = new Notice(title,content,createdAt,contact);
				notices.add(notice);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return notices;
	}

	private Contact getContactById(int id) {
		Contact contact = null;
		try {
			String query = "SELECT * FROM contacts WHERE id = ?";
			PreparedStatement pstmt = cnx.prepareStatement(query);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				contact = new Contact(rs.getString("name"), rs.getString("number"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contact;
	}

	private void insertContact(Contact contact) {
		try {
			String query = "INSERT INTO contacts(name, number) VALUES(?, ?)";
			PreparedStatement pstmt = cnx.prepareStatement(query);
			pstmt.setString(1, contact.getName());
			pstmt.setString(2, contact.getNumber());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private int getContactId(Contact contact) {
		int id = -1;
		try {
			String query = "SELECT id FROM contacts WHERE name = ? and number = ?";
			PreparedStatement pstmt = cnx.prepareStatement(query);
			pstmt.setString(1, contact.getName());
			pstmt.setString(2, contact.getNumber());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	public void insertNotice(Notice notice) {
		try {
			if (getContactId(notice.getContact()) == -1) {
				insertContact(notice.getContact());
			}
			String query = "INSERT INTO notices(title, content, contact_id) VALUES(?, ?, ?)";
			PreparedStatement pstmt = cnx.prepareStatement(query);
			pstmt.setString(1, notice.getTitle());
			pstmt.setString(2, notice.getContent());
			pstmt.setInt(3, getContactId(notice.getContact()));
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
