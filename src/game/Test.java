package game;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

import map.*;
//test class
public class Test extends JFrame
{
	Map town;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//creates a new draw area for the map
		DrawArea showMap = new DrawArea(town.getWidth(), town.getHeight());
		content.add(showMap); //adds the draw area to the content pane
		//setResizable(false);
		setTitle ("Pokemon Amethyst Test 1.0");
		pack();
		setLocationRelativeTo(null); //centers the window
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
		}
	}
}
