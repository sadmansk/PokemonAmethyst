package characters;

import java.awt.Image;
import map.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import map.*;

public class Character extends Block
{
	protected String name;
	protected int facing;
	protected Image back, faceRight, faceLeft;
	protected static final int UP = 2, DOWN = 0, LEFT = 1, RIGHT = 3;
	protected Map localMap;
	protected Block prevBlock;
	
	//SPECIAL NOTE!!: the x and y coordinates start from 1, not 0 (hence 1 is subtracted when it is constructed in the map object)
	public Character (int x, int y, String name, Map map)
	{
		super (x, y);
		prevBlock = map.map [y - 1][x - 1]; //stores the previous block object (where the character is now)
		passable = false;
		this.name = name;
		localMap = map;
		map.map [y - 1][x - 1].setProp ('C'); //the character C on the map identifies a non-player character
		/*try
		
		{
			//all the different facings are stored in the image variables of the object
			image = ImageIO.read (new File ("images\\characters\\" + name.toLowerCase() + ".gif")); // load file into Image object
			back = ImageIO.read (new File ("images\\characters\\" + name.toLowerCase() + "b.gif")); // load file into Image object (back facing)
			faceRight = ImageIO.read (new File ("images\\characters\\" + name.toLowerCase() + "r.gif")); // load file into Image object (right facing)
			faceLeft = ImageIO.read (new File ("images\\characters\\" + name.toLowerCase() + "l.gif")); // load file into Image object (left facing)
		} catch (IOException e)
		{
			System.out.println ("Image could not be loaded");
		}*/  
	}
	
	
}
