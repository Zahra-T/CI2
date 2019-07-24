package game.levelHandler;

import java.util.ArrayList;

import game.gameControl.Wave;
import game.gameControl.Wave1;
import game.gameControl.Wave2;
import game.gameControl.Wave3;
import game.gameControl.Wave4;
import programView.MainController;

public class SinglePlayerLevelHandler extends Thread implements LevelHandler{

	MainController mainController;
	int waveNum;
	ArrayList<Wave> waves;
	public SinglePlayerLevelHandler(MainController mainController, int waveNum) {
		this.mainController = mainController;
		this.waveNum = waveNum;
		initialize();
	}

	private void initialize() {
		waves.add(new Wave1());
		waves.add(new Wave2());
		waves.add(new Wave3());
		waves.add(new Wave4());
	}

	@Override
	public void run() {
		while(true)
		{
			if(mainController.running() && !(getWave() == 1 && getLevel() == 1) && !mainController.existEnemy()) {
				nextLevel(getLevel());
			}
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void nextLevel(int level) {
		if(level == 4) {
			nextWave(getWave());
		}
		else {
			mainController.setLevel(level++);
			startLevel(getWave(), getLevel());
		}
	}

	public void nextWave(int wave) {
		if(wave == waveNum) {
			mainController.finish();
		}
		else {
			mainController.showNextWaveMessage();
			setWave(wave++);
			setLevel(1);
			startLevel(getWave(), getLevel());
		}
	}

	public void startLevel(int wave, int level) {
		waves.get(wave-1).start(level);
	}

	public int getLevel() {
		return mainController.getLevel();
	}
	public int getWave() {
		return mainController.getWave();
	}
	
	public void setLevel(int level) {
		mainController.setLevel(level);
	}
	
	public void setWave(int wave) {
		mainController.setWave(wave);
	}




}
