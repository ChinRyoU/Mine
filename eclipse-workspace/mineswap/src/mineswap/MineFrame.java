package mineswap;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MineFrame extends JFrame implements MouseListener{
	static int length;
	static int width;
	int amount;
	int mine_left;
	JFrame over_frame;
	JPanel over_panel;
	JButton[][] button;
	static JPanel panel;
	int[][] mine;
	static Element[][] element;
	public MineFrame(int l, int w,int a) {
		length = l;
		width = w;
		amount = a;
		mine_left = a;
		over_frame = new JFrame();
		over_frame.setSize(200, 300);
		over_frame.setLocation(length / 4, width / 4);
		panel = new JPanel();
		panel.setBackground(Color.white);
		panel.setBounds(0, 0, 90 * w, 70 * l);
		panel.setLayout(null);
	//	frame.setLayout(null);

	//	frame.setSize(1000, 1000);
		element = new Element[length][width];
		button = new JButton[length][width];

		int no = 1;
		for(int i = 0; i < length; i++) {
			for(int j = 0; j < width; j++) {
				element[i][j] = new Element();
				element[i][j].No = no;
				no++;
				element[i][j].button = new JButton();
				element[i][j].button.setBounds((i + 1) * 45 - 25, (j + 1) * 45 - 25, 45, 45);
				element[i][j].button.addMouseListener(this);
				panel.add(element[i][j].button);
				panel.revalidate();
			}
		}

	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		e.consume();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		String num;
		Outer:
		for(int i = 0; i < length; i++) {
			for(int j = 0; j < width; j++) {
				if(e.getSource() == element[i][j].button && element[i][j].mine == 11 && e.getButton() != e.BUTTON3) {
					element[i][j].mine = 0;
					for(int x = 0; x < length; x++) {
						for(int y = 0; y < width; y++) {
							if((x == i - 1 && Math.abs(y - j) <= 1) || (x == i + 1 && Math.abs(y - j) <= 1) || (x == i && Math.abs(y - j) <= 1)) {
								element[x][y].mine = 0;
							}
						}
					}
					while(amount > 0) {
						int place = (int) (Math.random() * (length * width)) ;
						for(int x = 0; x < length; x++) {
							for(int y = 0; y < width; y++) {
								if(element[x][y].mine == 11 && element[x][y].No == place) {
									element[x][y].mine = -1;
									amount--;
								}
							}
						}
					}
					for(int x = 0; x < length; x++) {
						for(int y = 0; y < width; y++) {
							if(element[x][y].mine == 11) {
								element[x][y].mine = 0;
							}
						}
					}

					checkaround(element);
					element[i][j].button.setEnabled(false);
					openaround(element, i, j);
					break Outer;
				}else if(e.getSource() == element[i][j].button && e.getButton() != e.BUTTON3 && element[i][j].flag == false){
					element[i][j].button.setEnabled(false);
					num = String.valueOf(element[i][j].mine);
					if(element[i][j].mine == -1) {
						element[i][j].button.setText("*");

	//					over_frame.setVisible(true);
						element[i][j].button.setBackground(Color.yellow);
						openall();
				//		panel.revalidate();
					}else if(element[i][j].mine == 0) {
						openaround(element, i, j);
					}
					else {
						element[i][j].button.setText(num);
					}
					break Outer;
				}else if( e.getButton() == e.BUTTON3 && e.getSource() == element[i][j].button) {
					if(element[i][j].flag == false && element[i][j].button.isEnabled() ) {
						element[i][j].button.setText("!");
						element[i][j].flag = true;
						mine_left--;
					}else if(element[i][j].flag == true && element[i][j].button.isEnabled()){
						element[i][j].button.setText("");
						element[i][j].flag = false;
						mine_left++;
					}

				}
			}
		}
		// TODO Auto-generated method stub
		for(int i = 0; i < length; i++) {
			for(int j = 0; j < width; j++) {
				if( e.getModifiersEx()==(MouseEvent.BUTTON3_DOWN_MASK + MouseEvent.BUTTON1_DOWN_MASK) && e.getSource() == element[i][j].button && !element[i][j].button.isEnabled()) {
					e.consume();
					int flag_around = 0;
					try {
						if(element[i - 1][j - 1].flag == true) {
							flag_around++;
							System.out.println("¨I"+ element[i - 1][j - 1].mine);
						}
					}catch(Exception ex) {
						
					}
					try {
						if(element[i][j - 1].flag == true) {
							flag_around++;
							System.out.println("¡ü"+ element[i][j - 1].mine);
						}
					}catch(Exception ex) {
						
					}
					try {
						if(element[i + 1][j - 1].flag == true) {
							flag_around++;
							System.out.println("¨J"+ element[i + 1][j - 1].mine);
						}
					}catch(Exception ex) {
						
					}
					try {
						if(element[i - 1][j].flag == true) {
							flag_around++;
							System.out.println("¡û"+ element[i - 1][j].mine);
						}
					}catch(Exception ex) {
						
					}
					try {
						if(element[i + 1][j].flag == true) {
							flag_around++;
							System.out.println("¡ú"+ element[i + 1][j].mine);
						}
					}catch(Exception ex) {
						
					}
					try {
						if(element[i - 1][j + 1].flag == true) {
							flag_around++;
							System.out.println("¨L"+ element[i - 1][j + 1].mine);
						}
					}catch(Exception ex) {
						
					}
					try {
						if(element[i][j + 1].flag == true) {
							flag_around++;
							System.out.println("¡ý" + element[i][j + 1].mine);
						}
					}catch(Exception ex) {
						
					}
					try {
						if(element[i + 1][j + 1].flag == true) {
							flag_around++;
							System.out.println("¨K" + element[i + 1][j + 1].mine);
						}
					}catch(Exception ex) {
						
					}
					if(flag_around == element[i][j].mine && flag_is_mine(element, i, j) == true) {
						System.out.println("Yes");
						openaround(element, i, j);
					}else if(flag_around == element[i][j].mine && flag_is_mine(element, i, j) == false) {
						element[i][j].button.setBackground(Color.yellow);
						openall();
					}
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	public static void openall() {
		for(int i = 0; i < length; i++) {
			for(int j = 0; j < width; j++) {
				if(	element[i][j].button.isEnabled() == true) {
					element[i][j].button.setEnabled(false);
					if(element[i][j].mine == -1) {
						element[i][j].button.setText("*");
					}else if(element[i][j].mine > 0) {
						element[i][j].button.setText(String.valueOf(element[i][j].mine));
					}
				}
			}
		}
//		Gamestart.frame.remove(panel);
//		Gamestart.frame.dispose();
//		Gamestart.main(null);
	}
	
	public static boolean flag_is_mine(Element[][] ele,int i,int j) {
		try {
			if(element[i - 1][j - 1].flag == true && element[i - 1][j - 1].mine != -1) {
				return false;
			}
		}catch(Exception ex) {
			
		}
		try {
			if(element[i][j - 1].flag == true && element[i][j - 1].mine != -1) {
				return false;
			}
		}catch(Exception ex) {
			
		}
		try {
			if(element[i + 1][j - 1].flag == true && element[i + 1][j - 1].mine != -1) {
				return false;
			}
		}catch(Exception ex) {
			
		}
		try {
			if(element[i - 1][j].flag == true && element[i - 1][j].mine != -1) {
				return false;
			}
		}catch(Exception ex) {
			
		}
		try {
			if(element[i + 1][j].flag == true && element[i + 1][j].mine != -1) {
				return false;
			}
		}catch(Exception ex) {
			
		}
		try {
			if(element[i - 1][j + 1].flag == true && element[i - 1][j + 1].mine != -1) {
				return false;
			}
		}catch(Exception ex) {
			
		}
		try {
			if(element[i][j + 1].flag == true && element[i][j + 1].mine != -1) {
				return false;
			}
		}catch(Exception ex) {
			
		}
		try {
			if(element[i + 1][j + 1].flag == true && element[i + 1][j + 1].mine != -1) {
				return false;
			}
		}catch(Exception ex) {
			
		}
		return true;
	}
	
	public static void checkaround(Element[][] ele) {
		for(int x = 0; x < ele.length; x++) {
			for(int y = 0; y < ele[x].length; y++) {
				if(ele[x][y].mine == 0) {
					try {
						if(ele[x - 1][y - 1].mine == -1) {
							ele[x][y].mine++;
						}
					}catch(Exception e) {
						
					}
					try {
						if(ele[x][y - 1].mine == -1) {
							ele[x][y].mine++;
						}
					}catch(Exception e) {
						
					}
					try {
						if(ele[x + 1][y - 1].mine == -1) {
							ele[x][y].mine++;
						}
					}catch(Exception e) {
						
					}
					try {
						if(ele[x - 1][y].mine == -1) {
							ele[x][y].mine++;
						}
					}catch(Exception e) {
						
					}
					try {
						if(ele[x + 1][y].mine == -1) {
							ele[x][y].mine++;
						}
					}catch(Exception e) {
						
					}
					try {
						if(ele[x - 1][y + 1].mine == -1) {
							ele[x][y].mine++;
						}
					}catch(Exception e) {
						
					}
					try {
						if(ele[x][y + 1].mine == -1) {
							ele[x][y].mine++;
						}
					}catch(Exception e) {
						
					}
					try {
						if(ele[x + 1][y + 1].mine == -1) {
							ele[x][y].mine++;
						}
					}catch(Exception e) {
						
					}
				}
			}
		}
	}
	//openaround±¸·Ý
	public static void openaround(Element[][] ele, int x, int y) {
		try {
			if(ele[x - 1][y].mine == 0 && ele[x - 1][y].passed == false) {
			//	ele[x - 1][y].button.setText("¡û");
				ele[x - 1][y].passed = true;
				ele[x - 1][y].button.setEnabled(false);
				openaround(ele, x - 1, y);
			}else if(ele[x - 1][y].mine == 0 && ele[x - 1][y].passed == true) {
				if(ele[x - 2][y].mine == 0 && ele[x - 2][y].button.isEnabled() == true) {
					openaround(ele, x - 1, y);
				}
			}else if(ele[x - 1][y].mine > 0 /*&& ele[x - 1][y].button.isEnabled() == true*/) {
				ele[x - 1][y].button.setText(String.valueOf(ele[x - 1][y].mine));
				ele[x - 1][y].button.setEnabled(false);
				try {
					if(ele[x - 1][y + 1].mine > 0) {
						ele[x - 1][y + 1].button.setText(String.valueOf(ele[x - 1][y + 1].mine));
					}
					if(ele[x - 1][y + 1].mine >= 0) {
						ele[x - 1][y + 1].button.setEnabled(false);
					}

				}catch(Exception ex) {
					
				}

				if(ele[x - 1][y - 1].mine > 0) {
					ele[x - 1][y - 1].button.setText(String.valueOf(ele[x - 1][y - 1].mine));
				}
				if(ele[x - 1][y - 1].mine >= 0) {
					ele[x - 1][y - 1].button.setEnabled(false);
				}
			}
		}catch(Exception e) {
			
		}

		try {
			if(ele[x][y - 1].mine == 0 && ele[x][y - 1].passed == false) {
		//		ele[x][y - 1].button.setText("¡ü");
				ele[x][y - 1].passed = true;
				ele[x][y - 1].button.setEnabled(false);
				openaround(ele, x, y - 1);
			}else if(ele[x][y - 1].mine == 0 && ele[x][y - 1].passed == true) {
				if(ele[x][y - 2].mine == 0 && ele[x][y - 2].button.isEnabled() == true) {
					openaround(ele, x, y - 1);
				}
			}else if(ele[x][y - 1].mine > 0 /*&& ele[x][y - 1].button.isEnabled() == true*/) {
				ele[x][y - 1].button.setText(String.valueOf(ele[x][y - 1].mine));
				ele[x][y - 1].button.setEnabled(false);
				try {
					if(ele[x + 1][y - 1].mine > 0) {
						ele[x + 1][y - 1].button.setText(String.valueOf(ele[x + 1][y - 1].mine));
					}
					if(ele[x + 1][y - 1].mine >= 0) {
						ele[x + 1][y - 1].button.setEnabled(false);
					}
				}catch(Exception ex) {
					
				}

				if(ele[x - 1][y - 1].mine > 0) {
					ele[x - 1][y - 1].button.setText(String.valueOf(ele[x - 1][y - 1].mine));
				}
				if(ele[x - 1][y - 1].mine >= 0) {
					ele[x - 1][y - 1].button.setEnabled(false);
				}
			}
		}catch(Exception e) {
			
		}
		try {
			if(ele[x + 1][y].mine == 0 && ele[x + 1][y].passed == false) {
		//		ele[x + 1][y].button.setText("¡ú");
				ele[x + 1][y].passed = true;
				ele[x + 1][y].button.setEnabled(false);
				openaround(ele, x + 1, y);
			}else if(ele[x + 1][y].mine == 0 && ele[x + 1][y].passed == true) {
				if(ele[x + 2][y].mine == 0 && ele[x + 2][y].button.isEnabled() == true) {
					openaround(ele, x + 1, y);
				}
			}else if(ele[x + 1][y].mine > 0 /*&& ele[x + 1][y].button.isEnabled() == true*/) {
				ele[x + 1][y].button.setText(String.valueOf(ele[x + 1][y].mine));
				ele[x + 1][y].button.setEnabled(false);
				try {
					if(ele[x + 1][y + 1].mine > 0) {
						ele[x + 1][y + 1].button.setText(String.valueOf(ele[x + 1][y + 1].mine));
					}
					if(ele[x + 1][y + 1].mine >= 0) {
						ele[x + 1][y + 1].button.setEnabled(false);
					}
				}catch(Exception ex) {
					
				}
				if(ele[x + 1][y - 1].mine > 0) {
					ele[x + 1][y - 1].button.setText(String.valueOf(ele[x + 1][y - 1].mine));
				}
				if(ele[x + 1][y - 1].mine >= 0) {
					ele[x + 1][y - 1].button.setEnabled(false);
				}
			}
		}catch(Exception e) {
			
		}
		try {
			if(ele[x][y + 1].mine == 0 && ele[x][y + 1].passed == false) {
		//		ele[x][y + 1].button.setText("¡ý");
				ele[x][y + 1].passed = true;
				ele[x][y + 1].button.setEnabled(false);
				openaround(ele, x, y + 1);
			}else if(ele[x][y + 1].mine == 0 && ele[x][y + 1].passed == true) {
				if(ele[x][y + 2].mine == 0 && ele[x][y + 2].button.isEnabled() == true) {
					openaround(ele, x, y + 1);
				}
			}else if(ele[x][y + 1].mine > 0 /*&& ele[x][y + 1].button.isEnabled() == true*/) {
				ele[x][y + 1].button.setText(String.valueOf(ele[x][y + 1].mine));
				ele[x][y + 1].button.setEnabled(false);
				try {
					if(ele[x + 1][y + 1].mine > 0) {
						ele[x + 1][y + 1].button.setText(String.valueOf(ele[x + 1][y + 1].mine));
					}
					if(ele[x + 1][y + 1].mine >= 0) {
						ele[x + 1][y + 1].button.setEnabled(false);
					}
				}catch(Exception ex) {
					
				}

				if(ele[x - 1][y + 1].mine > 0) {
					ele[x - 1][y + 1].button.setText(String.valueOf(ele[x - 1][y + 1].mine));
				}
				if(ele[x - 1][y + 1].mine >= 0) {
					ele[x - 1][y + 1].button.setEnabled(false);
				}
			}
		}catch(Exception e) {
			
		}
		
		try {
			if(ele[x - 1][y - 1].mine >= 0 && ele[x - 1][y - 1].passed == false) {
				if(ele[x - 1][y - 1].mine == 0) {
					ele[x - 1][y - 1].passed = true;
					openaround(ele, x - 1, y - 1);
				}else {
					ele[x - 1][y - 1].button.setText(String.valueOf(ele[x - 1][y - 1].mine));
					ele[x - 1][y - 1].button.setEnabled(false);
				}
			}
		}catch(Exception ex) {
			
		}
		
		try {
			if(ele[x + 1][y - 1].mine >= 0 && ele[x + 1][y - 1].passed == false) {
				if(ele[x + 1][y - 1].mine == 0) {
					ele[x + 1][y - 1].passed = true;
					openaround(ele, x + 1, y - 1);
				}else {
					ele[x + 1][y - 1].button.setText(String.valueOf(ele[x + 1][y - 1].mine));
					ele[x + 1][y - 1].button.setEnabled(false);
				}
			}
		}catch(Exception ex) {
			
		}
		
		try {
			if(ele[x - 1][y + 1].mine >= 0 && ele[x - 1][y + 1].passed == false) {
				if(ele[x - 1][y + 1].mine == 0) {
					ele[x - 1][y + 1].passed = true;
					openaround(ele, x - 1, y + 1);
				}else {
					ele[x - 1][y + 1].button.setText(String.valueOf(ele[x - 1][y + 1].mine));
					ele[x - 1][y + 1].button.setEnabled(false);
				}
			}
		}catch(Exception ex) {
			
		}
		
		try {
			if(ele[x + 1][y + 1].mine >= 0 && ele[x + 1][y + 1].passed == false) {
				if(ele[x + 1][y + 1].mine == 0) {
					ele[x + 1][y + 1].passed = true;
					openaround(ele, x + 1, y + 1);
				}else {
					ele[x + 1][y + 1].button.setText(String.valueOf(ele[x + 1][y + 1].mine));
					ele[x + 1][y + 1].button.setEnabled(false);
				}

			}
		}catch(Exception ex) {
			
		}

	}


	
}
