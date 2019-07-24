package game.levelHandler;

import java.util.ArrayList;

import game.Game;
import programView.MainController;

public class MultiPlayerLevelHandler extends Thread implements LevelHandler{
	Game game;
	int levelNum;
	MainController mainController;
	ArrayList<Level> levels = new ArrayList<Level>();
	
	int level = 1, wave = 1;
	
	public MultiPlayerLevelHandler(Game game, MainController mainController, int levelNum) {
		this.game = game;
		this.levelNum = levelNum;
		this.mainController = mainController;
		initialize();
	}

	private void initialize() {
		levels.add(new Level4(game));
		levels.add(new Level2(game));
		levels.add(new Level3(game));
		levels.add(new Level4(game));
	}

	@Override
	public void run() {
		while(true)
		{
			if(game.running() && !(getWave() == 1 && getLevel() == 1) && !game.existEnemy()) {
				nextWave(getWave());
			}
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void nextWave(int wave) {
		if(wave == 4) {
			nextLevel(level);
		}
		else {
			setWave(wave++);
			startWave(level, wave);
		}
	}

	public void nextLevel(int level) {
		if(level == levelNum) {
			mainController.finish();
		}
		else {
			mainController.showNextWaveMessage();
			setLevel(level++);
			setWave(1);
			startWave(game.getLevel(), game.getWave());
		}
	}

	public void startLevel(int wave, int level) {
		levels.get(wave-1).start(level);
	}
	
	public void setWave(int wave) {
		this.wave = wave;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getLevel() {
		return level;
	}
	
	public int getWave() {
		return wave;
	}







}