package test;

import java.awt.List;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Document;

class Weather
{
	
	String[] temp = new String[8];
	String[] wfKor = new String[8];
	String[] hour = new String[8];
	String[] pty = new String[8];
	String[] pop = new String[8];
	String[] ws = new String[8];
	String[] day = new String[8];
	
	String temp_day;
	String temp_pty;
	
	
	public Weather()
	{
	
		try
		{
		 
			DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
			DocumentBuilder parser = f.newDocumentBuilder();
			Document xmldoc = null;
			
			String url = "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1162069500";
			xmldoc = parser.parse(url);
			
			Element root = xmldoc.getDocumentElement();
			
			Node category = root.getElementsByTagName("category").item(0);
			System.out.println(category.getTextContent());
			Node pubDate = root.getElementsByTagName("pubDate").item(0);
			System.out.println("��ǥ�ð� : " + pubDate.getTextContent());
			System.out.println();
			
			
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
				
				
				System.out.println(temp_day);
				System.out.println(node_hour.getTextContent() + "��");
				System.out.println("���� : " + node_temp.getTextContent());
				System.out.println(node_wfkor.getTextContent());
				System.out.println(temp_pty);
				System.out.println("����Ȯ�� : " + node_pop.getTextContent() + " %");
				System.out.println("ǳ�� : " + node_ws.getTextContent() + " m/s");
				System.out.println();
				

				day[i] = temp_day;
				hour[i] = node_hour.getTextContent() + "��";		
				temp[i] = node_temp.getTextContent();
				wfKor[i] = node_wfkor.getTextContent();
				pty[i] = temp_pty;
				pop[i] = node_pop.getTextContent();
				ws[i] = node_ws.getTextContent();
			}
			
		}
		catch(Exception e)
		{
			
		}
		
	}

	
	public static void main(String[] args)
	{
		Weather create = new Weather();
	}
	
}
