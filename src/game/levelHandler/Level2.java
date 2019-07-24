package game.levelHandler;

import game.Game;

public class Level2 implements Level{
	Game game;

	public Level2 (Game game) {
		this.game = game;
	}
	@Override
	public void start(int wave) {
		switch(wave) {
		case 1 : wave1();
		case 2 : wave2();
		case 3 : wave3();
		case 4 : wave4();
		}
	}

	@Override
	public void wave1() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void wave2() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void wave3() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void wave4() {
		// TODO Auto-generated method stub
		
	}

}
