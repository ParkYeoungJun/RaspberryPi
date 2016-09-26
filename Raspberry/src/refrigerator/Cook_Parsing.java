package refrigerator;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class Cook_Parsing {
	
	// JSON rul
	URL url;
	InputStreamReader isr;
	JSONObject object;
	
	ArrayList data = new ArrayList<Cookdata>();
	
	public Cook_Parsing()
	{
		try
		{
			// initial
			url = new URL("http://52.78.88.182/getRecipe.php");
			isr = new InputStreamReader(url.openConnection().getInputStream(), "UTF-8");
			object = (JSONObject)JSONValue.parse(isr);

			// parsing
			JSONArray resultArray = (JSONArray)object.get("result");
					
			for(int i = 0 ; i < resultArray.size() ; ++i)
			{
				String tempstr;
				
				JSONObject table = (JSONObject) resultArray.get(i);
		
				data.add(new Cookdata(table.get("id").toString(), table.get("cook").toString(),
						table.get("material").toString(), table.get("recipe").toString(),
						table.get("calorie").toString()));
			}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public ArrayList getData()
	{
		return data;
	}
}
