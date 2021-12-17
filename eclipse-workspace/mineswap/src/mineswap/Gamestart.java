package mineswap;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Gamestart implements ActionListener{
	static Lv l = new Lv();
	static JFrame frame = new JFrame();
	static JPanel LvPanel = new JPanel();
	static MineFrame mf;

	public static void main (String[] args) {
		// TODO Auto-generated method stub
		LvPanel.setBounds(0, 0, 400, 390);
		LvPanel.setBackground(Color.green);
		LvPanel.add(l.easy);
		LvPanel.add(l.normal);
		LvPanel.add(l.hard);

		l.easy.addActionListener(new ActionListener()
		      {
			     public void actionPerformed(ActionEvent e)
			     	{
			    	 	frame.remove(LvPanel);
			    	 	frame.reshape(0, 0, 500, 500);
			    	 	mf = new MineFrame(9, 9, 10);
			    	 	frame.add(mf.panel);
				    }
			   });
		l.normal.addActionListener(new ActionListener()
	      {
		     public void actionPerformed(ActionEvent e)
		     	{
		    	 	frame.remove(LvPanel);
		    	 	frame.reshape(0, 0, 800, 850);
		    	 	mf = new MineFrame(16, 16, 40);
		    	 	frame.add(mf.panel);
			    }
		   });
		l.hard.addActionListener(new ActionListener()
	      {
		     public void actionPerformed(ActionEvent e)
		     	{
		    	 	frame.remove(LvPanel);
		    	 	frame.reshape(0, 0, 1400, 850);
		    	 	mf = new MineFrame(30, 16, 99);
		    	 	frame.add(mf.panel);
			    }
		   });
		LvPanel.setLayout(null);
		frame.setSize(350,350);
		frame.setLayout(null);
		frame.add(LvPanel);
//		MineFrame mf = new MineFrame();
//		frame.add(mf.panel);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
