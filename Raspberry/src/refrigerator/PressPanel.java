package refrigerator;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class PressPanel extends JPanel{
	
	Dimension fulldim;
	
	public PressPanel(Dimension fulldim)
	{
		this.fulldim = fulldim;
		
		this.setBounds(0, fulldim.height/15 + fulldim.height/2, fulldim.width, fulldim.height - (fulldim.height/15+fulldim.height/2));

		this.setBackground(Color.GRAY);
		
		this.setVisible(true);
		
	}
}
