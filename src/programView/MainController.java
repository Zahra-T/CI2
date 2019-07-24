package programView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import Logger.Logger;
import client.Client;
import game.Game;
import game.Gamer;
import game.Triple;
import game.gameControl.MultiPlayerGame;
import game.gameControl.SinglePlayerGame;
import game.levelHandler.LevelHandler;
import game.levelHandler.MultiPlayerLevelHandler;
import messageHandler.ClientMessage;
import server.Server;
import singlePlayer.MultiPlayerInfoes;
import singlePlayer.PlayerInfo;
import sounds.SoundPlayer;
import singlePlayer.PlayerInfo;
public class MainController {

	TreeMap <String, PlayerInfo> infoes = new TreeMap<String, PlayerInfo>();
//	MultiPlayerGame multiPlayerGame;
	SinglePlayerGame singlePlayerGame;
	
	Server server;
	Client client;
	PanelHolder panelHolder = new PanelHolder(this);
	Game game;
	SoundPlayer soundPlayer;
	String username;
	
	String address = "C:\\Users\\Markazi.co\\workspace\\Chicken Invaders\\src\\game\\resources\\";
	String saveAddress = "C:\\Users\\Markazi.co\\workspace\\CI2\\";
	

	Logger logger = Logger.getLogger();

	public MainController() {
		
		loadInfo();
		setLabels();
		setChoosedPlayer();
	}

	public boolean containsPlayer(String username) {
		return infoes.containsKey(username);
	}
	
	public void createServer(int port, int maxPlayer, int levelNum) {
		game = new Game(new MultiPlayerLevelHandler(game, this, levelNum)); // !!!!
		server = new Server(port, game, maxPlayer);
		panelHolder.toServerControlPanel(port, maxPlayer, levelNum);
	}
	
	public void startServer() {
		server.start();
	}
	
	public void createClient(String IP, int port) {
		client = new Client(IP, port, this);
	}
	
	public void startClient() {
		client.start();
		panelHolder.toClientControlPanel();
	}
	
	public void showInfo(Triple <ArrayList<Gamer>, Integer, Integer> gameInfo) {
		panelHolder.showInfo(gameInfo);
	}
	
	public void addClientMessage(ClientMessage message) {
		client.addClientMessage(message);
	}


	public boolean running() {
		return game.running();
	}

	public void toMenu() {
		panelHolder.toMenu();
	}

	public void toStartPanel() {
		panelHolder.toStartPanel();
	}

	public void toSetting() {
		panelHolder.toSetting();

	}
	
	public void toSelectionPanel() {
		panelHolder.toSelectionPanel();
	}
	
	public void toServerOrClientPanel() {
		panelHolder.toServerOrClientPanel();
	}
	
	
	public void workAsServer() {
		panelHolder.toServerSettingPanel();
	}
	
	public void workAsClient() {
		panelHolder.toClientSettingPanel();
	}
	
	public void singlePlayer() {
		
	}
	
	
	
	
	public void toUsernamePanel() {
		usernamePanel = new UsernamePanel(this, menu);
		
		
	}

	public void toRankingPanel() {
		if(singlePlayer) {

		}
		else {

		}
	}

	public void resume() {

		levelHandler = new LevelHandler();
		gamePanel = new GamePanel();

	}


	public void setLabels() {
		panelHolder.setLabels();
	}



	public void newGame() {
		infoes.put(username, new PlayerInfo());
		startGame();
	}

	public void quit() {
		saveInfo();
		System.exit(0);
	}

	public void setChoosedPlayer() {
		for(PlayerInfo info : infoes.values()) {
			if(info.isChoosed()) {
				username = info.getUsername();
				panelHolder.choosePlayer(username);
				return;
			}
		}
	}

	public void choosePlayer(String username) {
		this.username = username;
		for(PlayerInfo info : infoes.values()) {
			if(info.getUsername().equals(username)) {
				info.setChoosed(true);
			}
			else
			{
				info.setChoosed(false);
			}
		}
		panelHolder.choosePlayer(username);

		panelHolder.getStartPanel().repaint();
		panelHolder.getStartPanel().revalidate();

	}

	public TreeMap<String, UserLabel> getLabels(){

		TreeMap <String, UserLabel> labels = new TreeMap<String, UserLabel>();
		for(PlayerInfo info : infoes.values()) {
			labels.put(info.getUsername(), new UserLabel(info.getUsername(), true, this));
		}
		return labels;
	}

	public PlayerInfo getInfo(String username) {
		return infoes.get(username);
	}

	public void removePlayer(String userName) {
		infoes.remove(userName);
	}

	public boolean addPlayer(String username) {

		if(infoes.containsKey(username)) {
			return false;

		}
		infoes.put(username,  new PlayerInfo(username));
		return true;
	}

	

	public void saveInfo()
	{
		File file = new File(saveAddress + "\\" + "game.data");

		try {
			PrintWriter printWriter = new PrintWriter(file);
			Gson gson = new Gson();
			String str = gson.toJson(infoes, new TypeToken<TreeMap<String, PlayerInfo>>(){}.getType());
			System.out.println(str);
			printWriter.println(str);
			printWriter.close();

			System.out.println("saved");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void loadInfo()
	{

		Scanner sc;
		try {
			sc = new Scanner(new File(saveAddress+"\\game.data"));
			Gson gson= new Gson();
			String str = sc.nextLine();
			infoes = gson.fromJson(str, new TypeToken<TreeMap<String, PlayerInfo>>(){}.getType());
			System.out.println("loaded");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}


	public void playSound(String name)
	{
		File sound = new File(address+name+".wav");
		soundPlayer = new SoundPlayer(sound);
	}

	public void stopSound()
	{
		soundPlayer.stop();
	}

	public boolean existEnemy() {
		return game.existEnemy();
	}

	public void showNextWaveMessage() {
		(new NextWaveMessage().start());
	}

	public void finish() {
		(new FinishMessage().start());
		toRankingPanel();
	}


	public String getUsername() {
		return username;
	}

	public int getLevel() {
		if(singlePlayer) {
			return infoes.get(username).getLevel();
		}
		else {
			return multiPlayerInfoes.getLevel();
		}
	}

	public int getWave() {
		if(singlePlayer) {
			return infoes.get(username).getWave();
		}
		else {
			return multiPlayerInfoes.getWave();
		}
	}

	public void setLevel(int level) {
		if(singlePlayer) {
			infoes.get(username).setLevel(level);
		}
		else {
			multiPlayerInfoes.setLevel(level);
		}
	}

	public void setWave(int wave) {
		if(singlePlayer) {
			infoes.get(username).setWave(wave);
		}
		else {
			multiPlayerInfoes.setWave(wave);
		}
	}


	public static void main(String [] args) {
		MainController mainController = new MainController();
	}
}
