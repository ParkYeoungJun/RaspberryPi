package refrigerator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class PressPanel extends JPanel{
	
	Dimension fulldim;

	Calendar today ;
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	URL url;
	InputStreamReader isr;
	JSONObject object;
	
	JLabel sumlabel;
	
	public PressPanel(Dimension fulldim)
	{
		
		
		
		this.fulldim = fulldim;
		
		today = Calendar.getInstance();

		this.setBounds(0, fulldim.height/15 + fulldim.height*4/5, fulldim.width, fulldim.height - fulldim.height/15-fulldim.height*4/5);
		this.setBackground(Color.GRAY);
		this.setLayout(null);
		
		sumlabel = new JLabel("",JLabel.CENTER);
		sumlabel.setFont(new Font(null,Font.BOLD,30));
		sumlabel.setBounds(0, 0, fulldim.width, (fulldim.height - fulldim.height/15-fulldim.height*4/5)/2);
		this.add(sumlabel);
		
		upgradeThread up = new upgradeThread();
		up.start();
		
		this.setVisible(true);
		
	}
	
	class upgradeThread extends Thread
	{
		public void run()
		{						
			try
			{
				today.getInstance();

				// initial
				url = new URL("http://52.78.88.182/getSecond.php?date="+format.format(today.getTime()));
				isr = new InputStreamReader(url.openConnection().getInputStream(), "UTF-8");
				object = (JSONObject)JSONValue.parse(isr);

				// parsing
				JSONArray resultArray = (JSONArray)object.get("result");
				
				int sum = 0;
				
				for(int i = 0 ; i < resultArray.size() ; ++i)
				{
					String tempstr;
					
					JSONObject table = (JSONObject) resultArray.get(i);
					
					sum += Integer.parseInt(table.get("second").toString());
				}
				
				int hour = sum/3600;
				if(hour != 0)
					sum -= hour*3600;
				int min = sum/60;
				if(min != 0)
					sum -= min*60;
				
				sumlabel.setText("<html><font color=#FFFFFFF>금일 "+hour+" 시간"+min+" 분"+sum+" 초 만큼 냉장고를 사용 하였습니다.</font></html>");
			
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
	}
}
