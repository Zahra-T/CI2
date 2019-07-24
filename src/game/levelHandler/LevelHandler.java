package game.levelHandler;

public interface LevelHandler {
	
	public void nextLevel(int wave);
	public void nextWave(int wave);
	public void startLevel(int wave, int level);
	public void setLevel(int level);
	public void setWave(int wave);
	public int getLevel();
	public int getWave();
	public void start();
}
