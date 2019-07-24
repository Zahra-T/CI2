package messageHandler;

import java.util.ArrayList;

import com.google.gson.Gson;

import game.Gamer;
import game.Triple;

public class OrderMessage {
	Triple <ArrayList<Gamer>, Integer, Integer> gameInfo;
	OrderType type;
	public OrderMessage(OrderType type, Triple<ArrayList<Gamer>, Integer, Integer> gameInfo) {
		this.type = type;
		this.gameInfo = gameInfo;
	}
	
	public Triple<ArrayList<Gamer>, Integer, Integer> getGameInfo() {
		return gameInfo;
	}
	
	public OrderType getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return (new Gson()).toJson(this);
	}
	

}
