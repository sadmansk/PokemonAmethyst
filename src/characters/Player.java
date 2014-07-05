package characters;

import java.awt.Image;

import javax.swing.JTextArea;

import pokemons.Pokemon;
import map.*;

public class Player extends Character
{
	public boolean newTown, encounter;
	public JTextArea textArea;
	String message;//message of the block in front of the character
	//SPECIAL NOTE!!: the x and y coordinates start from 1, not 0 (hence 1 is subtracted when it is constructed in the map object)
	public Player(int x, int y, String name, Map map)
	{
		super(x, y, name, map);
		newTown = false;
		textArea  = new JTextArea();
		textArea.setEditable(false); 
		textArea.setFocusable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		//localMap.map [y - 1][x - 1].setProp ('P'); //the character P on the map identifies the player
	}

	public Player(int x, int y, String name, Map map, Pokemon poke)
	{
		super(x, y, name, map);
		newTown = false;
		textArea  = new JTextArea();
		textArea.setEditable(false); 
		textArea.setFocusable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		pokemon = poke;
		//localMap.map [y - 1][x - 1].setProp ('P'); //the character P on the map identifies the player
	}

	//takes in an integer and moves the character
	public String move (int m)
	{

		facing = m;
		switch (m)
		{
		case 0://left
			if (localMap.map [yPos - 1][xPos - 2].getPass()) //checks if the target block is passable
				xPos--;
			else{
				message = localMap.map [yPos - 1][xPos - 2].getMsg();
				//Thread thread = new Thread(new MyRunnable());
				//thread.start(); 
				textArea.setText(message); 

			}
			break;

		case 1://up
			if (localMap.map [yPos - 2][xPos - 1].getPass()) //checks if the target block is passable
				yPos--;
			else{
				message = localMap.map [yPos - 2][xPos - 1].getMsg();
				textArea.setText(message); 


			}
			break;

		case 2://right
			if (localMap.map [yPos - 1][xPos].getPass()) //checks if the target block is passable
				xPos++;
			else{
				message = localMap.map [yPos - 1][xPos].getMsg();
				textArea.setText(message); 


			}
			break;

		case 3://down
			if (localMap.map [yPos][xPos - 1].getPass()) //checks if the target block is passable
				yPos++;
			else{
				message = localMap.map [yPos][xPos - 1].getMsg();
				textArea.setText(message); 


			}
		}
		if (localMap.map[yPos - 1][xPos - 1].doorType())
			newTown = true; //adds a boolean that checks whether the player should be sent to a new map
		if (localMap.map[yPos - 1][xPos - 1].getWild() && pokemon != null)
			if (pokemon.health > 0)
				encounter = true;

		return message;
	}

	//checks the facing tile
	public int facX ()
	{
		switch (facing)
		{
		case 2: //looking right
			return 1;
		case 0: //looking left
			return -1;
		default:
			return 0;
		}
	}

	public int facY ()
	{
		switch (facing)
		{
		case 1: //looking up
			return -1;
		case 3: //looking down
			return 1;
		default:
			return 0;
		}
	}
}
