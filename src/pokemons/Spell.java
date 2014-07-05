package pokemons;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Timer;

import javax.imageio.ImageIO;

import map.Map;

public class Spell //implements ActionListener
{
	public String name;
	public static int FIRE = 0, WATER = 1, GRASS = 2, LIGHTNING = 3, NORMAL = 4; //stores a constant integer for each type
	private int type;
	private double damageMul; //adds a base damage that adds more according to the level
	public int damage;

	public Spell (String n, int l)
	{
		name = n;
		switch (name.toUpperCase()) //assigns damage to the spells
		{
		case "THUNDERBOLT":
			type = LIGHTNING;
			damageMul = 2;
			break;
		case "QUICK-ATTACK":
			type = NORMAL;
			damageMul = 2.5;
			break;	
		case "QUICKATTACK":
			type = NORMAL;
			damageMul = 2.5;
			break;
		case "TACKLE":
			type = NORMAL;
			damageMul = 1.75;
			break;
		case "TAIL-ATTACK":
			type = NORMAL;
			damageMul = 2.25;
			break;
		case "EMBER":
			type = FIRE;
			damageMul = 2;
			break;
		case "WATER-GUN":
			type = WATER;
			damageMul = 2.5;
			break;
		case "BUBBLES":
			type = WATER;
			damageMul = 2;
			break;
		case "VINE-WHIP":
			type = GRASS;
			damageMul = 2.25;
			break;
		}
		damage = (int)(damageMul * l);
	}

	public void animate (Graphics g, int x, Pokemon P, Pokemon O) //animates the spell! by taking in the coordinates from the callling method
	{
		g.setColor(Color.white);
		try
			{
				switch (name.toUpperCase())
				{
				case "THUNDERBOLT":
					g.drawImage (ImageIO.read (new File (Map.curPath + "images\\pokemons\\thunderbolt.png")), 300 + (x * 10), 100, null);
					break;
				case "TACKLE":
				case "QUICK-ATTACK":
					g.fillRect (0, 100, 600, 200);
					g.drawImage (P.battle, 100 + (x * 20), 100, null); 
					break;
				case "TAIL-ATTACK":
				case "QUICKATTACK":
				case "VINE-WHIP":
					g.fillRect (300, 100, 600, 200);
					g.drawImage (P.opponent, 600 - (x * 20), 100, null);
					break;
				case "EMBER":
					g.drawImage (ImageIO.read (new File (Map.curPath + "images\\pokemons\\ember.png")), 350 - (x * 10), 100, null);
					break;
				case "WATER-GUN":
					g.drawImage (ImageIO.read (new File (Map.curPath + "images\\pokemons\\watergun.png")), 350 - (x * 8), 100, null);
					break;
				case "BUBBLES":
					g.drawImage (ImageIO.read (new File (Map.curPath + "images\\pokemons\\bubbles.png")), 350 - (x * 10), 100, null);
					break;
				}                
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
