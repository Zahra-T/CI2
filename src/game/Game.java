package game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

import Logger.Logger;
import game.asset.Asset;
import game.asset.Bomb;
import game.enemy.Enemy;
import game.levelHandler.LevelHandler;

public class Game {
	ArrayList<Gamer> gamers = new ArrayList<Gamer>();
	ArrayList<Rocket> rockets = new ArrayList<Rocket>();
	ArrayList<Asset> assets = new ArrayList<Asset>();
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	LevelHandler levelHandler; // single player or multiplayer level handler
	
	Logger logger = Logger.getLogger();
	boolean running = false;
	
	public Game(LevelHandler levelHandler) {
		this.levelHandler = levelHandler;
		addGamer(new Gamer(2,"zahra"));
	}
	
	public void throwBomb(int gamerID, Location firstPlace) {
		synchronized(assets) {
	     	assets.add(new Bomb(assets.size(), gamerID, firstPlace));
		}
	}
	
	public void move() {
		synchronized(assets) {
			for(Asset asset: assets) {
				asset.move();
			}
		}
	}
	
	public void remove() {
		synchronized(assets) {
			Iterator<Asset> itr = assets.iterator();
			
			while(itr.hasNext()) {
				Asset asset = itr.next();
				if(asset.remove()) {
					itr.remove();
				}
			}
		}
	}
	
	public boolean existEnemy() {
		return (enemies.size() > 0);
	}
	
	public void start() {
		levelHandler.start();
		running = true;
	}
	
	public void pause() {
		running = false;
	}
	
	public boolean running() {
		return running;
	}
	
	public void setRunning(boolean running) {
		this.running = running;
	}
	
	public void setMousePressed(int gamerID, boolean mousePressed) {
		getRocket(gamerID).setMousePressed(mousePressed);
	}
	
	public void setLocation(int gamerID, Location location) {
		getRocket(gamerID).setLocation(location);
		
	}
	
	public Gamer getGamer(int gamerID) {
		synchronized(gamers) {
			for(Gamer gamer: gamers) {
				if(gamer.getID() == gamerID) {
					return gamer;
				}
			}
		}
		return null;
	}
	
	public Rocket getRocket(int gamerID) {   //return rocket using gamerID;
		synchronized(rockets) {
			for(Rocket rocket : rockets) {
				if(rocket.getGamerID() == gamerID) {
					return rocket;
				}
			}
		}
		return null;
	}
	

	
	public int getRocketsSize() {
		return rockets.size();
	}
	
	public void addGamer(Gamer gamer) {
		synchronized(gamers) {
			gamers.add(gamer);
		}
	}
	
	public Triple getInfo() {
		Triple<ArrayList<Gamer>, Integer, Integer> info = new Triple<ArrayList<Gamer>, Integer, Integer>(gamers, getLevel(), getWave());
		return info;
	}
	
	public void addRocket(Rocket rocket) {
		rockets.add(rocket);
	}
	
	public ArrayList<Rocket> getRockets(){
		return rockets;
	}

	public ArrayList<Asset> getAssets(){
		return assets;
	}
	
	public int getLevel() {
		return levelHandler.getLevel();
	}
	
	public int getWave() {
		return levelHandler.getWave();
	}
	
	public void setLevel(int level) { 
		levelHandler.setLevel(level);
	}
	
	public void setWave(int wave) {
		levelHandler.setWave(wave);
	}
	

	
	
	
	
	

	

}
