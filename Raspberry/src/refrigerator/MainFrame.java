package refrigerator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MainFrame extends JFrame{

	Dimension fulldim = Toolkit.getDefaultToolkit().getScreenSize();
	
	// get date
	Date date_object;
	String[] day = {"일요일","월요일","화요일","수요일","목요일","금요일","토요일"};
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	String today;
	
	// ceilingpanel
	JPanel ceilingpanel;
	JLabel date;
	
	// menu icon
	ImageIcon menuicon;
	JLabel menulabel;
	
	// MenuPanel
	MenuPanel menupanel;
	
	
	int bound_x = 0;
	Timer timer;

	
	
	public MainFrame()
	{
		this.setTitle("");
		this.setLayout(null);
		this.setBounds(0, 0, fulldim.width, fulldim.height);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		//this.isUndecorated(true);
		
		
		menupanel = new MenuPanel(fulldim);
		this.add(menupanel);
		
		
		ceilingpanel = new JPanel();
		ceilingpanel.setLayout(null);
		ceilingpanel.setBounds(0, 0, fulldim.width, fulldim.height/15);
		ceilingpanel.setBackground(Color.WHITE);
		date = new JLabel("",JLabel.CENTER);
		date.setBounds(0,0, fulldim.width-50, fulldim.height/15);
		date.setFont(new Font(null,10,20));
		ceilingpanel.add(date);		
		// menu button
		try
		{
			menuicon = new ImageIcon(new URL("http://i.imgur.com/FKCvv3e.png"));
			menulabel = new JLabel();
			
			Image img;
			
			img = menuicon.getImage();
			
			menulabel.setIcon(new ImageIcon(img.getScaledInstance(fulldim.width/30, fulldim.height/20, Image.SCALE_SMOOTH)));
			
			menulabel.setBounds(0,10,fulldim.width/30,fulldim.height/20);
		
			ceilingpanel.add(menulabel);
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
	
		
		menulabel.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e)
			{
//				AppearAnimate temp = new AppearAnimate();
//				temp.run();
//				
//				
//				
//				while(true)
//				{
//					menupanel.setLocation(++bound_x, fulldim.height/15);
//			
//					System.out.println(bound_x);
//
//					menupanel.repaint();
//					menupanel.revalidate();
//					MainFrame.this.repaint();
//					MainFrame.this.revalidate();
//					
//					if(bound_x > fulldim.width/5)
//						break;
//					
//					try
//					{
//						Thread.sleep(10);
//					}
//					catch(Exception er)
//					{
//						System.err.println(er);
//					}
//				}		
			}
		});
	
				
		this.add(ceilingpanel);

		
		
		update();
		
		this.setVisible(true);
	}
	
	public void update()
	{
		// get date
		date_object = new Date();
		today = format.format(date_object);
		today += " "+day[date_object.getDay()];
				
		date.setText(today);
	}
	
	class AppearAnimate extends Thread
	{
		int bound_x = 0;
		
		public void run()
		{
			while(true)
			{
				menupanel.setLocation(++bound_x, fulldim.height/15);
			
								
//				menupanel.setSize(++bound_x, fulldim.height);
				
				System.out.println(bound_x);

				menupanel.repaint();
				
				if(bound_x > fulldim.width/5)
					break;
				
				try
				{
					Thread.sleep(10);
				}
				catch(Exception e)
				{
					System.err.println(e);
				}
			}
		}
	}
	
	public void repain()
	{
		this.repaint();
	}
	
	public static void main(String[] args)
	{
		MainFrame temp = new MainFrame();
	}
}
