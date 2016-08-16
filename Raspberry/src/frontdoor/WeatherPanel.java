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
	
	// ��ǥ�ð�
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
	ImageIcon rain = new ImageIcon("��.PNG");	
	ImageIcon snow = new ImageIcon("��.PNG");
	ImageIcon cloud = new ImageIcon("����.PNG");
	ImageIcon extrasunny = new ImageIcon("��.PNG");	
	ImageIcon sunny = new ImageIcon("������.PNG");
	ImageIcon umb = new ImageIcon("���.PNG");
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
		
		
		// ��ǥ ���, �ð�
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
		
		locate_label.setText(locate+" "+time+" ��ǥ");
		
		rain_or_snow = weather.get_weather();
		
		current_weather = weather.get_wfKor();
		
		if(current_weather[0].equals("��"))
		{
			current.setIcon(rain);
		}
		else if(current_weather[0].equals("��"))
		{
			current.setIcon(snow);
		}
		else if(current_weather[0].equals("�� ��"))
		{
			current.setIcon(snow);
		}
		else if(current_weather[0].equals("���� ����"))
		{
			current.setIcon(sunny);
		}
		else if(current_weather[0].equals("���� ����"))
		{
			current.setIcon(extrasunny);
		}
		
		
		if(rain_or_snow.contains("��") || rain_or_snow.contains("��"))
		{
			weather_label.setIcon(umb);
		
			weather_text.setText("�� ���̿Ϳ�. ����� ì�⼼��!!");
		}
		else
		{
//			weather_label.setIcon(umb);
			
			weather_text.setText("������ ����� �ʿ� ���� ���� �� �� ���ƿ�!!");
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
