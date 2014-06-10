package characters;

import java.awt.Image;
import map.*;

public class Player extends Character
{
	//SPECIAL NOTE!!: the x and y coordinates start from 1, not 0 (hence 1 is subtracted when it is constructed in the map object)
	public Player(int x, int y, String name, Map map)
	{
		super(x, y, name, map);
		//localMap.map [y - 1][x - 1].setProp ('P'); //the character P on the map identifies the player
	}

	//takes in an integer and moves the character
	public void move (int m)
	{
		facing = m;
		switch (m)
		{
		case 0://left
			if (localMap.map [yPos - 1][xPos - 2].getPass()) //checks if the target block is passable
				xPos--;
			break;
			
		case 1://up
			if (localMap.map [yPos - 2][xPos - 1].getPass()) //checks if the target block is passable
				yPos--;
			break;

		case 2://right
			if (localMap.map [yPos - 1][xPos].getPass()) //checks if the target block is passable
				xPos++;
			break;
			
		case 3://down
			if (localMap.map [yPos][xPos - 1].getPass()) //checks if the target block is passable
				yPos++;
		}
		//localMap.map [yPos - 1][ xPos - 1].setProp ('P');

	}
}
