package game;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import messageHandler.ObjectType;

public class GamePictures {
	String address = "C:\\Users\\Markazi.co\\workspace\\Chicken Invaders\\src\\game\\resources\\";
	private static GamePictures gamePictures;
	HashMap <ObjectType, Image> pictures;

	public static GamePictures getInstance()
	{
		if(gamePictures == null) {
			try {
				gamePictures = new GamePictures();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return gamePictures;
	}

	public GamePictures() throws IOException
	{
		initialize();
	}

	private void initialize() throws IOException
	{
		pictures = new HashMap();

		pictures.put(ObjectType.background, loadJPGImage("background"));

		pictures.put(ObjectType.startPanel, loadJPGImage("startPanel"));

		pictures.put(ObjectType.EGG, loadPNGImage("egg"));

		pictures.put(ObjectType.COIN, loadPNGImage("coin"));

		pictures.put(ObjectType.redBullet, loadPNGImage("redBullet"));

		pictures.put(ObjectType.yellowBullet, loadPNGImage("yellowBullet"));

		pictures.put(ObjectType.grayBullet, loadPNGImage("grayBullet"));

		pictures.put(ObjectType.redEmpowerer, loadPNGImage("redGift"));

		pictures.put(ObjectType.yellowEmpowerer, loadPNGImage("yellowGift"));

		pictures.put(ObjectType.grayEmpowerer, loadPNGImage("grayGift"));

		pictures.put(ObjectType.typeEmpowerer, loadPNGImage("empowerer"));

		pictures.put(ObjectType.CHICKEN1, loadPNGImage("chicken1"));

		pictures.put(ObjectType.CHICKEN2, loadPNGImage("chicken2"));

		pictures.put(ObjectType.CHICKEN3, loadPNGImage("chicken3"));

		pictures.put(ObjectType.CHICKEN4, loadPNGImage("chicken4"));

		pictures.put(ObjectType.GIANT, loadPNGImage("giant1"));

		pictures.put(ObjectType.ROCKET, loadPNGImage("rocket"));

		pictures.put(ObjectType.BOMB, loadPNGImage("BombItem"));

		pictures.put(ObjectType.LeftDown, loadPNGImage("leftDown"));

		pictures.put(ObjectType.LeftUp, loadPNGImage("leftUp"));

		pictures.put(ObjectType.userPanel, loadPNGImage("userPanel"));

		pictures.put(ObjectType.menuePanel, loadJPGImage("menuPanel"));

		pictures.put(ObjectType.serverUserPanel, loadPNGImage("ServerUserPanel"));


	}

	public Image get(ObjectType type)
	{
		synchronized(pictures) {
			return pictures.get(type);
		}
	}
	
	public Image loadJPGImage(String name) throws IOException
	{
		Image image = ImageIO.read(new File(address+name+".jpg"));
		return image;
	}
	
	public Image loadPNGImage(String name) throws IOException
	{
		Image image = ImageIO.read(new File(address+name+".png"));
		return image;
	}


}

