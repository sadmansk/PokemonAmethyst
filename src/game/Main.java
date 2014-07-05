package game;

import game.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;

import characters.Player;
import characters.Character;

import javax.swing.*;

import pokemons.Pokemon;
import map.*;
//test class
public class Main extends JFrame implements KeyListener 
{
	Map mainMap;
	Player testPlayer;
	DrawArea showMap;
	JPanel pausePanel, content;
	static boolean gameStarted = false, battle = false, battleStarted = false, battleEnded = true, yourTurn = true, spellCast = false; //using this to recognize whether the first map has been initialized
	Image offScreen;
	Graphics bufferGraphics;
	Structure house1, home, house2, house3, oakLab, pokecentre, heal, oak;
	//Character oak, mom;
	Pokemon wildPokemon;
	DBox text;
	String message;
	int arrowPos = 0, spellPos = 0, oppSpell = 0;
	SpellAnimation anim = new SpellAnimation();
	Timer T = new Timer (80, anim); //sets up a timer for animation

	//main method demonstrates an example of a Map
	public static void main (String [] args)
	{
		Main window = new Main();
		window.setVisible(true);
	}

	//default constructor
	public Main()
	{
		content = new JPanel ();     // Create a content pane
		//pausePanel = new Pause();
		content.setLayout (new BorderLayout ()); // Use BorderLayout for panel
		mainMap = new Map (20, 30, "lab"); //declares a new map, to place professor oak on the map
		//oak = new Character (15, 10, "robstark", mainMap);
		text = new DBox();
		mainMap = new Map (20, 30, "home"); //declares a new map, game starts from player's home
		this.setUndecorated(true);

		//add a character (player) to the map
		testPlayer = new Player (15, 10, "Ash", mainMap); //gives initial coordinates to the player

		//add a few test Structures to the map
		house1 = new Structure (4, 5, "houseD");
		home = new Structure (24, 5, "houseL");
		house2 = new Structure (4, 13, "houseD");
		pokecentre = new Structure (24, 13, "poke");
		oakLab = new Structure (12, 9, "lab");

		//add healing in pokecenter
		heal = new Structure (10, 6, "heal");

		//add a layout to oaks lab
		oak = new Structure (11, 7, "oak");

		/**JFrame Components zone**/
		//creates a new draw area for the map
		showMap = new DrawArea(mainMap.getWidth(), mainMap.getHeight() + 100);
		content.add(showMap, BorderLayout.CENTER); //adds the draw area to the content pane
		setContentPane(content); //adds the content pane to the JFrame
		initialize();
		setTitle ("Pokemon Amethyst Test 1.0");
		setSize (new Dimension (mainMap.getWidth(), mainMap.getHeight()+100)); //sets a static dimension for the window
		setLocationRelativeTo(null); //centers the window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closes when you click the exit button
	}

	public void initialize()
	{
		showMap.setFocusable(true); //makes sure that the computer is listening for input from the Map
		showMap.addKeyListener(this);
	}

	class DrawArea extends JPanel
	{
		public DrawArea (int width, int height)
		{
			this.setPreferredSize (new Dimension (width, height)); // sets the size
			//	this.setDoubleBuffered(true); 
		}
		//sets up the paint component
		public void paint(Graphics g)
		{
			this.setFocusable(true);
			if (!battle)
			{
				if (!gameStarted)//when the game first starts draws the map	
				{
					switch (mainMap.mapName)
					{
					case "HOME": //when the map is home		
						message = "HOME";
						mainMap.show (g);
						testPlayer.show(g);
						break;
					case "MAPLE": //when the map is maple town draws all the structures
						message = "MAPLE TOWN";
						mainMap.show (g);
						testPlayer.show (g);
						house1.show (g);
						home.show (g);
						house2.show (g);
						pokecentre.show (g);
						oakLab.show(g);
						break;
					case "POKE":
						message = "POKE CENTER";
						mainMap.show (g);
						heal.show(g);
						testPlayer.show(g);
						break;
					case "LAB":
						message = "OAK'S LAB";
						mainMap.show (g);
						oak.show(g);
						testPlayer.show(g);
						break;
					case "WINTERFELL":
						message = "WINTERFELL FOREST";
						mainMap.show (g);
						testPlayer.show(g);
						break;
					}
				}
				else if (battleEnded)
					testPlayer.show(g);
			}
			else //draws a battle screen
			{
				if (!battleStarted)
				{
					battleEnded = false;
					g.setColor(Color.white);
					g.fillRect(0, 0, 900, 600);
					message = "A wild " + wildPokemon.name + " has appeared!";
					testPlayer.pokemon.initBattle (wildPokemon, g);
				}
				battleStarted = true; //tells the program that the initial screen is painted
				if (battleStarted)
					testPlayer.pokemon.spellBox (arrowPos, g, wildPokemon);
				if (testPlayer.pokemon.health < 1) //if your pokemon faints
				{
					testPlayer.encounter = false;
					battle = false;
					message = "You lost the battle! Go to the pokecenter to get your pokemon healed.";
				}
			}

			if (message == "Yes I can heal your Pokemon! There you go!" && testPlayer.pokemon != null) testPlayer.pokemon.heal(); //when ash goes to pokecenter!
			if (message == "You got a Pikachu!" && testPlayer.pokemon== null) testPlayer.addPokemon ("Pikachu", 5); //adds a level five pikachu to the player
			if (spellCast)
			{
				T.start();
				if (!yourTurn)
				{
					g.setColor(Color.white);
					g.fillRect(300, 100, 300, 200);
					testPlayer.pokemon.spells[arrowPos].animate(g, spellPos, testPlayer.pokemon, wildPokemon);
					if (spellPos == 15)
					{
						testPlayer.pokemon.initBattle (wildPokemon, g);
						arrowPos = 0;
					}
				}
				else if (yourTurn)
				{
					g.setColor(Color.white);
					g.fillRect(300, 100, 300, 200);
					wildPokemon.spells[oppSpell].animate(g, spellPos, wildPokemon, testPlayer.pokemon);
					if (spellPos == 15)
						testPlayer.pokemon.initBattle (wildPokemon, g);
				}
			}
			//draws the dialogue box
			g.setColor (Color.black);
			text.showMsg(message, g);
		}
	}

