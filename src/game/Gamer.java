package game;

public class Gamer {
	int ID;
	String username;
	int rocketID;
	int heart = 5;
	int bomb = 3;
	int coin = 0;
	int score = 0;
	
	public Gamer (int ID, String userName) {
		this.ID = ID;
		this.username = userName;
	}
	public int getID() {
		return this.ID;
	}
	public String getUsername() {
		return username;
	}
	
	public int getScore() {
		return score;
	}
	
	public int getHeart() {
		return heart;
	}
	

	
}
