package frontdoor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class WeatherPanel extends JPanel{

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
	
	
	// Finedust
	JLabel dust_label;
	
	
	//ImageIcon
	ImageIcon rain = new ImageIcon("비.PNG");	
	ImageIcon snow = new ImageIcon("눈.PNG");
	ImageIcon cloud = new ImageIcon("구름.PNG");
	ImageIcon extrasunny = new ImageIcon("해.PNG");	
	ImageIcon sunny = new ImageIcon("구름해.PNG");
	ImageIcon umb = new ImageIcon("우산.PNG");
	JLabel current;

			
	public WeatherPanel(Dimension fulldim)
	{
		this.setBackground(Color.white);
		this.setBounds((fulldim.width/3)*2, 72, fulldim.width/3, fulldim.height);
//		this.setLayout(new FlowLayout(FlowLayout.CENTER, 0,0));		
//		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setLayout(new GridLayout(6,1));
		
//		this.weather = weather;
		weather = new Weather_Parsing();
		dust = new Finedust_Parsing();
		
		
		// 발표 장소, 시간
		locate_label = new JLabel("",JLabel.CENTER);
		locate_label.setSize(fulldim.width/3, 30);
		this.add(locate_label);
		
		JPanel test = new JPanel();
		test.setLayout(new BorderLayout());
		current = new JLabel("",JLabel.CENTER);
//		current.setBounds(0, fulldim.height/5, fulldim.width/3, 200);
		test.add(current,BorderLayout.CENTER);
//		this.add(current);
//		this.add(test);
		
		
		// umb panel
		weather_label = new JLabel("");
		weather_text = new JLabel("",JLabel.CENTER);
//		weather_text.setBounds(fulldim.width/15, fulldim.height/2, fulldim.width/5, 30);
//		weather_label.setBounds(fulldim.width/4, fulldim.height/2, 50, 50);
//		this.add(weather_text);
//		this.add(weather_label);
		test.add(weather_label,BorderLayout.EAST);
		this.add(test);
		
		// dust
		dust_label = new JLabel("", JLabel.RIGHT);
		dust_label.setFont(new Font(null, 10,20));
//		dust_label.setBounds(0, fulldim.height/2+100, fulldim.width/3 , 30);
		this.add(dust_label);
		

		
		update();
	}
	
	public int update()
	{
		// detect parsing error
		boolean success = true;
		
		locate = weather.get_locate_time().split("//")[0];
		time = weather.get_locate_time().split("//")[1];
		
		locate_label.setText(locate+" "+time+" 발표");
		
		rain_or_snow = weather.get_weather();
		
		current_weather = weather.get_wfKor();
		
		if(current_weather[0].equals("비"))
		{
			current.setIcon(rain);
		}
		else if(current_weather[0].equals("눈"))
		{
			current.setIcon(snow);
		}
		else if(current_weather[0].equals("비나 눈"))
		{
			current.setIcon(snow);
		}
		else if(current_weather[0].equals("구름 많음"))
		{
			current.setIcon(sunny);
		}
		else if(current_weather[0].equals("구름 조금"))
		{
			current.setIcon(extrasunny);
		}
		
		
		if(rain_or_snow.contains("비") || rain_or_snow.contains("눈"))
		{
			weather_label.setIcon(umb);
		
			weather_text.setText("비나 눈이와요. 우산을 챙기세요!!");
		}
		else
		{
//			weather_label.setIcon(umb);
			
			weather_text.setText("오늘은 우산이 필요 없는 날이 될 것 같아요!!");
		}
		
		
		
		/*
		 *  dust
		 */
		
		dust_label.setText(dust.get_pm10_seoul());
		
						
		
		if(success)
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
	
}
