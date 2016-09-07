package frontdoor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class WeatherPanel extends JPanel{

	Dimension fulldim;
	
	Weather_Parsing weather;
	Finedust_Parsing dust;
	
	// 발표시각
	JPanel locate_panel;
	String locate;
	String time;
	
	// weather
	String rain_or_snow;
	String[] current_weather = new String[8];
	JPanel rainpanel;
	JLabel weather_label;
	JLabel weather_text;
	
	JLabel locate_label;
	JLabel time_label;
	
	// max, min
	JLabel max_tmp;
	
	// Finedust
	JLabel dust_label;

	
	
	//ImageIcon
	ImageIcon rain;	
	ImageIcon snow;
	ImageIcon cloud;
	ImageIcon extrasunny;	
	ImageIcon sunny;
	ImageIcon umb;
	
	JLabel current;

			
	public WeatherPanel(Dimension fulldim)
	{
		this.setBackground(Color.WHITE);
		this.setBounds((fulldim.width/3)*2, fulldim.height/15, fulldim.width/3, fulldim.height-fulldim.height/15);
		this.setLayout(new GridLayout(3,1));
		
		this.fulldim = fulldim;
		
		// Initial icon
		try
		{
			rain = new ImageIcon(new URL("http://i.imgur.com/AWMa7gQ.png"));	
			snow = new ImageIcon(new URL("http://i.imgur.com/h4CMaCF.png"));
			cloud = new ImageIcon(new URL("http://i.imgur.com/MDbF2OO.png"));
			extrasunny = new ImageIcon(new URL("http://i.imgur.com/u1Bzd2n.png"));	
			sunny = new ImageIcon(new URL("http://i.imgur.com/PNzaYE9.png"));
			umb = new ImageIcon(new URL("http://i.imgur.com/Qa8JcUI.png"));
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
		
		
		// 발표 장소, 시간
		locate_label = new JLabel("",JLabel.CENTER);
		locate_label.setFont(new Font(null, Font.BOLD, 15));
		this.add(locate_label);
	
		JPanel test = new JPanel(){
			@Override
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				
				Image img;

				try
				{
					ImageIcon background = new ImageIcon(new URL("http://i.imgur.com/v2Vh9Ku.png"));
					img = background.getImage();

					g.drawImage(img, 0,0,this.getWidth(),this.getHeight(),this);

				}
				catch(Exception e)
				{
					System.err.println(e);
				}
			}
		};
//		test.setBackground(new Color(91,90,255));
//		test.setBackground(Color.WHITE);
		test.setLayout(new BorderLayout());
		current = new JLabel("",JLabel.CENTER);
		test.add(current,BorderLayout.CENTER);
	
		
		// umb panel
		JPanel umb_minmax = new JPanel(){
			@Override
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				
				Image img;

				try
				{
					ImageIcon background = new ImageIcon(new URL("http://i.imgur.com/v2Vh9Ku.png"));
					img = background.getImage();

					g.drawImage(img, 0,0,this.getWidth(),this.getHeight(),this);

				}
				catch(Exception e)
				{
					System.err.println(e);
				}
			}
		};
//		umb_minmax.setBackground(Color.WHITE);
		umb_minmax.setLayout(new BorderLayout());
		weather_label = new JLabel("");
		weather_text = new JLabel("",JLabel.CENTER);
		umb_minmax.add(weather_label, BorderLayout.NORTH);
		
		// max, min temp;
		max_tmp = new JLabel("",JLabel.RIGHT);
		max_tmp.setFont(new Font(null,Font.BOLD,15));
		umb_minmax.add(max_tmp, BorderLayout.SOUTH);

		test.add(umb_minmax, BorderLayout.EAST);

		// dust
		JPanel dustpanel = new JPanel(){
			@Override
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				
				Image img;

				try
				{
					ImageIcon background = new ImageIcon(new URL("http://i.imgur.com/v2Vh9Ku.png"));
					img = background.getImage();

					g.drawImage(img, 0,0,this.getWidth(),this.getHeight(),this);

				}
				catch(Exception e)
				{
					System.err.println(e);
				}
			}
		};
