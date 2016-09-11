package refrigerator;

public class Data {
	
	String group;
	String name;
	String shelf_life_cold;
	String shelf_life_freeze;
	
	String[] contents;
	
	public Data(String group, String name, String cold, String freeze)
	{
		this.group = group;
		this.name = name;
		this.shelf_life_cold = cold;
		this.shelf_life_freeze = freeze;
		
		contents = new String[5];
		contents[0] = group;
		contents[1] = name;
		contents[2] = shelf_life_cold;
		contents[3] = shelf_life_freeze;
		
	}
	
	public String getGroup()
	{
		return group;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getcold()
	{
		return shelf_life_cold;
	}
	
	public String getfreeze()
	{
		return shelf_life_freeze;
	}
	
	public String[] getContents()
	{
		return contents;
	}

}
