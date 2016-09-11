package refrigerator;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class MarinePanel extends JPanel{

public MarinePanel(int x, int y){
		
		this.setBounds(x, 0, x, y);
		this.setLayout(new GridLayout(3,2));
		
		
		this.setVisible(true);
		
	}
	
}
