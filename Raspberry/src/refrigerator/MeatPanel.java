package refrigerator;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class MeatPanel extends JPanel{

	public MeatPanel(int x, int y){
		
		this.setBounds(x, 0, x, y);
		this.setLayout(new GridLayout(3,2));
		this.setBackground(Color.WHITE);
		
		this.setVisible(true);
		
	}
	
}
