package frontdoor;

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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.trigger.GpioCallbackTrigger;

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

	
	@SuppressWarnings("deprecation")
	public MainFrame()
	{
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
		date_label = new JLabel(today);
		date_label.setBounds(fulldim.width/2-100, 20, fulldim.width, 30);
		date_label.setFont(new Font(null,Font.BOLD,20));
		date_label.setForeground(Color.white);;
		date.add(date_label);
		this.add(date);
		
		// weather
		weather_panel = new WeatherPanel(fulldim);
		this.add(weather_panel);
		
		note_panel = new NotePanel(fulldim);
		this.add(note_panel);
		
		updateThread up = new updateThread();
		up.start();
		
		sensorThread sensor = new sensorThread();
		sensor.start();
		
		
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
	
	
	class sensorThread extends Thread 
	{
		public void run()
		{
		
			while(true)
			{
				// create gpio controller
				final GpioController gpio = GpioFactory.getInstance();
	        
				// provision gpio pin #29, (header pin 40) as an input pin with its internal pull down resistor enabled
				final GpioPinDigitalInput pir = gpio.provisionDigitalInputPin(RaspiPin.GPIO_29);
				System.out.printf("Ready\n");
	 
				// create a gpio callback trigger on the gpio pin
				Callable<Void> callback = () -> {
	        	
					Process d = Runtime.getRuntime().exec("xset dpms force on");
					            
					return null;
				};
	        
				// create a gpio callback trigger on the PIR device pin for when it's state goes high
				pir.addTrigger(new GpioCallbackTrigger(PinState.HIGH, callback));
	 
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
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
	
	public static void main(String[] args)
	{
		MainFrame main = new MainFrame();
	}

}
