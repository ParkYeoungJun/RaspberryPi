package refrigerator;

import java.awt.Dimension;

import javax.swing.JFrame;

public class CookingPanel extends JFrame{
	
	Dimension fulldim;
	
	public CookingPanel(Dimension fulldim, MainFrame mainframe)
	{
		this.fulldim = fulldim;
		
		this.setTitle("���� ��õ�丮!!");
		this.setBounds(fulldim.width/7, fulldim.height/10, fulldim.width-(fulldim.width*2)/7, fulldim.height/2+fulldim.height/3);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
	
		
		
		
		this.setVisible(true);
	}

}
