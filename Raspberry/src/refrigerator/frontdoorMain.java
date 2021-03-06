package refrigerator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Callable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.trigger.GpioCallbackTrigger;

import refrigerator.MainFrame;

public class frontdoorMain extends JFrame{
	
	// get screen size
	private Dimension fulldim = Toolkit.getDefaultToolkit().getScreenSize();
	
	// panels
	JPanel weather;
	JPanel finedust;
	JPanel note;
	JPanel date;
	
	// JLabel in date panel
	JLabel date_label;
	JLabel menulabel;
	ImageIcon menuicon;
	
	// weather panel
	WeatherPanel weather_panel;
	NotePanel note_panel;
	
	// for get date
	Date date_object;
	String[] day = {"일요일","월요일","화요일","수요일","목요일","금요일","토요일"};
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	String today;
	
	
	// Black Frame
	JFrame screensaver = new JFrame();
	JPanel tempsaver = new JPanel();
	
	
	// weather and Finedust parsing object
//	Weather_Parsing weatherinfo = new Weather_Parsing();
//	Finedust_Parsing dustinfo;
	
	BufferedImage img;
	
	Calendar current;
	
	int from = -fulldim.width/4;
    int to = 0;
    
    MenuPanel menupanel;
    Timer timer;
	
    MainFrame mainframe;
    
    JLabel mainlabel;
    ImageIcon mainicon;
    
	@SuppressWarnings("deprecation")
	public frontdoorMain(MainFrame mainframe)
	{
		this.mainframe = mainframe;
		
		current = Calendar.getInstance();
		
		this.setTitle("");
		this.setLayout(null);
		this.setBounds(0,0,fulldim.width, fulldim.height);	
		// full Screen
		this.setUndecorated(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		try {
			img = ImageIO.read(new File("icon/frontbackground.png"));
		
			this.setContentPane(new ImagePanel(img));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
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
		date.setOpaque(false);
		date.setLayout(null);
		date_label = new JLabel(today);
		date_label.setBounds(fulldim.width/2-100, 0, fulldim.width, fulldim.height/15);
		date_label.setFont(new Font(null,Font.BOLD,20));
		date_label.setForeground(Color.white);;
		date.add(date_label);
		
		mainicon = new ImageIcon("icon/refrigerator.png");
		mainlabel = new JLabel();
		
		Image img;
		
		img = mainicon.getImage();
		mainlabel.setIcon(new ImageIcon(img.getScaledInstance(fulldim.width/28, fulldim.height/20, Image.SCALE_SMOOTH)));
		mainlabel.setBounds(0,0,fulldim.width/28,fulldim.height/20);
		
		
		mainlabel.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e)
			{
				frontdoorMain.this.setVisible(false);
				mainframe.setVisible(true);
			}
		});
		
		
		date.add(mainlabel);
		
		
		
		// menu
				
		this.add(date);
		
		// weather
		weather_panel = new WeatherPanel(fulldim);
		this.add(weather_panel);
		
		note_panel = new NotePanel(fulldim);
		this.add(note_panel);
		
		updateThread up = new updateThread();
		up.start();
		
		try {
			Process d = Runtime.getRuntime().exec("xset dpms force off");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		sensorThread sensor = new sensorThread();
//		sensor.start();
		
		
		this.setVisible(true);
	}
	
	class updateThread extends Thread
	{
		public void run()
		{
			while(true)
			{
				update();
				
				try {
					Thread.sleep(60000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	Calendar temp;
	
	class dropthis extends Thread
	{
		public void run()
		{
			
			while(true)
			{
				
				temp = Calendar.getInstance();
						
				if(temp.getTimeInMillis() - current.getTimeInMillis() > 10000)
				{
					try {
						Process d = Runtime.getRuntime().exec("xset dpms force off");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				}
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
				}
			}
		}
	}
	
	dropthis dp = new dropthis();
	
	boolean running;
	
	
	class sensorThread extends Thread 
	{
		public void run()
		{
			running = false;
			
			final GpioController gpio = GpioFactory.getInstance();
			
			final GpioPinDigitalInput pir = gpio.provisionDigitalInputPin(RaspiPin.GPIO_29);
			final GpioPinDigitalInput off = gpio.provisionDigitalInputPin(RaspiPin.GPIO_28);
		
			// create a gpio callback trigger on the gpio pin
			Callable<Void> callback = () -> {
	        	
				Process d = Runtime.getRuntime().exec("xset dpms force on");
				
				current = Calendar.getInstance();
				
				System.out.println("out");
						 
				return null;
					
			};
				
			Callable<Void> turnoff = () ->{
				
				if(!running)
				{
					dp.start();
					running = true;
				}
				
				return null;
					
			};
				
	        
			// create a gpio callback trigger on the PIR device pin for when it's state goes high
			pir.addTrigger(new GpioCallbackTrigger(PinState.HIGH, callback));
				
			off.addTrigger(new GpioCallbackTrigger(PinState.LOW, turnoff));

	 
			// stop all GPIO activity/threads by shutting down the GPIO controller
			Runtime.getRuntime().addShutdownHook(new Thread() {
				@Override
				public void run() {
					System.out.println("Interrupted, stopping...\n");
					gpio.shutdown();
				}
			});
	 
			// keep program running until user aborts (CTRL-C)	
			try {
				while(true)
				{
					Thread.sleep(100);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	public void update()
	{
		// get date
		date_object = new Date();
		today = format.format(date_object);
		today += " "+day[date_object.getDay()];
		date_label.setText(today);
//		
//		weather_panel = new WeatherPanel(fulldim);
	}
	
	class ImagePanel extends JComponent {
	    private Image image;
	    public ImagePanel(Image image) {
	        this.image = image;
	    }
	    @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(),this);
	    }
	}
}
