package refrigerator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class MainFrame extends JFrame
{
	private Dimension fulldim = Toolkit.getDefaultToolkit().getScreenSize();


	public MainFrame()
	{
		this.setBounds(0, 0, fulldim.width, fulldim.height);
		this.setUndecorated(true);
		this.setBackground(Color.WHITE);
		
		this.setVisible(true);
	}

	public static void main(String[] args)
	{
		MainFrame temp = new MainFrame();
	}
}
