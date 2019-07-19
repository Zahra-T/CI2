package singlePlayer;

import game.Location;

public class PlayerInfo {
	String username;
	int heart = 5;
	int bomb = 3;
	int coin = 0;
	int score = 0;
	int level = 1;
	Location location;
	boolean choosed;
//	rocketType rocketType;
	
	public PlayerInfo() {}
	public PlayerInfo(String username) {
		this.username = username;
	}
	
	public boolean isChoosed() {
		return choosed;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setChoosed(boolean b) {
		choosed = b;
	}
	
}
