package com.learning.hello.model.tennis;

import java.util.ArrayList;
import java.util.List;

public class Game {
	private List<Serve> serves;
	private int player1Score;
	private int player2Score;
	
	public Game() {
		serves = new ArrayList<>();
		player1Score = 0;
		player2Score = 0;
	}
	
	public int player1Score() {
		return player1Score;
	}
	
	public int player2Score() {
		return player2Score;
	}
	
	public void setPlayer1Score(int score) {
		this.player1Score = score;
	}
	
	public void setPlayer2Score(int score) {
		this.player2Score = score;
	}
	
	public List<Serve> getServes() {
		return this.serves;
	}
	
}
