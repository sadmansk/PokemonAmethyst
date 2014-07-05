package map;

import java.awt.Image;

import pokemons.Pokemon;


public class Block
{
	public int xPos, yPos; //stores the coordinates of a block on the map as x and y coordinates
	protected char prop;
	protected Image image;
	protected boolean passable;
	public String message;
	private boolean wild = false;
	public Pokemon wP; //stores the wild pokemon

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
		case 'O': //professor oak
		case 'A': //mom
		case 'l': //locked door'
		case 'N': //nothing
		case 'K': //rock
		case 'W': //water
		case 'V': //TV
		case 'B': //bed
		case 'E': //heal center
		case 'i': //table
			passable = false;
			break;

		case '1': //charmander
			wild = true;
			wP = new Pokemon ("Charmander", 10);
		case 'G': //grass
			int rand = (int)(Math.random() * 10) + 1;
			int pokeLvl = (int)(Math.random() * 3) + 3; //random pokemon between lvl 3-6
			if (rand == 2)
			{
				wild = true; // one in 10 chances for a bulbasaur to exist in the grass
				wP = new Pokemon ("Bulbasaur", pokeLvl);
			}
			else if (rand == 1)
			{
				wild = true;
				wP = new Pokemon ("Squirtle", pokeLvl);
			}
			passable = true;
			break;
		case 'P': //the player
		case ' ': //empty space
		case 'F': //floor
		case 'm': //maple from home
		case 'q': //maple from center
		case 'p': //maple from lab
		case 'r':
		case 'h': //home
		case 'c': //pokecenter
		case 'o': //oaks lab
		default: //for everything else
			passable = true;
			break;
		}
	}

	public String getMsg()
	{//gets the string message associated with current block 
		switch (prop)
		{
		case 'A': //mom
			message = "Mom: Be careful out there!";
			break;
		case 'O': //oak
			message = "I left a pokemon on the table for you! Be responsible!";
			break;
		case 'E': //nurse
			message = "Yes I can heal your Pokemon! There you go!";
			break;
		case 'l': //locked doors
			message = "The door is locked!";
			break;
		case 'i': // table
			message = "You got a Pikachu!";
			break;
		case 'B': //bed
			message = "Don't go to sleep during daytime!";
			break;
		case 'V': //TV
			message = "No TV when the weather is so nice!";
			break;
		}
		return message;
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

	public void setPass (boolean p)
	{
		passable = p;
	}

	public boolean doorType() //returns whether the prop is of door/portal type
	{
		switch  (prop)
		{
		case 'm': //maple from home
		case 'q': //maple from center
		case 'p': //maple from lab
		case 'h': //home
		case 'c': //pokecenter
		case 'o': //oaks lab
		case 'r': //other door in lab
		case 'S': //winterfell to maple
		case 's': //maple to winterfell
			return true;
		default:
			return false;
		}
	}

	public boolean getWild () //returns whether there is a wild pokemon in the block
	{
		if (wild)
			if (wP.health > 0)
				return true;
		return false;
	}
}
