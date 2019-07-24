package singlePlayer;

import java.util.HashMap;

public class MultiPlayerInfoes {
	int level = 1;
	int wave = 1;
	int time = 0;
	HashMap<String, MultiPlayerInfo> infoes = new HashMap<String, MultiPlayerInfo>();
	
	public void addPlayer(String username) {
		infoes.put(username, new MultiPlayerInfo(username, infoes.size()+1));
	}
	
	public void removePlayer(String username) {
		infoes.remove(username);
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getWave() {
		return wave;
	}
	
	public void setWave(int wave) {
		this.wave = wave;
	}
	
	
}

class MultiPlayerInfo{
	int ID;
	String username;
	int score = 0;
	int coin = 0;
	int bomb = 3;
	int heart = 5;
	
	public MultiPlayerInfo(String username, int ID) {
		this.username = username;
		this.ID = ID;
	}
	
}
