package game;

public class Gamer {
	int ID;
	String userName;
	int rocketID;
	int heart = 5;
	int bomb = 3;
	int coin = 0;
	int score = 0;
	int level = 1;
	
	public Gamer (int ID, String userName) {
		this.ID = ID;
		this.userName = userName;
	}
	public int getID() {
		return this.ID;
	}
	
	

	
}
