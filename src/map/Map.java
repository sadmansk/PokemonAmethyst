package map;

import java.awt.Image;

public class Map
{
	private Block [][] map;
	
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
}

class Block
{
	private int [] block = new int [2]; //stores the coordinates of a block on the map as x and y coordinates
	private char prop;
	private Image image;
	private boolean passable;
	
	public Block (int x, int y)
	{
		block [0] = x;
		block [1] = y;
		prop = ' ';
		passable = true;
	}
	
	public Block (int x, int y, char prop)
	{
		block [0] = x;
		block [1] = y;
		this.prop = prop;
	}
	
	public String toString ()
	{
		return "(" + block [0] + ", " + block [1] + ")";
	}
	
	public char getProp ()
	{
		return prop;
	}
	
	public void setProp (char prop)
	{
		this.prop = prop;
	}
}
