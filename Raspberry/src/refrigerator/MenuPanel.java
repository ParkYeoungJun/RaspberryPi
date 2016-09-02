package refrigerator;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class MenuPanel extends JPanel{

	Dimension fulldim;
		
	int bound_x;
	
	public MenuPanel(Dimension fulldim)
	{
		this.fulldim = fulldim;

		bound_x = -fulldim.width/5;
		
//		this.setBounds(-fulldim.width/5, fulldim.height-fulldim.height/15, fulldim.width/5, fulldim.height);
//		this.setLocation(0, 0);
//		this.setSize(fulldim.width/5, fulldim.height);
		this.setBackground(Color.BLACK);
		
		this.setVisible(true);
	}
	
//	public void appear()
//	{
//		AnimateClass temp = new AnimateClass();
//		temp.start();
//	}
//	
//	private class AnimateClass extends Thread
//	{
//		public void run()
//		{
//			
//			while(true)
//			{
//				MenuPanel.this.setBounds(bound_x, fulldim.height-fulldim.height/15, fulldim.width/5, fulldim.height);
//		
//				bound_x++;
//				
//				if(bound_x == fulldim.width/5)
//					break;
//			}
//		}
//	}
	
}
