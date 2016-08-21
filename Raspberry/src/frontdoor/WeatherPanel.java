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
	
	// max, min
	JLabel max_tmp;
	
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
		this.setBackground(Color.WHITE);
		this.setBounds((fulldim.width/3)*2, fulldim.height/15, fulldim.width/3, fulldim.height-fulldim.height/15);
		this.setLayout(new GridLayout(3,1));
		
		
		// ��ǥ ���, �ð�
		locate_label = new JLabel("",JLabel.CENTER);
		locate_label.setFont(new Font(null, Font.BOLD, 15));
		this.add(locate_label);
		
		JPanel test = new JPanel();
		test.setBackground(Color.WHITE);
		test.setLayout(new BorderLayout());
		current = new JLabel("",JLabel.CENTER);
		test.add(current,BorderLayout.CENTER);
		
		
		// umb panel
		JPanel umb_minmax = new JPanel();
		umb_minmax.setBackground(Color.WHITE);
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
		JPanel dustpanel = new JPanel();
		dustpanel.setBackground(Color.WHITE);
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
		
		max_tmp.setText("<html>�ְ� : " + weather.get_maxtmp() + "<br>���� : "+weather.get_mintmp()+"</html>");

		
		
		/*
		 *  dust
		 */
		String duststr;
		String dustcolor;
		Double pm10 = Double.parseDouble(dust.get_pm10_seoul());
		if(pm10 < 30.0)
		{
			duststr = "����";
			dustcolor = "#0054FF";
		}
		else if(pm10 > 30 && pm10 < 80)
		{
			duststr = "����";
			dustcolor = "#2FED28";
		}
		else
		{
			duststr = "����";
			dustcolor = "#FF0000";
		}
			
		dust_label.setText("<html>�̼����� : <br><font color = "+dustcolor+">"+duststr+"</font>"+"("+dust.get_pm10_seoul()+"��g/m��)</html>");
		
						
		
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
