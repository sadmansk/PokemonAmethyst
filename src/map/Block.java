package map;

import java.awt.Image;


public class Block
{
	protected int xPos, yPos; //stores the coordinates of a block on the map as x and y coordinates
	protected char prop;
	protected Image image;
	protected boolean passable;

	public Block (int x, int y)
	{
		xPos = x;
		yPos = y;
		prop = ' ';
		passable = true;
	}

	public Block (int x, int y, char prop)
	{
		xPos = x;
		yPos = y;
		this.prop = prop;
		switch (prop)
		{
		case 'T': //trees
		case 'H': //structures
		case 'C': //characters
		case 'L': //ledge
			passable = false;
			break;

		case ' ': //empty space
		case 'G': //grass
		default: //for everything else
			passable = true;
			break;
		}
	}

	public String toString ()
	{
		return "(" + xPos + ", " + yPos + ")";
	}

	public char getProp ()
	{
		return prop;
	}

	public void setProp (char prop)
	{
		this.prop = prop;
	}

	public boolean getPass ()
	{
		return passable;
	}


}
