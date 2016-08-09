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
//		this.setLayout(new FlowLayout(FlowLayout.CENTER,50,100));
		this.setLayout(null);		
		
//		this.weather = weather;
		weather = new Weather_Parsing();
		
		
		// ��ǥ ���, �ð�
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
		
		locate_label.setText(locate+time+" ��ǥ");
		
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
			weather_label = new JLabel(umb);
		
			weather_text.setText("�� ���̿Ϳ�. ����� ì�⼼��!!");
		}
		else
		{
			weather_text.setText("������ ����� �ʿ� ���� ���� �� �� ���ƿ�!!");
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