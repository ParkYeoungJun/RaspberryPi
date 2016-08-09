package frontdoor;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class FinedustPanel extends JPanel{

	public FinedustPanel(Dimension fulldim)
	{
		this.setBackground(Color.BLUE);
		this.setBounds(0, fulldim.height/2, fulldim.width/3, fulldim.height/2);
	}
	
}
