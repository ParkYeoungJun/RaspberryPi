package refrigerator;

import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

import javax.swing.JPanel;

public class PressPanel extends JPanel{
	
	Dimension fulldim;

	Calendar today ;
	
	public PressPanel(Dimension fulldim)
	{
		this.fulldim = fulldim;
		
		today = Calendar.getInstance();

				
		this.setBounds(0, fulldim.height/15 + fulldim.height/2, fulldim.width, fulldim.height - (fulldim.height/15+fulldim.height/2));

		this.setBackground(Color.GRAY);
		
		upgradeThread up = new upgradeThread();
		up.start();
		
		this.setVisible(true);
		
	}
	
	class upgradeThread extends Thread
	{
		public void run()
		{
			today.getInstance();
						
			if(today.get(Calendar.HOUR_OF_DAY) == 0)
			{
				FileReader input;
				try {
					input = new FileReader("/home/pi/RaspberryPi/Raspberry/output.txt");
				
			
				BufferedReader br = new BufferedReader(input);
				
				String line;
				while((line = br.readLine()) != null)
				{
					System.out.println(line);
				}
				}
				 catch (Exception e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
				
		}
		
	}
}
