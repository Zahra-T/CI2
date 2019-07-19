package programControl;
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
import messageHandler.ClientMessage;
import singlePlayer.PlayerInfo;
import sounds.SoundPlayer;
import singlePlayer.PlayerInfo;
public class MainController {

	TreeMap <String, PlayerInfo> infoes = new TreeMap<String, PlayerInfo>();
	SoundPlayer soundPlayer;
	String username;
	String address = "C:\\Users\\Markazi.co\\workspace\\Chicken Invaders\\src\\game\\resources\\";
	String saveAddress = "C:\\Users\\Markazi.co\\workspace\\Chicken Invaders\\src\\game";
	MainFrame mainFrame;
	StartPanel startPanel;
	Menu menu;
	Logger logger = Logger.getLogger();
	public MainController() {
		mainFrame = new MainFrame();
		startPanel = new StartPanel(this);
		mainFrame.add(startPanel);
		mainFrame.repaint();
		mainFrame.revalidate();
		loadInfo();
		setChoosedPlayer();
	}

	public boolean containsPlayer(String username) {
		return infoes.containsKey(username);
	}

	public void revalidate() {
		mainFrame.revalidate();
	}

	public void toMenu() {
		stopSound();
		mainFrame.remove(startPanel);
		if(menu == null) menu = new Menu(mainController);
		mainFrame.add(menu);
		mainFrame.validate();
		mainFrame.repaint();

		playSound("The Fathiers");
	}

	public void setChoosedPlayer() {
		for(PlayerInfo info : infoes.values()) {
			if(info.isChoosed()) {
				username = info.getUsername();
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
		startPanel.choosePlayer(username);

		startPanel.repaint();
		startPanel.revalidate();

		mainFrame.repaint();
		mainFrame.revalidate();
	}

	public TreeMap<String, UserLabel> getLabels(){
		TreeMap <String, UserLabel> labels = new TreeMap<String, UserLabel>();
		for(PlayerInfo info : infoes.values()) {
			labels.put(info.getUsername(), new UserLabel(info.getUsername(), this));
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

		File file = new File(address + "\\" + "game.data");

		try {
			PrintWriter printWriter = new PrintWriter(file);
			Gson gson = new Gson();
			String str = gson.toJson(infoes, new TypeToken<TreeMap<String, PlayerInfo>>(){}.getType());
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

	public String getUsername() {
		return username;
	}


	public static void main(String [] args) {
		MainController mainController = new MainController();
	}


}
