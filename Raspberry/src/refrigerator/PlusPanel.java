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
		this.setBounds(fulldim.width/7, fulldim.height/10, fulldim.width/2+fulldim.width/4, fulldim.height/2+fulldim.height/3);
		
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
