package model;

//import java.util.Date;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import persistence.NoticeBoardDB;

public class NoticeBoard {
	private List<Notice> notices;
	private static final int SIZE = 6;
	private NoticeBoardDB db;
	private static NoticeBoard inst;

	public static NoticeBoard get() {
		if (inst == null) inst = new NoticeBoard();
		return inst;
	}
	public void init() {
		db = new NoticeBoardDB();		
	}
	public List<Notice> getNotices() {
		notices = db.getNotices(SIZE);
		return Collections.unmodifiableList(notices);
	}
	
	public void addNotice(Notice notice) {
		db.insertNotice(notice);
	}
	
	public static void main(String[] args) {
		NoticeBoard nb = NoticeBoard.get();
		nb.init();
		Notice n1 = new Notice("holiday", "wkuegkwbkjwbc", new Date(), new Contact("chaitanya", "9963335976"));
//		nb.addNotice(n1);
		System.out.println(nb.getNotices());
	}
}
