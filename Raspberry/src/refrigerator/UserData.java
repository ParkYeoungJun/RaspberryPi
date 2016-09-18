package refrigerator;

import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class UserData {

	String id;
	String group;
	String name;
	String purchase_date;
	String shelf_life;
	String num;
	String position;
	
	public UserData(String id,String group, String name, String purchase_date
			, String shelf_life, String num, String position)
	{
		this.id = id;
		this.group = group;
		this.name = name;
		this.purchase_date = purchase_date;
		this.shelf_life = shelf_life;
		this.num = num;
		this.position = position;
	}
	
	public String getGroup()
	{
		return group;
	}

	public String getName()
	{
		return name;
	}
	
	public String getPurchase()
	{
		return purchase_date;
	}
	
	public String getShelf()
	{
		return shelf_life;
	}
	
	public String getNum()
	{
		return num;
	}
	
	public String getPosition()
	{
		return position;
	}
	
	public String getId()
	{
		return id;
	}
}
