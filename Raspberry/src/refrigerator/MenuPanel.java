package refrigerator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MenuPanel extends JPanel{

	Dimension fulldim;
	
	MainFrame mainframe;
	
	ImageIcon xicon;
	JLabel xlabel;
	
	Timer timer;
	
	int from;
	int to;
	
	public MenuPanel(Dimension fulldim, MainFrame mainframe)
	{
		this.fulldim = fulldim;
		this.mainframe = mainframe;
		
		from = 0;
		to = -fulldim.width/4;
		
		this.setBackground(Color.WHITE);
		this.setLayout(null);

		
		try
		{
		
			Image img;
			
			xicon = new ImageIcon(new URL("http://i.imgur.com/yYe29Ya.png"));
			xlabel = new JLabel();
		
			img = xicon.getImage();
			xlabel.setIcon(new ImageIcon(img.getScaledInstance(fulldim.width/40, fulldim.height/25, Image.SCALE_SMOOTH)));
			xlabel.setBounds(fulldim.width/4-fulldim.width/35, 0, fulldim.width/40, fulldim.height/25);			
			xlabel.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e)
				{
					mainframe.panels_enable(true);
					
					timer = new Timer(5, new ActionListener(){
						public void actionPerformed(ActionEvent ae){

							if(from > to)
							{
								from = from-10;
								MenuPanel.this.setLocation(from, 0);
								MenuPanel.this.repaint();
							}
							else
							{
								from = 0;
								to = -fulldim.width/4;
																
								timer.stop();
							}							
						}
					});
					
					timer.start();
				}
			});
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
		
		this.add(xlabel);
		
		this.setVisible(true);
	}
}