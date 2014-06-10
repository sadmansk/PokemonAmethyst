package game;

import java.awt.*;
import javax.swing.*;

public class Pause extends JFrame{
	//public boolean pauseOn;
	JPanel pausePanel;
	public Pause (){
        //this.setVisible(false);
       // this.setOpaque(false);
        //pauseOn = false;
       // this.setBackground(Color (0,0,0,50)); 
		pausePanel = new JPanel();
        JButton btn = new JButton("button");
       // btn.setOpaque(false);
       // btn.setContentAreaFilled(false);
       // btn.setSelected(false);
        pausePanel.add(btn);
        setContentPane(pausePanel);
	}
//	public void paintComponent(Graphics g) {
//        g.setColor(getBackground());
//        Rectangle r = g.getClipBounds();
//        g.fillRect(r.x, r.y, r.width, r.height);
//        super.paintComponent(g);
//    }
	
}
