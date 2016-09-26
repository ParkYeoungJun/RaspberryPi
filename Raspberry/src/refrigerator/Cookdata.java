package refrigerator;

public class Cookdata implements Comparable<Cookdata>{
	
	String id;
	String name;
	String material;
	String recipe;
	String calorie;
	int count;

	public Cookdata(String id, String name, String material,
			String recipe, String calorie)
	{
		this.id = id;
		this.name = name;
		this.material = material;
		this.recipe = recipe;
		this.calorie = calorie;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getMaterial()
	{
		return material;
	}
	
	public String getCalorie()
	{
		return calorie;
	}

	public int getCount()
	{
		return count;
	}
	
	public void setCount(int count)
	{
		this.count = count;
	}

	@Override
	public int compareTo(Cookdata c) {
		// TODO Auto-generated method stub
		if(this.count > c.count)
		{
			return -1;
		}
		else if(this.count < c.count)
		{
			return 1;
		}
		else
			return 0;
	}
}
