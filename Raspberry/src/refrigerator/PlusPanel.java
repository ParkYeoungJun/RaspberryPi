package refrigerator;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PlusPanel extends JFrame{
	
	JFrame mainframe;
	
	Dimension fulldim;
	
	
	
	public PlusPanel(Dimension fulldim, JFrame mainframe)
	{
		this.fulldim = fulldim;
		this.mainframe = mainframe;
		
		this.setTitle("Add");
		this.setLayout(null);
		this.setBounds(fulldim.width/3, fulldim.height/3, fulldim.width/3, fulldim.height/3-fulldim.height/15);
		
		
		this.setVisible(true);
		
		this.addWindowListener(new java.awt.event.WindowAdapter(){
			
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent)
			{
				mainframe.setEnabled(true);
			}
			
		});	
	}
}
