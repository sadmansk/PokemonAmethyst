package game;

import game.*;
import game.Keys.Key;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;

import characters.Player;
import characters.Character;

import javax.swing.*;

import map.*;
//test class
public class SadTest extends JFrame implements KeyListener 
{
	Map town;
	Player testPlayer;
	Key key;
	DrawArea showMap;
	JPanel pausePanel, content;
	static boolean gameStarted = false; //using this to recognize whether the first map has been initialized
	Image offScreen;
	Graphics bufferGraphics;
	Structure house1, home, house2, house3, oakLab, pokecentre;

	//main method demonstrates an example of a Map
	public static void main (String [] args)
	{
		SadTest window = new SadTest();
		window.setVisible(true);
	}

	//default constructor
	public SadTest()
	{
		content = new JPanel ();     // Create a content pane
		//pausePanel = new Pause();
		content.setLayout (new BorderLayout ()); // Use BorderLayout for panel
		town = new Map (20, 30, "Maple"); //declares a new map

		this.setUndecorated(true); 
		//add a character (player) to the map
		testPlayer = new Player (28, 18, "Ash", town);
		//Character japhet = new Character (27, 18, "Japhet", town);
		//pausePanel.setPreferredSize(new Dimension(town.getWidth() + 16, town.getHeight() + 39));

		//add a few test Structures to the map
		house1 = new Structure (4, 5, "houseD");
		home = new Structure (24, 5, "houseL");
		house2 = new Structure (4, 13, "houseD");
		pokecentre = new Structure (24, 13, "poke");
		oakLab = new Structure (12, 9, "lab");

		//creates a new draw area for the map
		showMap = new DrawArea(town.getWidth(), town.getHeight());
		content.add(showMap, BorderLayout.EAST); //adds the draw area to the content pane
		//content.add(pausePanel,BorderLayout.WEST);   
		setContentPane(content); //adds the content pane to the JFrame
		//setResizable(false);
		//initialize();
		//this.createBufferStrategy(1);
		initialize();
		setTitle ("Pokemon Amethyst Test 1.0");
		setSize (new Dimension (town.getWidth(), town.getHeight())); //sets a static dimension for the window
		setLocationRelativeTo(null); //centers the window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closes when you click the exit button
	}

	public void initialize()
	{
		showMap.setFocusable(true); //makes sure that the computer is listening for input from the Map
		showMap.addKeyListener(this);   
		pauseOn = false;//OFF
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

			if (!gameStarted)
			{
				town.show (g); //when the game first starts draws the map
				house1.show (g);
				home.show (g);
				house2.show (g);
				pokecentre.show (g);
				oakLab.show(g);
			}
			testPlayer.show(g);
		}
	}

	//public String name;
	public  int numValue;
	public boolean pauseOn;

	@Override
	public void keyPressed(KeyEvent e)
	{
		numValue = e.getKeyCode()-37;
		if(e.getKeyCode()>=37 && e.getKeyCode()<=40){
			testPlayer.move(numValue);
			gameStarted = true; //tells the program that the map has been loaded once
			//			showMap.repaint();
			showMap.repaint();
			//drawStuff();

		}
		else if(e.getKeyCode() == KeyEvent.VK_ESCAPE){//press escape to exit
			dispose();
		}
		//		else if(e.getKeyCode()==80){
		//			//pause screen
		//			pausePanel = new Pause();
		//			pausePanel.setBackground(new Color(0, 0, 0,50));
		//			content.add(pausePanel, BorderLayout.CENTER); 
		//			System.out.println("paused key pressed");
		//			//pausePanel.repaint();
		//		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		//System.out.println("Released: "+name+": "+numValue);  
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		//Only use this for character input. Not for Directional Arrow Input
		if(e.getKeyChar()=='P' || e.getKeyChar()=='p'){ //'P' for PAUSE
			// System.out.println("1. stroke registered"); 
			//switcher();
			//this.repaint();
			//System.out.println("5.out of switcher");
		}
	}
}