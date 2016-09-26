package refrigerator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
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
import javax.swing.SwingWorker;
import javax.swing.Timer;

import org.json.simple.JSONObject;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.trigger.GpioCallbackTrigger;

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
	
	
	Database db;
	
	FoodParsing fooddata;
	
	boolean alarmflag = true;

	int k  = 0;
	
	BufferedImage img;

	frontdoorMain frontdoor;

	ImageIcon fronticon;
	JLabel frontlabel;
	
	ImageIcon alarmicon;
	JLabel alarmlabel;

	ImageIcon cookicon;
	JLabel cooklabel;
	
	public MainFrame()
	{
		this.setTitle("");
		this.setLayout(null);
		this.setBounds(0, 0, fulldim.width, fulldim.height);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setUndecorated(true);
		
		frontdoor = new frontdoorMain(this);
		frontdoor.setVisible(false);
		
		try {
			img = ImageIO.read(new File("icon/white.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.setContentPane(new ImagePanel(img));
		
		db = new Database();
		fooddata = new FoodParsing();
		
//		menupanel = new MenuPanel(fulldim, MainFrame.this, frontdoor);
//		menupanel.setVisible(false);
//		this.add(menupanel);
		
		
		/*
		 * date panel
		 */
		
		ceilingpanel = new JPanel();
		ceilingpanel.setLayout(null);
		ceilingpanel.setBounds(0, 0, fulldim.width, fulldim.height/15);
		ceilingpanel.setBackground(Color.DARK_GRAY);
		date = new JLabel("",JLabel.CENTER);
		date.setBounds(0,0, fulldim.width-50, fulldim.height/15);
		date.setFont(new Font(null,Font.BOLD,20));
		ceilingpanel.add(date);		
		
		/*
		 * 
		 */
		
		
		/*
		 *  menu, plus button
		 */
		
		try
		{
//			menuicon = new ImageIcon("icon/menu_icon.png");
//			menulabel = new JLabel();
			
			plusicon = new ImageIcon("icon/PlusButton.png");
			pluslabel = new JLabel();
			
			fronticon = new ImageIcon("icon/frontdoor.png");
			frontlabel = new JLabel();
			
			alarmicon = new ImageIcon("icon/alarmon.png");
			alarmlabel = new JLabel();

			cookicon = new ImageIcon("icon/cooking.png");
			cooklabel = new JLabel();
			
			// menu
			Image img;
	
//			img = menuicon.getImage();
//			menulabel.setIcon(new ImageIcon(img.getScaledInstance(fulldim.width/30, fulldim.height/22, Image.SCALE_SMOOTH)));
//			menulabel.setBounds(0,10,fulldim.width/30,fulldim.height/22);
//			ceilingpanel.add(menulabel);
			
			// plus
			img = plusicon.getImage();
			pluslabel.setIcon(new ImageIcon(img.getScaledInstance(fulldim.width/35, fulldim.height/15, Image.SCALE_SMOOTH)));
			pluslabel.setBounds(fulldim.width-fulldim.width/21, 0, fulldim.width/35, fulldim.height/15);
			ceilingpanel.add(pluslabel);
			
			img = fronticon.getImage();
			frontlabel.setIcon(new ImageIcon(img.getScaledInstance(fulldim.width/30, fulldim.height/22, Image.SCALE_SMOOTH)));
			frontlabel.setBounds(5,12,fulldim.width/30,fulldim.height/22);
			ceilingpanel.add(frontlabel);
			
			img = cookicon.getImage();
			cooklabel.setIcon(new ImageIcon(img.getScaledInstance(fulldim.width/30, fulldim.height/22, Image.SCALE_SMOOTH)));
			cooklabel.setBounds(fulldim.width/30,10,fulldim.width/30,fulldim.height/22);
			ceilingpanel.add(cooklabel);	
			
			img = alarmicon.getImage();
			alarmlabel.setIcon(new ImageIcon(img.getScaledInstance(fulldim.width/50, fulldim.height/30, Image.SCALE_SMOOTH)));
			alarmlabel.setBounds(fulldim.width/30+fulldim.width/30,13,fulldim.width/50,fulldim.height/30);
			ceilingpanel.add(alarmlabel);	
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
		
		frontlabel.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e)
			{
				MainFrame.this.setVisible(false);
				frontdoor.setVisible(true);
			}
		});
		
		cooklabel.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e)
			{
				CookingPanel cook = new CookingPanel(fulldim, MainFrame.this);
			}
		});
		
		alarmlabel.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if(alarmflag)
				{
					alarmicon = new ImageIcon("icon/alarmoff.png");
					
					Image img;
					
					img = alarmicon.getImage();
					alarmlabel.setIcon(new ImageIcon(img.getScaledInstance(fulldim.width/50, fulldim.height/30, Image.SCALE_SMOOTH)));
					
					setalarmflag(false);
				}
				else
				{
					alarmicon = new ImageIcon("icon/alarmon.png");
					
					Image img;
					
					img = alarmicon.getImage();
					alarmlabel.setIcon(new ImageIcon(img.getScaledInstance(fulldim.width/50, fulldim.height/30, Image.SCALE_SMOOTH)));
					
					setalarmflag(true);
				}
			}
		});
		
