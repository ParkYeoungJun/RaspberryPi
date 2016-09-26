package refrigerator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
	
 String[] weekDay = {"일","월","화","수","목","금","토"};
	
	URL url;
	InputStreamReader isr;
	JSONObject object;
	
	ArrayList labellist = new ArrayList<JLabel>();
		
	public PressPanel(Dimension fulldim)
	{
		
		this.fulldim = fulldim;
		
		today = Calendar.getInstance();

		this.setBounds(30, fulldim.height/15 + fulldim.height*4/5, fulldim.width-60, fulldim.height - fulldim.height/15-fulldim.height*4/5-10);
		this.setBackground(Color.GRAY);
		this.setLayout(new GridLayout(1,7));
		
		for(int i = 0 ; i < 7 ; ++i)
		{
			JLabel temp = new JLabel("",JLabel.CENTER);
			temp.setFont(new Font(null, Font.BOLD, 15));
			labellist.add(temp);
		
			this.add(temp);
		}
		
		
		upgradeThread up = new upgradeThread();
		up.start();
		
		
		this.setVisible(true);
		
	}
	
	public int gettime(JLabel label, int beforeday)
	{
		int sum = 0;
		
		try {
			
			today = Calendar.getInstance();

			today.add(Calendar.DATE, -beforeday);
			// initial
			URL funurl = new URL("http://52.78.88.182/getSecond.php?date="+format.format(today.getTime()));
		
			InputStreamReader funisr = new InputStreamReader(funurl.openConnection().getInputStream(), "UTF-8");
			JSONObject funobject = (JSONObject)JSONValue.parse(funisr);

			// parsing
			JSONArray resultArray = (JSONArray)funobject.get("result");
		
			for(int i = 0 ; i < resultArray.size() ; ++i)
			{
				String tempstr;
			
				JSONObject table = (JSONObject) resultArray.get(i);
			
				sum += Integer.parseInt(table.get("second").toString());
			}
		
//			int hour = sum/3600;
//			
//			if(hour != 0)
//				sum -= hour*3600;
			
			int min = sum/60;

			if(min != 0)
				sum -= min*60;
			
			label.setText("<html><font color=#FFFFFFF>"+(today.get(Calendar.MONTH)+1)+"."+today.get(Calendar.DAY_OF_MONTH)
			+" "+weekDay[today.get(Calendar.DAY_OF_WEEK)-1]+"<br>"
			+min+"분 "+sum+"초</font></html>");

		
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sum;

	}
	
	class upgradeThread extends Thread
	{
		public void run()
		{						
			try
			{
				for(int i = 0; i < 7 ; ++i)
				{
					JLabel temp = (JLabel)labellist.get(i);
					gettime(temp, 6-i);
				}
				
				Thread.sleep(60000);
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
	}
}
