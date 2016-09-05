package refrigerator;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class ColdPanel extends JPanel{

	Dimension fulldim;
	
	public ColdPanel(Dimension fulldim)
	{
		this.fulldim = fulldim;
		
		this.setBounds(fulldim.width/2, fulldim.height/15, fulldim.width/2, fulldim.height/2+fulldim.height/15);
		
		this.setBackground(Color.BLUE);

		this.setVisible(true);
	}
	
}
