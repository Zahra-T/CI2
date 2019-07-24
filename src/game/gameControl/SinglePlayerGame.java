package game.gameControl;

import java.util.ArrayList;

import game.Game;
import game.levelHandler.SinglePlayerLevelHandler;
import game.levelHandler.Wave;
import programView.MainController;

public class SinglePlayerGame {
	MainController mainController;
	SinglePlayerLevelHandler levelHandler = new SinglePlayerLevelHandler(mainController, 4);
	Game game = new Game();
	
	public SinglePlayerGame(MainController mainController) {
		this.mainController = mainController;
	}
	
	public void start() {
		game.start();
		levelHandler.start();
	}



}










