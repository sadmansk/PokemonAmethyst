package pokemons;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import map.Map;
public class Pokemon
{
	public String name;
	public int level,  type;
	public int health;
	public int experience;
	public Image battle, opponent, healthBar;
	public Spell[] spells;
	Font font = new Font("Verdana", Font.PLAIN, 20);

	public Pokemon (String n, int l)
	{
		name = n;
		level = l;
		experience = l * 100;
		health = l * 10;
		spells = new Spell[3];
		try
		{
			battle = ImageIO.read (new File (Map.curPath + "images\\pokemons\\" + name.toLowerCase() + ".jpg"));
		}
		catch (IOException e)
		{
			System.out.println (Map.curPath + "images\\pokemons\\" + name.toLowerCase() + ".jpg");
		}
		try
		{
			opponent = ImageIO.read (new File (Map.curPath + "images\\pokemons\\" + name.toLowerCase() + "o.jpg"));
		}
		catch (IOException e)
		{
			System.out.println (Map.curPath + "images\\pokemons\\" + name.toLowerCase() + "o.jpg");
		}
		try
		{
			healthBar = ImageIO.read (new File (Map.curPath + "images\\pokemons\\" + name.toLowerCase() + "health.jpg"));
		}
		catch (IOException e)
		{
			System.out.println (Map.curPath + "images\\pokemons\\" + name.toLowerCase() + "health.jpg");
		}
		switch (name.toUpperCase())
		{
		case "PIKACHU":
			type = Spell.LIGHTNING;
			spells[0] = new Spell ("THUNDERBOLT", level);
			spells[1] = new Spell ("QUICK-ATTACK", level);
			spells[2] = new Spell ("TACKLE", level);
			break;

		case "SQUIRTLE":
			type = Spell.WATER;
			spells[0] = new Spell ("WATER-GUN", level);
			spells[1] = new Spell ("BUBBLES", level);
			spells[2] = new Spell ("TAIL-ATTACK", level);
			break;

		case "CHARMANDER":
			type = Spell.FIRE;
			spells[0] = new Spell ("EMBER", level);
			spells[1] = new Spell ("QUICKATTACK", level);
			spells[2] = new Spell ("TAIL-ATTACK", level);
			break;

		case "BULBASAUR":
			type = Spell.GRASS;
			spells[0] = new Spell ("VINE-WHIP", level);
			spells[1] = new Spell ("QUICKATTACK", level);
			spells[2] = new Spell ("TAIL-ATTACK", level);
			break;

		default:
			type = Spell.NORMAL;
			break;
		}
		//check to see if the custom font is importable
		try
		{
			//create the font to use. Specify the size!
			font = Font.createFont(Font.TRUETYPE_FONT, new File(Map.curPath + "pokemon_fire_red.ttf")).deriveFont(30f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			//register the font
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(Map.curPath + "pokemon_fire_red.ttf")));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		catch(FontFormatException e)
		{
			e.printStackTrace();
		}
	}

	//fight!
	public void initBattle (Pokemon O, Graphics g) //shows the battle screen
	{
		g.setColor(Color.white);
		g.fillRect(300, 100, 300, 200);
		g.drawImage (battle, 100, 100, null); //draws the user's pokemon
		g.drawImage (O.opponent, 600, 100, null); //draws the opponent's pokemon

		g.setFont(font);
		g.setColor (Color.black);

		//also draws the health bars and levels
		g.drawImage (healthBar, 75, 15, null);
		g.drawString (level + "", 280, 48);
		g.drawImage (O.healthBar, 575, 15, null);
		g.drawString (O.level + "", 780, 48);
		
		//draws the health
		g.setColor (Color.green);
		if ((int)(133 * getPercHealth()) > 0)
			g.fillRect (180, 61, (int)(133 * getPercHealth()), 10);
		if ((int)(133 * O.getPercHealth()) > 0)
			g.fillRect (680, 61, (int)(133 * O.getPercHealth()), 10);

	}

	public void spellBox (int pos, Graphics g, Pokemon O) // takes in the position of the arrow with the spells
	{
		g.setFont(font);
		//contains all the spells
		g.setColor (Color.black);
		g.fillRect (50, 350, 300, 200);
		g.setColor (Color.white);
		for (int i = 0; i < spells.length; i++)
		{
			g.drawString (spells[i].name, 90, 400 + (i * 40));
		}
		g.setColor (Color.white);
		int x[] = {70, 85, 70};
		int y[] = {381 + (pos * 40), 391 + (pos * 40), 401 + (pos * 40)};
		g.drawPolygon (x, y, 3); //draws an arrow
	}

	public int damageDone (int spellIndex) //takes in the index of the spell and casts it on the opponent!
	{
		return spells[spellIndex].damage;
	}

	public double getPercHealth ()
	{
		return (double)health / (level * 10);
	}
	public int addExperience (int l)
	{
		int exp = l * 10;
		experience += exp;
		level = experience / 100;
		return exp;
	}

	public void heal () //heals back the pokemon to full health
	{
		health = level * 10;
	}
}
