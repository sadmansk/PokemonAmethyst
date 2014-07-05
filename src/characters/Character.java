package characters;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.image.BufferedImage;
import pokemons.Pokemon;
import map.*;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import map.*;

public class Character extends Block
{
	protected String name;
	protected int facing;
	protected BufferedImage back, faceRight, faceLeft, image, in;
	protected static final int UP = 1, DOWN = 3, LEFT = 0, RIGHT = 2;
	protected Map localMap;
	protected Block prevBlock;
	public Pokemon pokemon;

	//SPECIAL NOTE!!: the x and y coordinates start from 1, not 0 (hence 1 is subtracted when it is constructed in the map object)
	public Character (int x, int y, String name, Map map)
	{
		super (x, y);
		passable = false;
		this.name = name;
		localMap = map;
		facing = 3;
		pokemon = null;
	}
	
	public void show(Graphics g)
	{
		in = new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB);
		prevBlock = localMap.map [yPos - 1][xPos - 1]; //stores the previous block object (where the character is now)
		try
		{
			switch (facing)
			{
			case 0: //left facing
				in =ImageIO.read (new File (Map.curPath + "images\\characters\\" + name.toLowerCase() + prevBlock.getProp() + "l.jpg"));
				g.drawImage (ImageIO.read (new File (Map.curPath + "images\\_" + localMap.map[yPos -1][xPos].getProp() + "image.jpg")), (xPos) * Map.blockW, (yPos - 1) * Map.blockH, null); //assumes that the user came from the right, so repaints that block
				break;
			case 1: //facing top
				in = ImageIO.read (new File (Map.curPath + "images\\characters\\" + name.toLowerCase() + prevBlock.getProp() + "b.jpg"));
				g.drawImage (ImageIO.read (new File (Map.curPath + "images\\_" + localMap.map[yPos][xPos - 1].getProp() + "image.jpg")), (xPos - 1) * Map.blockW, (yPos) * Map.blockH, null); //assumes that the user came from below, so repaints that block
				break;
			case 2: //right facing
				in = ImageIO.read (new File (Map.curPath + "images\\characters\\" + name.toLowerCase() + prevBlock.getProp() + "r.jpg"));
				g.drawImage (ImageIO.read (new File (Map.curPath + "images\\_" + localMap.map[yPos - 1][xPos - 2].getProp() + "image.jpg")), (xPos - 2) * Map.blockW, (yPos - 1) * Map.blockH, null); //assumes that the user came from the left, so repaints that block
				break;
			case 3: //front facing (forward/down)
				in = ImageIO.read (new File (Map.curPath + "images\\characters\\" + name.toLowerCase() + prevBlock.getProp() + ".jpg"));
				g.drawImage (ImageIO.read (new File (Map.curPath + "images\\_" + localMap.map[yPos - 2][xPos - 1].getProp() + "image.jpg")), (xPos - 1) * Map.blockW, (yPos - 2) * Map.blockH, null); //assumes that the user came from top, so repaints that block
				break;
			default:
				break;
			}
		}
		catch (IOException e)
		{
			//System.out.println ("Tell em");
		}
		GraphicsConfiguration gc = in.createGraphics().getDeviceConfiguration();
		BufferedImage out =	gc.createCompatibleImage(Map.blockW, Map.blockH, BufferedImage.BITMASK);
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setComposite(AlphaComposite.Src);
		g2d.drawImage(in, (xPos - 1) * Map.blockW, (yPos - 1) * Map.blockH, Map.blockW, Map.blockH, null);
		g2d.dispose();
		//ImageIO.write(out, "png", destFile);
	}
	
	public void addPokemon (String name, int level)
	{
		pokemon = new Pokemon (name, level);
	}
	
//	public void paintComponent(Graphics g){
//		GraphicsConfiguration gc = in.createGraphics().getDeviceConfiguration();
//		BufferedImage out =	gc.createCompatibleImage(Map.blockW, Map.blockH, BufferedImage.BITMASK);
//		Graphics2D g2d = (Graphics2D) g.create();
//		g2d.setComposite(AlphaComposite.Src);
//		g2d.drawImage(in, (xPos - 1) * Map.blockW, (yPos - 1) * Map.blockH, Map.blockW, Map.blockH, null);
//		g2d.dispose();
//	}
}

