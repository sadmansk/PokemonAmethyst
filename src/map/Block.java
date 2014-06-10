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
		prop = ' '; //adds empty spaces as default
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
		case 'l': //locked door
			passable = false;
			break;
			
		case 'P': //the player
		case ' ': //empty space
		case 'G': //grass
		case 'D': //unlocked door
		case 'd': //stairs down
		case 'u': //stairs up
		case 'F': //floor
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
