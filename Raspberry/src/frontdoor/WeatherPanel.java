package frontdoor;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

class WeatherPanel extends JPanel{

	Weather_Parsing weather;
	Finedust_Parsing dust;
	
	
	String locate;
	String time;
	
	JLabel locate_label;
	JLabel time_label;
	
		
	public WeatherPanel(Dimension fulldim, Weather_Parsing weather)
	{
		this.setBackground(Color.white);
		this.setBounds((fulldim.width/3)*2, 72, fulldim.width/3, fulldim.height);
				
		this.weather = weather;
//		this.dust = dust;
		
		locate_label = new JLabel();
		
		locate_label.setBounds(0, 0, 100, 30);

		this.add(locate_label);
		
		update();
	}
	
	public int update()
	{
		// detect parsing error
		boolean success = true;
		
		locate = weather.get_locate_time().split("//")[0];
		time = weather.get_locate_time().split("//")[1];
		
		locate_label.setText(locate+time+" ��ǥ");
				
		
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
