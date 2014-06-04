package map;

import java.awt.Image;

public class Map
{
	private Block [][] map;
	public static int blockW = 10, blockH = 10;
	
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