package refrigerator;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class MainFrame extends JFrame{

	Dimension fulldim = Toolkit.getDefaultToolkit().getScreenSize();
	
	public MainFrame()
	{
		this.setTitle("");
		this.setLayout(null);
		this.setBounds(0, 0, fulldim.width, fulldim.height);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		//this.isUndecorated(true);
		
		this.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		MainFrame temp = new MainFrame();
	}
}
