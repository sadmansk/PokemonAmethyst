package map;

import java.awt.Color;

import javax.imageio.ImageIO;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import game.Main;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import characters.Player;

public class Map
{
	public Block [][] map;
	public static int blockW = 30, blockH = 30;
	public final static String curPath = Map.class.getClassLoader().getResource("").getPath().substring(1).replace("%20", " " ) + "map\\";
	public Door[] doors;
	public String mapName;

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
		mapName = town.toUpperCase();
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
		addDoors();		
	}


	/**
	 * This puts doors in the right places which lead to the right places
	 */	
	private void addDoors ()
	{
		int count = 0;
		for (int row = 0 ; row < map.length ; row++)
			for (int col = 0 ; col < map[0].length ; col++)
			{
				switch (map [row] [col].getProp())
				{
				case 'm': //maple from home
				case 'q': //maple from center
				case 'p': //maple from lab
				case 'r':
				case 'h': //to home
				case 'c': //to pokecenter
				case 'o': //to oak's lab
				case 's': //maple to winterfell
				case 'S': //winterfell to maple
					count++;
					break;

				default:
					break;
				}
			}
		//sets up the doors
		doors = new Door[count];
		count = 0;
		for (int row = 0 ; row < map.length ; row++)
			for (int col = 0 ; col < map[0].length ; col++)
			{
				switch (map [row] [col].getProp())
				{
				case 'm': //maple from home
					int[] eC = {25, 9};
					doors[count] = new Door (col + 1, row + 1, 'm', "maple", eC);
					//System.out.println (doors[count].ending);
					count++;
					break;
				case 'h': //to home from maple
					int[] eC1 = {14, 15};
					doors[count] = new Door (col + 1, row + 1, 'h', "home", eC1);
					count++;
					break;
				case 'c': //to pokecenter
					int[] eC2 = {17, 15};
					doors[count] = new Door (col + 1, row + 1, 'c', "poke", eC2);
					count++;
					break;
				case 'o': //to oak's lab
					int[] eC3 = {16, 15};
					doors[count] = new Door (col + 1, row + 1, 'o', "lab", eC3);
					count ++;
					break;
				case 'p': //from oak's lab to maple
				case 'r':
					int[] eC4 = {15, 13};
					doors[count] = new Door (col + 1, row + 1, 'p', "maple", eC4);
					count ++;
					break;
				case 'q': //from pokecenter to maple
					int[] eC5 = {26, 17};
					doors[count] = new Door (col + 1, row + 1, 'q', "maple", eC5);
					count ++;
					break;
				case 's': //from maple to winterfell
					int[] eC6 = {9, 19};
					doors[count] = new Door (col + 1, row + 1, 's', "winterfell", eC6);
					count ++;
					break;
				case 'S': //from winterfell to maple
					int[] eC7 = {15, 2};
					doors[count] = new Door (col + 1, row + 1, 'S', "maple", eC7);
					count ++;
					break;
				}
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
			{					
				try
				{
					g.drawImage (ImageIO.read (new File (Map.curPath + "images\\_" + map[row][col].getProp() + "image.jpg")), col * blockW, row * blockH, null);
				}
				catch (IOException e)
				{
					try {
						g.drawImage (ImageIO.read (new File (Map.curPath + "images\\_Himage.jpg")), col * blockW, row * blockH, null);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						System.out.println (Map.curPath + "images\\_" + map[row][col].getProp() + "image.jpg");
					}
				}
				
			}
	}
	
	public int [] sendPlayer (int x, int y)
	{
		int[] newCor = new int [2];
		//recreates the map
		for (int i = 0; i < doors.length; i++)
		{
			if (doors[i].xPos == x && doors[i].yPos == y )
			{
				//System.out.println (doors[i].ending);
				newCor = doors[i].endCor;
				map = new Block [map.length][map[0].length];
				mapName = doors[i].ending.toUpperCase();
				File filePath = new File (curPath + "\\mapsTxt\\" + doors[i].ending.toLowerCase() + ".txt");
				int j = 0;
				try (Scanner scanner =  new Scanner(filePath))
				{
					while (scanner.hasNextLine())
					{
						map[j] = setProp (j, scanner.nextLine().toCharArray()); //stores each line/row by converting it into an char array
						j++;
					}
					scanner.close();
				}
				catch (FileNotFoundException e)
				{
					JOptionPane.showMessageDialog(new JFrame(),
							"No such file found!" + curPath + "\\mapsTxt\\" + doors[i].ending.toLowerCase() + ".txt",
							"Error",
							JOptionPane.ERROR_MESSAGE);;
				}
			}
		}
		addDoors();
		//System.out.println (newCor[0] + " " + newCor[1]);
		return newCor;
	}
}