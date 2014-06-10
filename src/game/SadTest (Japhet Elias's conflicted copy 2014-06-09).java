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
		pausePanel.setBackground(new Color(0,0,0,50));
		//content.setDoubleBuffered(true);
		//pausePanel.setDoubleBuffered(true);
		/*
		//test to see if map is getting imported correctly
		for (int row = 0 ; row < town.map.length ; row++)
		{
			for (int col = 0 ; col < town.map[0].length ; col++)
			{
				System.out.print (town.map[row][col].getProp());
			}
			System.out.println ();
		}
		 */
		this.setUndecorated(true); 
		//add a character (player) to the map
		testPlayer = new Player (28, 18, "Ash", town);
		//Character japhet = new Character (27, 18, "Japhet", town);
		pausePanel.setPreferredSize(new Dimension(town.getWidth() + 16, town.getHeight() + 39));

		//add a few test Structures to the map
		house1 = new Structure (4, 5, "houseD");
		home = new Structure (24, 5, "houseL");
		house2 = new Structure (4, 13, "houseD");
		pokecentre = new Structure (24, 13, "poke");
		oakLab = new Structure (12, 9, "lab");

		//creates a new draw area for the map
		showMap = new DrawArea(town.getWidth()+16, town.getHeight()+39);
		content.add(showMap, BorderLayout.CENTER); //adds the draw area to the content pane
		//content.add(pausePanel,BorderLayout.WEST);   
		setContentPane(content); //adds the content pane to the JFrame
		//setResizable(false);
		//initialize();
		//this.createBufferStrategy(1);
		initialize();
		setTitle ("Pokemon Amethyst Test 1.0");
		setSize (new Dimension (town.getWidth() + 16, town.getHeight() + 39)); //sets a static dimension for the window
		setLocationRelativeTo(null); //centers the window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closes when you click the exit button
	}

	public void initialize()
	{
		
		showMap.setFocusable(true); //makes sure that the computer is listening for input from the Map
		showMap.addKeyListener(this);   
		
		pausePanel.addKeyListener(this);
		//showMap.setFocusable(true); //makes sure that the computer is listening for input from the Map
		pausePanel.setFocusable(true); //makes sure that the computer is listening for input from the Map
		
		pausePanel.setVisible(false);
		pauseOn = false;//OFF
       
	//	offScreen = createImage(town.getWidth() + 16, town.getHeight() + 39); 
     //   bufferGraphics = offScreen.getGraphics(); 

	//	drawStuff();
		
	}
	public void switcher ()
	{
		// if(){}
		// System.out.println("2.in switcher");

		if(pauseOn){//if ON
			pausePanel.setVisible(false);
			showMap.setVisible(true);
			pauseOn = false;//OFF
			//pausePanel.setFocusable(true);
			showMap.setFocusable(true);
			pausePanel.setFocusable(false);
			showMap.requestFocusInWindow();
			//pausePanel.transferFocusUpCycle(); 
			//System.out.println("3.pausePanel is not visible");
			this.repaint();

		}
		else{//if OFF
			pausePanel.setVisible(true);
			showMap.setVisible(false);
			pauseOn = true;//ON
			pausePanel.setFocusable(true);
			showMap.setFocusable(false);
//			showMap.setFocusable(true);
			pausePanel.requestFocusInWindow();
			//System.out.println("3.pausePanel is visible");
		}
//		System.out.println("4.leaving switcher");

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
				town.show (g); //when the game first starts draws the map
//			town.show(g);
//			testPlayer.show(g);
//			house1.show (g);
//			home.show (g);
//			house2.show (g);
//			pokecentre.show (g);
//			oakLab.show(g);
		}
	}

	//public String name;
	public  int numValue;
	public boolean pauseOn;
	// Input handling variables.
	//public boolean keyStateDown;

	// Key related variables.
//	public boolean isTappedDown;
//	public boolean isPressedDown;
//	public boolean lastKeyState;

	@Override
	public void keyPressed(KeyEvent e)
	{
		// TODO Auto-generated method stub
		//System.out.println("Source: "+ e.getSource());
		//name = e.getKeyText(e.getKeyCode());
		//numValue = e.getKeyCode()-37;
		//JButton btn = new JButton("button");
//		if(e.getKeyCode()>=37 && e.getKeyCode()<=40){
//			testPlayer.move(numValue);
//			//				x = testPlayer.xPos;
//			//y = ...
//			gameStarted = true; //tells the program that the map has been loaded once
//			showMap.repaint();
//
//		}
		numValue = e.getKeyCode()-37;
		if(e.getKeyCode()>=37 && e.getKeyCode()<=40){
			testPlayer.move(numValue);
			gameStarted = true; //tells the program that the map has been loaded once
//			showMap.repaint();
			this.repaint();
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
			switcher();
			//this.repaint();
			 //System.out.println("5.out of switcher");
		}
	}
//	private void drawStuff() {
//		BufferStrategy bf = this.getBufferStrategy();
//		Graphics g = null;
//	 
//		try {
//			g = bf.getDrawGraphics();
//	 
//			// It is assumed that mySprite is created somewhere else.
//			// This is just an example for passing off the Graphics object.
//			if (!gameStarted )
//				town.show(g);
//			testPlayer.show(g);
//			house1.show (g);
//			home.show (g);
//			house2.show (g);
//			pokecentre.show (g);
//			oakLab.show(g);
//			
//		} finally {
//			// It is best to dispose() a Graphics object when done with it.
//			g.dispose();
//		}
//	 
//		// Shows the contents of the backbuffer on the screen.
//		bf.show();
//	 
//	        //Tell the System to do the Drawing now, otherwise it can take a few extra ms until 
//	        //Drawing is done which looks very jerky
//	        Toolkit.getDefaultToolkit().sync();	
//	}
	public void paint(Graphics g)
	{
//		if (!gameStarted)
	//		town.show(g);
		//bufferGraphics.clearRect(0,0,town.getWidth() + 16, town.getHeight() + 39); 
		if (!gameStarted )
			town.show(g);
		testPlayer.show(g); 
		house1.show (g);
		home.show (g);
		house2.show (g);
		pokecentre.show (g);
		oakLab.show(g);
		//drawStuff();
		//showMap.repaint()
//		testPlayer.show(g);
//		house1.show (g);
//		home.show (g);
//		house2.show (g);
//		pokecentre.show (g);
//		oakLab.show(g);
	}
}
