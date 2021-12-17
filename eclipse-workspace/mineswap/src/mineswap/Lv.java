package mineswap;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Lv extends JFrame {
	JButton easy;
	JButton normal;
	JButton hard;
	public Lv() {
		easy = new JButton();
		normal = new JButton();
		hard = new JButton();
		easy.setBounds(50, 70, 200, 50);
		easy.setText("Easy");
		normal.setBounds(50, 120, 200, 50);
		normal.setText("Normal");
		hard.setBounds(50, 170, 200, 50);
		hard.setText("Hard");
	}


}
