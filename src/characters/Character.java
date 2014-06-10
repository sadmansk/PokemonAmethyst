package characters;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.image.BufferedImage;

import map.*;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import map.*;

public class Character extends Block
{
	protected String name;
	protected int facing;
	protected BufferedImage back, faceRight, faceLeft, image;
	protected static final int UP = 1, DOWN = 3, LEFT = 0, RIGHT = 2;
	protected Map localMap;
	protected Block prevBlock;

	//SPECIAL NOTE!!: the x and y coordinates start from 1, not 0 (hence 1 is subtracted when it is constructed in the map object)
	public Character (int x, int y, String name, Map map)
	{
		super (x, y);
		//prevBlock = map.map [y - 1][x - 1]; //stores the previous block object (where the character is now)
		passable = false;
		this.name = name;
		localMap = map;
		// map.map [y - 1][x - 1].setProp ('C'); //the character C on the map identifies a non-player character
		try

		{
			//all the different facings are stored in the image variables of the object
			image = ImageIO.read (new File (Map.curPath + "images\\characters\\" + name.toLowerCase() + ".jpg")); // load file into Image object
			back = ImageIO.read (new File (Map.curPath + "images\\characters\\" + name.toLowerCase() + "b.jpg")); // load file into Image object (back facing)
			faceRight = ImageIO.read (new File (Map.curPath + "images\\characters\\" + name.toLowerCase() + "r.jpg")); // load file into Image object (right facing)
			faceLeft = ImageIO.read (new File (Map.curPath + "images\\characters\\" + name.toLowerCase() + "l.png")); // load file into Image object (left facing)
		} catch (IOException e)
		{
			System.out.println ("Image could not be loaded" + Map.curPath + "images\\characters\\" + name.toLowerCase() + ".jpg");
		}
		facing = 3;
	}


	public void show(Graphics g)
	{
		BufferedImage in = new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB);
		try
		{
			switch (facing)
			{
			case 0: //left facing
				in = faceLeft;
				g.drawImage (ImageIO.read (new File (Map.curPath + "images\\_" + localMap.map[yPos -1][xPos].getProp() + "image.jpg")), (xPos) * Map.blockW, (yPos - 1) * Map.blockH, null); //assumes that the user came from the right, so repaints that block
				break;
			case 1: //facing top
				in = back;
				g.drawImage (ImageIO.read (new File (Map.curPath + "images\\_" + localMap.map[yPos][xPos - 1].getProp() + "image.jpg")), (xPos - 1) * Map.blockW, (yPos) * Map.blockH, null); //assumes that the user came from below, so repaints that block
				break;
			case 2: //right facing
				in = faceRight;
				g.drawImage (ImageIO.read (new File (Map.curPath + "images\\_" + localMap.map[yPos - 1][xPos - 2].getProp() + "image.jpg")), (xPos - 2) * Map.blockW, (yPos - 1) * Map.blockH, null); //assumes that the user came from the left, so repaints that block
				break;
			case 3: //front facing (forward/down)
				in = image;
				g.drawImage (ImageIO.read (new File (Map.curPath + "images\\_" + localMap.map[yPos - 2][xPos - 1].getProp() + "image.jpg")), (xPos - 1) * Map.blockW, (yPos - 2) * Map.blockH, null); //assumes that the user came from top, so repaints that block
				break;
			default:
				break;
			}
		}
		catch (IOException e)
		{
			System.out.println ("Tell em");
		}
		GraphicsConfiguration gc = in.createGraphics().getDeviceConfiguration();;
		BufferedImage out =	gc.createCompatibleImage(Map.blockW, Map.blockH, BufferedImage.BITMASK);
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setComposite(AlphaComposite.Src);
		g2d.drawImage(in, (xPos - 1) * Map.blockW, (yPos - 1) * Map.blockH, Map.blockW, Map.blockH, null);
		g2d.dispose();
		//ImageIO.write(out, "png", destFile);
	}
}

