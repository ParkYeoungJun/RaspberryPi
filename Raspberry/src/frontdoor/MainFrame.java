package frontdoor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainFrame extends JFrame{
	
	// get screen size
	private Dimension fulldim = Toolkit.getDefaultToolkit().getScreenSize();
	
	// panels
	JPanel weather;
	JPanel finedust;
	JPanel note;
	JPanel date;
	
	// JLabel in date panel
	JLabel date_label;
	
	// weather panel
	WeatherPanel weather_panel;
	
	// dust panel
	FinedustPanel dust_panel;
	
	
	// for get date
	Date date_object;
	String[] day = {"일요일","월요일","화요일","수요일","목요일","금요일","토요일"};
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	String today;
	
	
	// Black Frame
	JFrame screensaver = new JFrame();
	JPanel tempsaver = new JPanel();
	
	
	// weather and Finedust parsing object
	Weather_Parsing weatherinfo = new Weather_Parsing();
//	Finedust_Parsing dustinfo;


	
	@SuppressWarnings("deprecation")
	public MainFrame()
	{
		this.setTitle("");
		this.setLayout(null);

		
		// full Screen
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		this.setUndecorated(true);
		gd.setFullScreenWindow(this);
		

		// screensave screen initial
		screensaver.setBounds(0,0,fulldim.width,fulldim.height);
		tempsaver.setBounds(0, 0, fulldim.width, fulldim.height);
		tempsaver.setBackground(Color.BLACK);
		screensaver.add(tempsaver);
		screensaver.setUndecorated(true);
		

		// get date
		date_object = new Date();
		today = format.format(date_object);
		today += " "+day[date_object.getDay()];

		
		// Initial date panel
		date = new JPanel();
		date.setBounds(0, 0, fulldim.width, fulldim.height/15);
		date.setBackground(Color.BLUE);
		date_label = new JLabel(today);
		date_label.setBounds(fulldim.width/2-100, 20, fulldim.width, 30);
		date_label.setFont(new Font(null,10,30));
		date_label.setForeground(Color.white);;
		date.add(date_label);
		this.add(date);
		
		// weather
//		dustinfo = new Finedust_Parsing();
		weather_panel = new WeatherPanel(fulldim, weatherinfo);
		this.add(weather_panel);
		
		
//		// dust
//		dust_panel = new FinedustPanel(fulldim);
//		this.add(dust_panel);
	
		
//		if(PRI Sensor detect)
//		{
//			update();
//			
//			this.setVisible(true);
//		}
//		else
//		{
//			try {
//				Thread.sleep(3000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			this.setVisible(false);
//			
//			screensaver.setVisible(true);
//		}
		
		
		
		this.setVisible(true);
	}
	
	
	public void update()
	{
//		// get date
//		date_object = new Date();
//		today = format.format(date_object);
//		today += " "+day[date_object.getDay()];
//		
//		weather_panel = new WeatherPanel(fulldim);
	}
	
	
	public static void main(String[] args)
	{
		MainFrame main = new MainFrame();
	}

}
