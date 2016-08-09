package frontdoor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

class WeatherPanel extends JPanel{

	Weather_Parsing weather;
	Finedust_Parsing dust;
	
	
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
//		this.setLayout(new FlowLayout(FlowLayout.CENTER,50,100));
		this.setLayout(null);		
		
//		this.weather = weather;
		weather = new Weather_Parsing();
		
		
		// 발표 장소, 시간
		locate_label = new JLabel("",JLabel.CENTER);
//		locate_label.setBounds(fulldim.width/3/4, 0, fulldim.width/3, 30);
		locate_label.setBounds(0, 0, fulldim.width/3, 30);
		this.add(locate_label);
		
		current = new JLabel("",JLabel.CENTER);
		current.setBounds(0, fulldim.height/5, fulldim.width/3, 200);
		this.add(current);
		
		
		// umb panel
		weather_label = new JLabel();
		weather_text = new JLabel();
		this.add(rainpanel);
	
		
		update();
	}
	
	public int update()
	{
		// detect parsing error
		boolean success = true;
		
		locate = weather.get_locate_time().split("//")[0];
		time = weather.get_locate_time().split("//")[1];
		
		locate_label.setText(locate+time+" 발표");
		
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
			weather_label = new JLabel(umb);
		
			weather_text.setText("비나 눈이와요. 우산을 챙기세요!!");
		}
		else
		{
			weather_text.setText("오늘은 우산이 필요 없는 날이 될 것 같아요!!");
		}
						
		
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
