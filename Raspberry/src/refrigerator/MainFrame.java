package refrigerator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
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
	
	// Plus icon
	ImageIcon plusicon;
	JLabel pluslabel;
	boolean plusflag = true;
	
	// MenuPanel
	MenuPanel menupanel;
	
	// PlusPanel
	PlusPanel pluspanel;
	
	
	// summary panel
	FreezePanel s_freezepanel;
	ColdPanel s_coldpanel;
	PressPanel s_presspanel;
	
	int bound_x = 0;
	Timer timer;
	
	int from = -fulldim.width/4;
	int to = 0;


	
	
	public MainFrame()
	{
		this.setTitle("");
		this.setLayout(null);
		this.setBounds(0, 0, fulldim.width, fulldim.height);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//		this.setUndecorated(true);
		
		
		menupanel = new MenuPanel(fulldim, this);
		this.add(menupanel);
		
		
		/*
		 * date panel
		 */
		
		ceilingpanel = new JPanel();
		ceilingpanel.setLayout(null);
		ceilingpanel.setBounds(0, 0, fulldim.width, fulldim.height/15);
		ceilingpanel.setBackground(Color.BLUE);
		date = new JLabel("",JLabel.CENTER);
		date.setBounds(0,0, fulldim.width-50, fulldim.height/15);
		date.setFont(new Font(null,10,20));
		ceilingpanel.add(date);		
		
		/*
		 * 
		 */
		
		
		/*
		 *  menu, plus button
		 */
		
		try
		{
			menuicon = new ImageIcon("icon/menu_icon.png");
			menulabel = new JLabel();
			
			plusicon = new ImageIcon("icon/PlusButton.png");
			pluslabel = new JLabel();
			
			
			// menu
			Image img;
	
			img = menuicon.getImage();
			menulabel.setIcon(new ImageIcon(img.getScaledInstance(fulldim.width/30, fulldim.height/20, Image.SCALE_SMOOTH)));
			menulabel.setBounds(0,10,fulldim.width/30,fulldim.height/20);
			ceilingpanel.add(menulabel);
			
			// plus
			img = plusicon.getImage();
			pluslabel.setIcon(new ImageIcon(img.getScaledInstance(fulldim.width/35, fulldim.height/20, Image.SCALE_SMOOTH)));
			pluslabel.setBounds(fulldim.width-fulldim.width/21, fulldim.height-fulldim.height/6, fulldim.width/35, fulldim.height/15);
			this.add(pluslabel);
			
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
		
		/*
		 * 
		 */
	
		/*
		 *  listeners
		 */
		
		menulabel.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e)
			{
				
				panels_enable(false);
				
				menupanel.setLocation(from, 0);
				
				menupanel.setSize(fulldim.width/4, fulldim.height);
				
				timer = new Timer(5, new ActionListener(){
					public void actionPerformed(ActionEvent ae){
						
						if(from < to)
						{
							from = from+10;
							menupanel.setLocation(from, 0);
							menupanel.repaint();
						}
						else
						{
							from = -fulldim.width/4;
							to = 0;
							
							timer.stop();
						}
					}
				});
				
				timer.start();
			}
		});
		
		pluslabel.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if(plusflag)
				{
					MainFrame.this.setEnabled(false);
			
					pluspanel = new PlusPanel(fulldim, MainFrame.this);
				}			
			}
		});
		
		/*
		 * 
		 */
		
		
		this.add(ceilingpanel);
		
		/*
		 *	  Initial summary panels
		 */
		
		s_freezepanel = new FreezePanel(fulldim);
		s_coldpanel = new ColdPanel(fulldim);
		s_presspanel = new PressPanel(fulldim);
	
	
		this.add(s_freezepanel);
		this.add(s_coldpanel);
		this.add(s_presspanel);
	
		
		/*
		 * 
		 */
		
		update();

		this.setVisible(true);
	}
	
	public void panels_enable(boolean flag)
	{
		plusflag = flag;
	}
	
	public void update()
	{
		// get date
		date_object = new Date();
		today = format.format(date_object);
		today += " "+day[date_object.getDay()];
				
		date.setText("<html><font color = #FFFFFFF>"+today+"</font><html>");
	}

	public static void main(String[] args)
	{
		MainFrame temp = new MainFrame();
	}
}
