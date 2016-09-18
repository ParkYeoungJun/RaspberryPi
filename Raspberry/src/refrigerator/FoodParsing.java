package refrigerator;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class FoodParsing {

		ArrayList colddata = new ArrayList<UserData>();
		ArrayList freezedata = new ArrayList<UserData>();
		
		// JSON rul
		URL url;
		InputStreamReader isr;
		JSONObject object;
		
		public FoodParsing()
		{
			try
			{
				// initial
				url = new URL("http://52.78.88.182/getFood.php");
				isr = new InputStreamReader(url.openConnection().getInputStream(), "UTF-8");
				object = (JSONObject)JSONValue.parse(isr);

				// parsing
				JSONArray resultArray = (JSONArray)object.get("result");
						
				for(int i = 0 ; i < resultArray.size() ; ++i)
				{
					String tempstr;
					
					JSONObject table = (JSONObject) resultArray.get(i);
					
					if(table.get("position").toString().equals("0"))
					{
						freezedata.add(new UserData(table.get("id").toString(), table.get("group").toString(), table.get("name").toString(), 
							table.get("purchase_date").toString(), table.get("shelf_life").toString(),
							table.get("num").toString(), table.get("position").toString()));
					}
					else if(table.get("position").toString().equals("1"))
					{
						colddata.add(new UserData(table.get("id").toString(), table.get("group").toString(), table.get("name").toString(), 
								table.get("purchase_date").toString(), table.get("shelf_life").toString(),
								table.get("num").toString(), table.get("position").toString()));
					}
						
				}
			
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		public  void update(int flag)
		{
			freezedata.clear();
			colddata.clear();
			
			// parsing
			try {
				url = new URL("http://52.78.88.182/getFood.php");
				isr = new InputStreamReader(url.openConnection().getInputStream(), "UTF-8");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			object = (JSONObject)JSONValue.parse(isr);

			JSONArray resultArray = (JSONArray)object.get("result");
					
			for(int i = 0 ; i < resultArray.size() ; ++i)
			{
				String tempstr;
				
				JSONObject table = (JSONObject) resultArray.get(i);
				
				if(flag == 0)
				{
					if(table.get("position").toString().equals("0"))
					{
						freezedata.add(new UserData(table.get("id").toString(), table.get("group").toString(), table.get("name").toString(), 
								table.get("purchase_date").toString(), table.get("shelf_life").toString(),
								table.get("num").toString(), table.get("position").toString()));
					}
				}
				else if(flag == 1)
				{
					if(table.get("position").toString().equals("1"))
					{
					
						colddata.add(new UserData(table.get("id").toString(), table.get("group").toString(), table.get("name").toString(), 
								table.get("purchase_date").toString(), table.get("shelf_life").toString(),
								table.get("num").toString(), table.get("position").toString()));
					}
				}
					
			}
		}
		
		public ArrayList getcoldData()
		{
			return colddata;
		}

		public ArrayList getfreezeData()
		{
			return freezedata;
		}
		
		public String getId(int flag, String name, String purchase_date, String num)
		{
			if(flag == 0)
			{
				for(int i = 0 ; i < freezedata.size() ; ++i)
				{
					UserData temp;
					
					temp = (UserData) freezedata.get(i);
					
					if(name.equals(temp.getName()))
					{
						if(purchase_date.equals(temp.getPurchase()))
						{
							if(num.equals(temp.getNum()))
							{
								if(temp.position.equals("0"))
								{
									return temp.getId();
								}
							}
						}
					}
				}
			}
			else if(flag == 1)
			{
				for(int i = 0 ; i < colddata.size() ; ++i)
				{
					UserData temp;
					
					temp = (UserData) colddata.get(i);
					
					if(name.equals(temp.getName()))
					{
						if(purchase_date.equals(temp.getPurchase()))
						{
							if(num.equals(temp.getNum()))
							{
								if(temp.position.equals("1"))
								{
									return temp.getId();
								}
							}
						}
					}
				}
			}
			
			return null;
		}
}
