package refrigerator;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Database {
	
	static int size = 115;
	
	// Data class
	ArrayList meat = new ArrayList<Data>();
	ArrayList fish = new ArrayList<Data>();
	ArrayList seafood = new ArrayList<Data>();
	ArrayList vegetable = new ArrayList<Data>();
	ArrayList fruit = new ArrayList<Data>();
	ArrayList dairy = new ArrayList<Data>();
	ArrayList beverage = new ArrayList<Data>();
	ArrayList etc = new ArrayList<Data>();

	public Database()
	{
			try
			{
				FileInputStream fis = new FileInputStream("icon/shelf_life_data.xml");
			
				DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
				DocumentBuilder parser = f.newDocumentBuilder();
				Document xmldoc = null;

				xmldoc = parser.parse(fis);
					
				Element root = xmldoc.getDocumentElement();
				
				for(int i = 1 ; i < size ; ++i)
				{
				
					Node xmlNode1 = root.getElementsByTagName("Row").item(i);
				
//					Node node_id = ((Element) xmlNode1).getElementsByTagName("Cell").item(0);
					Node node_group = ((Element) xmlNode1).getElementsByTagName("Cell").item(1);
					Node node_name = ((Element) xmlNode1).getElementsByTagName("Cell").item(2);
//					Node node_icon = ((Element) xmlNode1).getElementsByTagName("Cell").item(3);
					Node node_shelf_life_cold = ((Element) xmlNode1).getElementsByTagName("Cell").item(4);
					Node node_shelf_life_freeze = ((Element) xmlNode1).getElementsByTagName("Cell").item(5);
					
					if(node_group.getTextContent().equals("육류"))
					{
						meat.add(new Data(node_group.getTextContent(), node_name.getTextContent(),
								node_shelf_life_cold.getTextContent(), node_shelf_life_freeze.getTextContent()));
					}
					else if(node_group.getTextContent().equals("생선"))
					{
						fish.add(new Data(node_group.getTextContent(), node_name.getTextContent(),
								node_shelf_life_cold.getTextContent(), node_shelf_life_freeze.getTextContent()));
					}
					else if(node_group.getTextContent().equals("해산물"))
					{
						seafood.add(new Data(node_group.getTextContent(), node_name.getTextContent(),
								node_shelf_life_cold.getTextContent(), node_shelf_life_freeze.getTextContent()));
					}
					else if(node_group.getTextContent().equals("채소"))
					{
						vegetable.add(new Data(node_group.getTextContent(), node_name.getTextContent(),
								node_shelf_life_cold.getTextContent(), node_shelf_life_freeze.getTextContent()));	
					}
					else if(node_group.getTextContent().equals("과일"))
					{
						fruit.add(new Data(node_group.getTextContent(), node_name.getTextContent(),
								node_shelf_life_cold.getTextContent(), node_shelf_life_freeze.getTextContent()));
					}
					else if(node_group.getTextContent().equals("유제품"))
					{
						dairy.add(new Data(node_group.getTextContent(), node_name.getTextContent(),
								node_shelf_life_cold.getTextContent(), node_shelf_life_freeze.getTextContent()));
					}
					else if(node_group.getTextContent().equals("음료"))
					{
						beverage.add(new Data(node_group.getTextContent(), node_name.getTextContent(),
								node_shelf_life_cold.getTextContent(), node_shelf_life_freeze.getTextContent()));
					}
					else if(node_group.getTextContent().equals("기타"))
					{
						etc.add(new Data(node_group.getTextContent(), node_name.getTextContent(),
								node_shelf_life_cold.getTextContent(), node_shelf_life_freeze.getTextContent()));
					}
				}
			}
			catch(Exception e)
			{
				System.err.println(e);
			}
	}
	
	public Data getShelflife(String group, String name)
	{
		
		if(group.equals("육류"))
		{
			for(int i = 0 ; i < meat.size() ; ++i)
			{
				Data temp = (Data)meat.get(i);
				
				if(temp.getName().equals(name))
				{
					return temp;
				}
			}
		}
		else if(group.equals("생선"))
		{
			for(int i = 0 ; i < fish.size() ; ++i)
			{
				Data temp = (Data)fish.get(i);
				
				if(temp.getName().equals(name))
				{
					return temp;
				}
			}
		}
		else if(group.equals("해산물"))
		{
			for(int i = 0 ; i < seafood.size() ; ++i)
			{
				Data temp = (Data)seafood.get(i);
				
				if(temp.getName().equals(name))
				{
					return temp;
				}
			}	
		}
		else if(group.equals("채소"))
		{
			for(int i = 0 ; i < vegetable.size() ; ++i)
			{
				Data temp = (Data)vegetable.get(i);
				
				if(temp.getName().equals(name))
				{
					return temp;
				}
			}
		}
		else if(group.equals("과일"))
		{
			for(int i = 0 ; i < fruit.size() ; ++i)
			{
				Data temp = (Data)fruit.get(i);
				
				if(temp.getName().equals(name))
				{
					return temp;
				}
			}
		}
		else if(group.equals("유제품"))
		{
			for(int i = 0 ; i < dairy.size() ; ++i)
			{
				Data temp = (Data)dairy.get(i);
				
				if(temp.getName().equals(name))
				{
					return temp;
				}
			}
		}
		else if(group.equals("음료"))
		{
			for(int i = 0 ; i < beverage.size() ; ++i)
			{
				Data temp = (Data)beverage.get(i);
				
				if(temp.getName().equals(name))
				{
					return temp;
				}
			}
		}
		else
		{
			for(int i = 0 ; i < etc.size() ; ++i)
			{
				Data temp = (Data)etc.get(i);
				
				if(temp.getName().equals(name))
				{
					return temp;
				}
			}
		}
		return null;
	}
}
