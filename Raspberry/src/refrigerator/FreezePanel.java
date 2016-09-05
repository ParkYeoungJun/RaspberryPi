package refrigerator;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class FreezePanel extends JPanel{

	Dimension fulldim;
	
	public FreezePanel(Dimension fulldim)
	{
		this.fulldim = fulldim;
		
		this.setBounds(0, fulldim.height/15, fulldim.width/2, fulldim.height/2+fulldim.height/15);

		this.setBackground(Color.BLACK);
		
		this.setVisible(true);
		
	}
	
}
