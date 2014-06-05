package game;

import java.awt.*;
import java.awt.event.*;
import characters.Player;
import characters.Character;
import javax.swing.JFrame;
import javax.swing.JPanel;

import map.*;
//test class
public class Test extends JFrame
{
	Map town;
	Structure house1, home, house2, house3;

	//main method demonstrates an example of a Map
	public static void main (String [] args)
	{
		Test window = new Test();
		window.setVisible(true);
	}

	//default constructor
	public Test()
	{
		JPanel content = new JPanel ();     // Create a content pane
		content.setLayout (new BorderLayout ()); // Use BorderLayout for panel
		town = new Map (20, 30, "Maple"); //declares a new map
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
		//add a character (player) to the map
		Player test = new Player (28, 18, "Test", town);
		Character japhet = new Character (27, 18, "Japhet", town);
		
		//add a few test Structures to the map
		house1 = new Structure (4, 5, "house");
		home = new Structure (24, 5, "house");
		house2 = new Structure (4, 13, "house");
		house3 = new Structure (24, 13, "house");
		
		//creates a new draw area for the map
		DrawArea showMap = new DrawArea(town.getWidth(), town.getHeight());
		content.add(showMap); //adds the draw area to the content pane
		setContentPane(content); //adds the content pane to the JFrame
		//setResizable(false);
		setTitle ("Pokemon Amethyst Test 1.0");
		setSize (new Dimension (town.getWidth() + 16, town.getHeight() + 39)); //sets a static dimension for the window
		setLocationRelativeTo(null); //centers the window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closes when you click the exit button
	}
	
	//use this as the listener class, make any changes needed to make the character move around the map
	class AddListener implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			//repaints the draw area with the new changes made to the deck
			repaint ();
		}
	}

	class DrawArea extends JPanel
	{
		public DrawArea (int width, int height)
		{
			this.setPreferredSize (new Dimension (width, height)); // sets the size
		}
		//sets up the paint component
		public void paint(Graphics g)
		{
			town.show (g);
			house1.show (g);
			home.show (g);
			house2.show (g);
			house3.show (g);
		}
	}
}
