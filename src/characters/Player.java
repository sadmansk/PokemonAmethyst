package characters;

import java.awt.Image;
import map.*;

public class Player extends Character
{
	public Player(int x, int y, String name, Map map)
	{
		super(x, y, name, map);
	}
	
	//takes in an integer and moves the character
	public void move (int m)
	{
		facing = m;
		switch (m)
		{
		case 0:
			if (localMap.map [yPos + 1][xPos].getPass()) //checks if the target block is passable
				yPos++;
			break;
			
		case 1:
			if (localMap.map [yPos][xPos - 1].getPass()) //checks if the target block is passable
				xPos--;
			break;
			
		case 2:
			if (localMap.map [yPos - 1][xPos].getPass()) //checks if the target block is passable
				yPos--;
			break;
			
		case 3:
			if (localMap.map [yPos][xPos + 1].getPass()) //checks if the target block is passable
				xPos++;
		}
	}
}
