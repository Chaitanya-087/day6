package com.learning.hello.model.tennis;

public class Serve {
	Player server;
	Player winner;
	
	public Serve(Player server,Player winner) {
		this.server = server;
		this.winner = winner;
	}
	
	public void setServer(Player player) {
		this.server = player;
	}
	
	public Player getWinner() {
		return winner;
	}
	
	public Player getServer() {
		return server;
	}
}
