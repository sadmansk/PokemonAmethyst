package characters;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import map.*;

public class Character extends Block
{
	protected String name;
	protected int facing;
	protected Image back, faceRight, faceLeft;
	
	public Character (int x, int y, String name)
	{
		super (x, y);
		passable = false;
		this.name = name;
		try
		{
			//all the different facings are stored in the image variables of the object
			image = ImageIO.read (new File ("images\\characters\\" + name.toLowerCase() + ".gif")); // load file into Image object
			back = ImageIO.read (new File ("images\\characters\\" + name.toLowerCase() + "b.gif")); // load file into Image object (back facing)
			faceRight = ImageIO.read (new File ("images\\characters\\" + name.toLowerCase() + "r.gif")); // load file into Image object (right facing)
			faceLeft = ImageIO.read (new File ("images\\characters\\" + name.toLowerCase() + "l.gif")); // load file into Image object (left facing)
		} catch (IOException e)
		{
			System.out.println ("Image could not be loaded");
		} 
	}
}
