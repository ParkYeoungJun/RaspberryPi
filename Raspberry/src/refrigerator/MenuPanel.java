package refrigerator;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class MenuPanel extends JPanel{

	Dimension fulldim;
	
	public MenuPanel(Dimension fulldim)
	{
		this.fulldim = fulldim;
		
		this.setBackground(Color.BLACK);
		
		this.setVisible(true);
	}
}