	//a timer class to handle animation delays with the spells
	class SpellAnimation implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent event)
		{
			if (spellPos < 15)
			{
				spellPos++; //this variable will control the position of the spell
				showMap.repaint();
			}
			else
			{
				T.stop(); //ends the timer after 15 iterations
				spellPos = 0;
				spellCast = false;
			}

		}

	}
	//public String name;
	public  int numValue;
	public boolean pauseOn;

	//handles the input from the keyboard
	@Override
	public void keyPressed(KeyEvent e)
	{
		numValue = e.getKeyCode()-37;
		if(e.getKeyCode()>=37 && e.getKeyCode()<=40 && !battle)
		{
			testPlayer.move(numValue);
			gameStarted = true; //tells the program that the map has been loaded once
			//if the passed block is a door
			if (testPlayer.newTown)
			{
				int newCor [] = mainMap.sendPlayer (testPlayer.xPos, testPlayer.yPos);
				gameStarted = false; //tells the program that the map  has to be repainted
				if (testPlayer.pokemon == null)
				{
					testPlayer = new Player (newCor[0], newCor[1], "Ash", mainMap);
				}
				testPlayer = new Player (newCor[0], newCor[1], "Ash", mainMap, testPlayer.pokemon);
			}
			//if a pokemon is encountered
			if (testPlayer.encounter)
			{
				battle = true;
				battleStarted = false;
				wildPokemon = mainMap.map [testPlayer.yPos - 1][testPlayer.xPos - 1].wP;
			}
			showMap.repaint();
		}
		//for interactive blocks
		if (e.getKeyCode() == KeyEvent.VK_X && !battle)
		{
			if (mainMap.map [testPlayer.yPos - 1 + testPlayer.facY()][testPlayer.xPos - 1 + testPlayer.facX()].getMsg() != null)
				message = mainMap.map [testPlayer.yPos - 1 + testPlayer.facY()][testPlayer.xPos - 1 + testPlayer.facX()].getMsg();
			showMap.repaint();
			//gameStarted = false;
		}
		//for the battle scene handles the arrow movement
		if (battle && e.getKeyCode() == 38) //arrow goes up
		{
			//battleStarted = true;
			if (arrowPos > 0)
				arrowPos--;
			showMap.repaint();
		}
		if (battle && e.getKeyCode() == 40) //arrow goes down
		{
			//battleStarted = true;
			if (arrowPos < 2)
				arrowPos++;
			showMap.repaint();
		}

		//when a spell is selected and casted
		if (battle && e.getKeyCode() == KeyEvent.VK_X)
		{
			if (yourTurn) //when its your turn, the spell is cast
			{
				wildPokemon.health -= testPlayer.pokemon.damageDone (arrowPos); //sends in the current position of the arrow as the spell index
				message = testPlayer.pokemon.name + " used " + testPlayer.pokemon.spells[arrowPos].name + "!";
				yourTurn = false;
				spellCast = true;
			}
			else if (!yourTurn) //when its the opponent's turn, the opponent casts a spell
				if (wildPokemon.health > 0)
				{
					oppSpell = (int)(Math.random() * 3); //randomizes what the wild pokemon will cast
					testPlayer.pokemon.health -= wildPokemon.damageDone (oppSpell); //does the damage
					message = wildPokemon.name + " used " + wildPokemon.spells[oppSpell].name + "!";
					yourTurn = true;
					spellCast = true;
				}
				else
				{
					testPlayer.encounter = false;
					battle = false;
					message = "You won the battle! " + testPlayer.pokemon.name + " got " + testPlayer.pokemon.addExperience (wildPokemon.level) + " exp points.";
				}
			showMap.repaint();
		}
		else if (e.getKeyCode() == KeyEvent.VK_X && !battle && !battleEnded)
		{
			gameStarted = false; //redraws the map
			battleEnded = true;
			showMap.repaint(); //repaints the map
		}
		else if(e.getKeyCode() == KeyEvent.VK_ESCAPE)//press escape to exit
		{
			System.exit(0);
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		//System.out.println("Released: "+name+": "+numValue);  
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		// TODO Auto-generated method stub
	}
}
