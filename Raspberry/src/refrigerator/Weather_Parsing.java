package refrigerator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

class Weather_Parsing {
	
	String locate;
	String time;
	
	String[] temp = new String[8];
	String[] wfKor = new String[8];
	String[] hour = new String[8];
	String[] pty = new String[8];
	String[] pop = new String[8];
	String[] ws = new String[8];
	String[] day = new String[8];
	Double max_tmp;
	Double min_tmp;
	String weather = "";
	
	String temp_day;
	String temp_pty;
	
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	Date current;
	String date;
	String today;
	
	
	public Weather_Parsing()
	{

		try
		{
			
			current = new Date();
			date = format.format(current);
		 
			DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
			DocumentBuilder parser = f.newDocumentBuilder();
			Document xmldoc = null;
			
			String url = "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1162069500";
			xmldoc = parser.parse(url);
						
			Element root = xmldoc.getDocumentElement();
			
			Node category = root.getElementsByTagName("category").item(0);
			locate = category.getTextContent();
			Node pubDate = root.getElementsByTagName("pubDate").item(0);
			time = pubDate.getTextContent();
					
			
			for(int i = 0 ; i < temp.length ; ++i)
			{
				Node xmlNode1 = root.getElementsByTagName("data").item(i);

				// hour
				Node node_hour = ((Element) xmlNode1).getElementsByTagName("hour").item(0);
			
				// temperature
				Node node_temp = ((Element) xmlNode1).getElementsByTagName("temp").item(0);
				
				// weather_korean
				Node node_wfkor = ((Element) xmlNode1).getElementsByTagName("wfKor").item(0);
				
				
				// precipitation
				Node node_pty = ((Element) xmlNode1).getElementsByTagName("pty").item(0);
				if(node_pty.getTextContent().equals("0"))
					temp_pty = "���� Ȯ�� ����";
				else if(node_pty.getTextContent().equals("1"))
					temp_pty = "��";
				else if(node_pty.getTextContent().equals("2") || node_pty.equals("3"))
					temp_pty = "�� ��";
				else if(node_pty.getTextContent().equals("4"))
					temp_pty = "��";
				
				
				// percent precipitation
				Node node_pop = ((Element) xmlNode1).getElementsByTagName("pop").item(0);
				
				// wind speed
				Node node_ws = ((Element) xmlNode1).getElementsByTagName("ws").item(0);
				
				//day
				Node node_day = ((Element) xmlNode1).getElementsByTagName("day").item(0);
				if(node_day.getTextContent().equals("0"))
					temp_day = "����";
				else if(node_day.getTextContent().equals("1"))
					temp_day = "����";
				else if(node_day.getTextContent().equals("2"))
					temp_day = "��";
				
				
				// store weather
				if(temp_day.equals("����"))
				{
					if(temp_pty.equals("��"))
					{
						if(weather.equals("��") && weather.equals("�� ��"))
							weather = "�� ��";
						else
						{
							weather = "��";
						}
					}
					else if(temp_pty.equals("��"))
					{
						if(weather.equals("��") && weather.equals("�� ��"))
							weather = "�� ��";
						else
						{
							weather = "��";
						}
					}
					else if(temp_pty.equals("�� ��"))
					{
						weather = "�� ��";
					}
				}
				
				
//				// max, min temp
//				Node node_max = ((Element) xmlNode1).getElementsByTagName("tmx").item(0);
//				Node node_min = ((Element) xmlNode1).getElementsByTagName("tmn").item(0);
//
//				
//				if(!node_max.getTextContent().equals("-999.0") && temp_day.equals("����"))
//				{
//					max_tmp[1] = node_max.getTextContent();
//				}
//				if(!node_min.getTextContent().equals("-999.0") && temp_day.equals("����"))
//				{
//					min_tmp[1] = node_min.getTextContent();
//				}
//				
//				
//				if(!node_max.getTextContent().equals("-999.0") && temp_day.equals("����"))
//				{
//					max_tmp[0] = node_max.getTextContent();
//				}
//				if(!node_min.getTextContent().equals("-999.0") && temp_day.equals("����"))
//				{
//					min_tmp[0] = node_min.getTextContent();
//				}
				
				
				
//				System.out.println(temp_day);
//				System.out.println(node_hour.getTextContent() + "��");
//				System.out.println("���� : " + node_temp.getTextContent());
//				System.out.println(node_wfkor.getTextContent());
//				System.out.println(temp_pty);
//				System.out.println("����Ȯ�� : " + node_pop.getTextContent() + " %");
//				System.out.println("ǳ�� : " + node_ws.getTextContent() + " m/s");
//				System.out.println(max_tmp);
//				System.out.println(min_tmp);
//				System.out.println();
				
				

				day[i] = temp_day;
				hour[i] = node_hour.getTextContent() + "��";		
				temp[i] = node_temp.getTextContent();
				wfKor[i] = node_wfkor.getTextContent();
				pty[i] = temp_pty;
				pop[i] = node_pop.getTextContent();
				ws[i] = node_ws.getTextContent();
			}
			
			max_tmp = -999.0;
			min_tmp = 999.0;
			
			for(int i = 0 ; i < temp.length ; ++i)
			{
				if(day[i].equals("����"))
				{
					if(Double.parseDouble(temp[i]) > max_tmp)
					{
						max_tmp = Double.parseDouble(temp[i]);
					}
					
					if(Double.parseDouble(temp[i]) < min_tmp)
					{
						min_tmp = Double.parseDouble(temp[i]);
					}
				}
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	// ���� ����, ��ǥ �ð�
	public String get_locate_time()
	{
		return locate+"//"+time;
	}

	// �������� ��������
	public String[] get_day()
	{
		return day;
	}
	
	// �ð�
	public String[] get_hour()
	{
		return hour;
	}
	
	// �µ�
	public String[] get_temp()
	{
		return temp;
	}
	
	// ����(�ѱ���)
	public String[] get_wfKor()
	{
		return wfKor;
	}
	
	// ���� (��,�� ��)
	public String[] get_pty()
	{
		return pty;
	}
	
	// ���� Ȯ��
	public String[] get_pop()
	{
		return pop;
	}
	
	// ǳ��
	public String[] get_ws()
	{
		return ws;
	}
	
	public String get_weather()
	{
		return weather;
	}
	
	public Double get_maxtmp()
	{
		return max_tmp;
	}
	
	public Double get_mintmp()
	{
		return min_tmp;
	}
	
//	public static void main(String[] args)
//	{
//		Weather_Parsing temp = new Weather_Parsing();
//	}
}