//		menulabel.addMouseListener(new MouseAdapter(){
//			@Override
//			public void mouseClicked(MouseEvent e)
//			{
//				
//				menupanel.setVisible(true);
//
//				MainFrame.this.setEnabled(false);
//				
//				panels_enable(false);
//								
//				
////				menupanel.setLocation(from, 0);
//
//				
//				timer = new Timer(1, new ActionListener(){
//					public void actionPerformed(ActionEvent ae){
//						
//						if(from < to)
//						{
//							from = from+30;
//							menupanel.setLocation(from, 0);
//							menupanel.repaint();
//							
//						}
//						else
//						{
//							menupanel.setLocation(0, 0);
//							menupanel.repaint();
//
//							from = -fulldim.width/4;
//							to = 0;
//							
//							timer.stop();
//						}
//					}
//				});
//				
//				timer.start();
//			}
//		});
		
		pluslabel.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if(plusflag)
				{
					MainFrame.this.setEnabled(false);
			
					pluspanel = new PlusPanel(fulldim, MainFrame.this, db);
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
		
		s_freezepanel = new FreezePanel(fulldim, this);
		s_coldpanel = new ColdPanel(fulldim, this);
		s_presspanel = new PressPanel(fulldim);
	
	
		this.add(s_freezepanel);
		this.add(s_coldpanel);
		this.add(s_presspanel);
	
		
		/*
		 * 
		 */
		
		update();
		
		timeThread timethread = new timeThread();
		timethread.start();
		
		try {
			Process d = Runtime.getRuntime().exec("xset dpms force off");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		sensorThread sensor = new sensorThread();
		sensor.start();
				
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
		
		s_freezepanel = new FreezePanel(fulldim, this);
		s_coldpanel = new ColdPanel(fulldim, this);
		
	}
	
	public void setalarmflag(boolean flag)
	{
		alarmflag = flag;
	}
	public boolean getalarmflag()
	{
		return alarmflag;
	}
	
	public void InsertData(String group, String purchase_date, String name, int image_num
			, String shelf_life, int num, int position)
	{
		  class Insert extends SwingWorker {

				@Override
				protected Object doInBackground() throws Exception {
				
			            try {

			                String uri = "http://52.78.88.182/insertFood.php";
			                JSONObject jsonObj = new JSONObject();
			            
			                System.out.println(group);
			                System.out.println(name);

			                jsonObj.put("group", group);
			                jsonObj.put("purchase_date", purchase_date);
			                jsonObj.put("name", name);
			                jsonObj.put("image_num", image_num);
			                jsonObj.put("shelf_life", shelf_life);
			                jsonObj.put("num", num);
			                jsonObj.put("position", position);

			                BufferedWriter bufferedWriter = null;
			                URL url = new URL(uri);
			                HttpURLConnection con = (HttpURLConnection) url.openConnection();
			                StringBuilder sb = new StringBuilder();
			                con.setDoOutput(true);
			                con.setDoInput(true);
			                
			                String data ="&" + URLEncoder.encode("data", "UTF-8") + "="+ URLEncoder.encode(jsonObj.toString(), "UTF-8");
			                
			                System.out.println(data);
			                
			                OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
			                
			           
			                wr.write(data);
			                wr.flush();
			                
			                BufferedReader reader=new BufferedReader(new InputStreamReader(con.getInputStream()));
			                String line=null;
			                while((line=reader.readLine())!=null){
			                    //서버응답값을 String 형태로 추가함
			                	System.out.println(line);
			                }
			           
			                return sb.toString().trim();
			                
			           
			            }catch(Exception e){
			            	System.out.println(e);
			            	return null;
			            }
			        }

			}
		  
		  Insert g = new Insert();
		  g.execute();   
	}
	
	public void DeleteData(String id)
	{
	    class Delete extends SwingWorker {

			@Override
			protected Object doInBackground() throws Exception {

                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL("http://52.78.88.182/deleteFood.php?id=" + id);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while((json = bufferedReader.readLine())!= null){
                        sb.append(json+"\n");
                    }
                    return sb.toString().trim();

                }catch(Exception e){
                    return null;
                }
            }

        }
	    Delete g = new Delete();
        g.execute();
	}
	public void UpgradeData(String id, String group, String purchase_date, String name, int image_num
			, String shelf_life, int num, int position)
	{
        class updateFoodJSON extends SwingWorker {

			@Override
			protected Object doInBackground() throws Exception {


            	try {
                    String uri = "http://52.78.88.182/updateFood.php";
                    JSONObject jsonObj = new JSONObject();
                    jsonObj.put("id", id);
                    jsonObj.put("group", group);
                    jsonObj.put("name", name);
                    jsonObj.put("purchase_date", purchase_date);
                    jsonObj.put("image_num", image_num);
                    jsonObj.put("shelf_life", shelf_life);
                    jsonObj.put("num", num);
                    jsonObj.put("position", position);

                    BufferedWriter bufferedWriter = null;
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    con.setDoOutput(true);

	                String data ="&" + URLEncoder.encode("data", "UTF-8") + "="+ URLEncoder.encode(jsonObj.toString(), "UTF-8");

                    OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
                    wr.write(data);
                    wr.flush();

                    BufferedReader reader=new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String line=null;
                    while((line=reader.readLine())!=null){
                        //서버응답값을 String 형태로 추가함
                    }
                    return sb.toString().trim();

                }catch(Exception e){
                    return null;
                }
            }
        }
        updateFoodJSON g = new updateFoodJSON();
        g.execute();

	}
	
	class timeThread extends Thread
	{
		public void run()
		{
			while(true)
			{
				// get date
				date_object = new Date();
				today = format.format(date_object);
				today += " "+day[date_object.getDay()];
				date.setText("<html><font color = #FFFFFFF>"+today+"</font><html>");
	
				try
				{
					Thread.sleep(60000);
				}
				catch(Exception e)
				{
					System.err.println(e);
				}
			}
		}
	}
	
	
	Calendar current;
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
		MainFrame temp = new MainFrame();
	}
}
