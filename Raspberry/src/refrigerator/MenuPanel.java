package refrigerator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
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
	ImageIcon hanulcon;
	ImageIcon background;
	JLabel xlabel;
	JLabel background_label;
	
	JLabel settingbutton;
	JLabel profile_icon;
	JLabel profile_text;
	
	
	Timer timer;
	
	int from;
	int to;
	
	public MenuPanel(Dimension fulldim, MainFrame mainframe)
	{
		this.fulldim = fulldim;
		this.mainframe = mainframe;
				
		from = 0;
		to = -fulldim.width/4;
		
		this.setLayout(null);

		
		try
		{

			/*
			 *  x icon
			 */
			
			Image img;
			
			xicon = new ImageIcon("icon/xicon.png");
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
			
			/*
			 * 
			 */
			
			
			
			/*
			 *  profile
			 */
			
			hanulcon = new ImageIcon("icon/hanul.png");
			profile_icon = new JLabel();
			
			img = hanulcon.getImage();
			profile_icon.setIcon(new ImageIcon(img.getScaledInstance(fulldim.width/7, fulldim.height/4, Image.SCALE_SMOOTH)));
			profile_icon.setBounds(fulldim.width/4-fulldim.width/5+fulldim.width/100, fulldim.height/10, fulldim.width/7, fulldim.height/4);			
			profile_icon.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e)
				{
					JFrame good = new JFrame();
					good.setBounds(fulldim.width/3, fulldim.height/4, 460, 618);
					good.setTitle("Our Picture");
					JLabel goodlabel = new JLabel();
					goodlabel.setSize(460,618);
					ImageIcon goodicon;
					
					Image goodimage;
					try
					{
						goodicon = new ImageIcon("icon/hanul_big.png");
						goodlabel.setIcon(goodicon);
						
						good.add(goodlabel);
					}
					catch(Exception er)
					{
						System.err.println(er);
					}
					
					good.setVisible(true);
										
				}
				
			});
			this.add(profile_icon);
			
			profile_text = new JLabel("»Ø»Ø»Ø", JLabel.CENTER);
			profile_text.setFont(new Font(null,10,20));
			profile_text.setBounds(0, fulldim.height/3+fulldim.height/20, fulldim.width/4, 30);
			this.add(profile_text);
			
			settingbutton = new JLabel("<html><font color = #000000>--------------------------------------------------------------------<br>  Setting<br>--------------------------------------------------------------------</font></html>", JLabel.LEFT);
			settingbutton.setFont(new Font(null, 10, 20));
			settingbutton.setBounds(0,fulldim.height/2, fulldim.width/4, 70);
			this.add(settingbutton);
			
			/*
			 * 
			 */
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
		
		this.add(xlabel);
		
		this.setVisible(true);
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Image img;

		try
		{
			background = new ImageIcon("icon/menupanel_background.png");
			img = background.getImage();

//			g.drawImage(img.getScaledInstance(fulldim.width/4, fulldim.height-fulldim.height/15, Image.SCALE_SMOOTH), 0,0,this.getWidth(),this.getHeight(),this);
			g.drawImage(img, 0,0,this.getWidth(),this.getHeight(),this);

		}
		catch(Exception e)
		{
			System.err.println(e);
		}
	}
}