package map;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Structure
{
	private Image image;
	private int xPos, yPos;
	private String ID;
	
	public Structure (int x, int y, String name)
	{
		//sets up the position of the structure on the map. Note: x and y comes in as user friendly numbers, hence the numbering starts from 1 instead of the actual 0 value
		xPos = x;
		yPos = y;
		ID = name;
		image = null;
		//tries loading the image from the file directory
		try
		{
			image = ImageIO.read (new File (Map.curPath + "images\\structures\\" + ID.toLowerCase() + ".jpg"));
		}
		catch (IOException e) //shows an error in case the file is not found
		{
			System.out.println ("File not found!" + Map.curPath + "images\\structures\\" + ID.toLowerCase() + ".jpg");
		}
		/**
		 * Important: These are the required dimensions for each of the pictures:
		 * house.jpg: 120 x 120
		 * lab.jpg: 210 x 120
		 * poke.jpg: 120 x 120
		 * (All dimensions are in pixels)
		 */
	}
	
	public void show (Graphics g)
	{
		/*Note: For displaying the picture in the right place,
		the "user-friendly" xPos and yPos is converted back to
		the program's language and the pixels are multiplied by
		the static block dimensions of the map */
		g.drawImage (image, (xPos - 1) * Map.blockW, (yPos - 1) * Map.blockH, null); //the image is shown using the graphics component
	}
	//adds a door to the structure
	public void setDoor (int x, int y, char doorType, String end)
	{
		Door door = new Door ((xPos + x)*Map.blockW, (yPos + y)*Map.blockH, doorType, end);
		
	}
	
}
