package com.learning.hello.model.tennis;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Match {
	private Player player1;
	private Player player2;
	private Player winner;
	private List<Set> sets;
	private Date createdAt;
	
	public Match(Player p1, Player p2, Date createdAt) {
		this.player1 = p1;
		this.player2 = p2;
		this.createdAt = createdAt;
	}
	
	public Player getPlayer1() {
		return player1;
	}
	
	public Player getPlayer2() {
		return player2;
	}
	
	public String getCreatedAt() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM, yyyy HH:mm", Locale.ENGLISH);
		return dateFormat.format(createdAt);
	}
	
	public Player getWinner() {
		return winner;
	}
	
	public List<Set> getSets() {
		return this.sets;
	}
	
}
