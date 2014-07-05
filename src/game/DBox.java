package game;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import map.Map;

public class DBox
{
	private Image frame;
	Font font = new Font("Verdana", Font.PLAIN, 20);
	
	public DBox ()
	{
		try
		{
			frame = ImageIO.read (new File (Map.curPath + "images\\dialogues\\dBoxWithGrass.jpg"));
		}
		catch (IOException e)
		{
			System.out.println ("File not found");
		}
		//check to see if the custom font is importable
				try
				{
					//create the font to use. Specify the size!
					font = Font.createFont(Font.TRUETYPE_FONT, new File(Map.curPath + "pokemon_fire_red.ttf")).deriveFont(40f);
					GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
					//register the font
					ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(Map.curPath + "pokemon_fire_red.ttf")));
				} catch (IOException e)
				{
					e.printStackTrace();
				}
				catch(FontFormatException e)
				{
					e.printStackTrace();
				}
	}
	
	public void showMsg (String message, Graphics g)
	{
		g.drawImage (frame, 0, 600, null);
		g.setFont (font);
		g.drawString (message, 50, 655);
	}
}
