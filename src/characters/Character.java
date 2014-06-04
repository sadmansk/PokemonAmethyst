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
	
	public Character (int x, int y, String name, Map map)
	{
		super (x, y);
		passable = false;
		this.name = name;
		localMap = map;
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
