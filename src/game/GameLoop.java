package game;

public class GameLoop extends Thread{
	Game game;
	
	public GameLoop(Game game) {
		this.game = game;
	}
	
	@Override
	public void run() {
		
		while(true) {
			if(game.running()) {
				game.move();
				game.remove();
			}
			
			try {
				sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}

}
