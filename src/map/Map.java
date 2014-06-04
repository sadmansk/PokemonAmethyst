package map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import game.Main;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Map
{
	public Block [][] map;
	public static int blockW = 30, blockH = 30;
	public final static String curPath = Map.class.getClassLoader().getResource("").getPath().substring(1).replace("%20", " " ) + "map\\";
	//creates an empty map
	public Map (int rows, int columns)
	{
		map = new Block [rows][columns];

		for (int row = 0 ; row < rows ; row++)
			for (int col = 0 ; col <columns ; col++)
			{
				map [row] [col] = new Block (col + 1, row +1); // blank space
			}
	}

	public Map (int rows, int columns, String town)
	{
		map = new Block [rows][columns];
		File filePath = new File (curPath + "\\mapsTxt\\" + town.toLowerCase() + ".txt");
		int i = 0;
		try (Scanner scanner =  new Scanner(filePath))
		{
			while (scanner.hasNextLine())
			{
				map[i] = setProp (i, scanner.nextLine().toCharArray()); //stores each line/row by converting it into an char array
				i++;
			}
			scanner.close();
		}
		catch (FileNotFoundException e)
		{
			JOptionPane.showMessageDialog(new JFrame(),
					"No such file found!" + curPath + "\\mapsTxt\\" + town.toLowerCase() + ".txt",
					"Error",
					JOptionPane.ERROR_MESSAGE);;
		}
	}

	public String toString ()
	{
		String str = "";
		for (int row = 0 ; row < map.length ; row++)
		{
			for (int col = 0 ; col < map[0].length ; col++)
			{
				str += map[row][col] + " ";
			}
			str += "\n";
		}
		return str;
	}
	
	public int getWidth ()
	{
		return blockW * map[0].length; //returns the total width of the map area
	}

	public int getHeight ()
	{
		return blockH * map.length; //returns the total height of the map area
	}

	public Block getBlock (int x, int y)
	{
		return map[y][x];
	}

	public Block [] setProp (int row, char [] charArray)
	{
		Block [] newProp = new Block [charArray.length];
		for (int i = 0; i < newProp.length; i++)
		{
			newProp[i] = new Block (i + 1, row, charArray[i]);
		}
		return newProp;
	}

	public void show (Graphics g)    // displays the map on the screen
	{
		for (int row = 0 ; row < map.length; row++)
			for (int col = 0 ; col < map[0].length; col++)
			{					//simple rectangles
				if (map [row] [col].getProp() == 'L') // ledge
					g.setColor (Color.black);
				else if (map [row] [col].getProp() == 'T') // tree
					g.setColor (Color.gray);
				else if (map [row] [col].getProp() == ' ') // space will erase what was there
					g.setColor (Color.white);
				else if (map[row][col].getProp() == 'G') //grass
					g.setColor (Color.green);
				else if (map[row][col].getProp() == 'H') //house/structure
					g.setColor (Color.blue);
				else if (map[row][col].getProp() == 'C') //character
						g.setColor (Color.pink);
				else if (map[row][col].getProp() == 'P') //player
					g.setColor (Color.yellow);
				g.fillRect (col * blockW, row * blockH, blockW, blockH); // draw block
			}
	}
}