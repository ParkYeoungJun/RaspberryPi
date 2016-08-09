package frontdoor;

import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class Note_Parsing {
	
	// 현재 날짜
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	Date current;
	String date;
	
	// JSON rul
	URL url;
	InputStreamReader isr;
	JSONObject object;
	
	public Note_Parsing()
	{
		try
		{
			current = new Date();
			date = format.format(current);
				
			// initial
			url = new URL("http://52.78.88.182/getdata.php?date=2016-08-03");
//			url = new URL("http://52.78.88.182/getdata.php?date="+date);
			isr = new InputStreamReader(url.openConnection().getInputStream(), "UTF-8");
			object = (JSONObject)JSONValue.parse(isr);

			// parsing
			JSONArray resultArray = (JSONArray)object.get("result");
					
			for(int i = 0 ; i < resultArray.size() ; ++i)
			{
				JSONObject data = (JSONObject) resultArray.get(i);
				
				System.out.println(data.get("id").toString());
				System.out.println(data.get("date").toString());
				System.out.println(data.get("memo").toString());
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
