package map;

import java.awt.Image;


public class Block
{
	protected int [] block = new int [2]; //stores the coordinates of a block on the map as x and y coordinates
	protected char prop;
	protected Image image;
	protected boolean passable;
	
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