//		dustpanel.setBackground(Color.WHITE);
		dustpanel.setLayout(new BorderLayout());
		dust_label = new JLabel("", JLabel.RIGHT);
		dust_label.setFont(new Font(null, Font.BOLD,15));
		dustpanel.add(dust_label, BorderLayout.SOUTH);
		test.add(dustpanel, BorderLayout.WEST);

		this.add(test);

		update();
	}
	
	public int update()
	{
		// detect parsing error
		boolean success = true;
		

		weather = new Weather_Parsing();
		dust = new Finedust_Parsing();
		
		locate = weather.get_locate_time().split("//")[0];
		time = weather.get_locate_time().split("//")[1];
		
		locate_label.setText("<html><font color=#FFFFFF>"+locate+"</font></html>");
		
		rain_or_snow = weather.get_weather();
		
		current_weather = weather.get_wfKor();
		
		Image img;
		
		if(current_weather[0].equals("비"))
		{	
			img = rain.getImage();
		
			current.setIcon(new ImageIcon(img.getScaledInstance(fulldim.width/10, fulldim.height/8, Image.SCALE_SMOOTH)));
		}
		else if(current_weather[0].equals("눈"))
		{
			img = snow.getImage();
			
			current.setIcon(new ImageIcon(img.getScaledInstance(fulldim.width/10, fulldim.height/8, Image.SCALE_SMOOTH)));
		
			
//			current.setIcon(snow);
		}
		else if(current_weather[0].equals("비나 눈"))
		{
			img = snow.getImage();
			
			current.setIcon(new ImageIcon(img.getScaledInstance(fulldim.width/10, fulldim.height/8, Image.SCALE_SMOOTH)));

			
			//			current.setIcon(snow);
		}
		else if(current_weather[0].equals("구름 많음"))
		{
			img = sunny.getImage();
			
			current.setIcon(new ImageIcon(img.getScaledInstance(fulldim.width/10, fulldim.height/8, Image.SCALE_SMOOTH)));

			
			
			//			current.setIcon(sunny);
		}
		else if(current_weather[0].equals("구름 조금"))
		{
			img = extrasunny.getImage();
			
			current.setIcon(new ImageIcon(img.getScaledInstance(fulldim.width/10, fulldim.height/8, Image.SCALE_SMOOTH)));

			
			//			current.setIcon(extrasunny);
		}
	
		
		if(rain_or_snow.contains("비") || rain_or_snow.contains("눈"))
		{
			img = umb.getImage();
			
			weather_label.setIcon(new ImageIcon(img.getScaledInstance(fulldim.width/25, fulldim.height/15, Image.SCALE_SMOOTH)));
		
			weather_text.setText("비나 눈이와요. 우산을 챙기세요!!");
		}
		else
		{
			weather_text.setText("오늘은 우산이 필요 없는 날이 될 것 같아요!!");
		}
		
		max_tmp.setText("<html><font color = #FFFFFFF>최고 : " + weather.get_maxtmp() + "<br>최저 : "+weather.get_mintmp()+"</font></html>");

		
		
		/*
		 *  dust
		 */
		String duststr;
		String dustcolor;
		Double pm10;
		
		if(dust.get_pm10_seoul() == "")
			pm10 = 0.0;
		else
			pm10 = Double.parseDouble(dust.get_pm10_seoul());
		
		if(pm10 < 30.0)
		{
			duststr = "좋음";
			dustcolor = "#0054FF";
		}
		else if(pm10 > 30 && pm10 < 80)
		{
			duststr = "보통";
			dustcolor = "#2FED28";
		}
		else
		{
			duststr = "나쁨";
			dustcolor = "#FF0000";
		}
			
		dust_label.setText("<html><font color = #FFFFFFF>미세먼지 : </font><br><font color = "+dustcolor+">"+duststr+"</font>"+"<font color = #FFFFFFF>("+dust.get_pm10_seoul()+"μg/m³)</font></html>");
		
						
		
		if(success)
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Image img;

		try
		{
			ImageIcon background = new ImageIcon(new URL("http://i.imgur.com/NLr14Py.png"));
			img = background.getImage();

			g.drawImage(img, 0,0,this.getWidth(),this.getHeight(),this);

		}
		catch(Exception e)
		{
			System.err.println(e);
		}
	}
	
}
