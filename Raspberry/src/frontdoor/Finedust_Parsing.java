package frontdoor;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

class Finedust_Parsing {
	
	// 환경 관리 공단에서 얻어온 xml
	String url;
	
	
	// 현재 날짜
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	Date current;
	String date;
	
	// Parser
	DocumentBuilderFactory f;
	DocumentBuilder parser;
	Document xmldoc = null;
	Element root;
	
	// 발표 시간
	String[] time = new String[2];
	
	// pm10
	String pm10_seoul; // 서울 상태
	String pm10_cause; // 요약
	
	// pm2.5
	String pm25_seoul; // 서울 상태
	String pm25_cause; // 요약

	// nodes
	Node xmlnode;
	Node dataTime;
	Node informCause;
	Node informGrade;
	
	public Finedust_Parsing()
	{
		try
		{
			current = new Date();
			date = format.format(current);
		
			url = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getMinuDustFrcstDspth?searchDate="+date+"&ServiceKey=9GNgTTrK7LYCwh%2FYFhtBM42mRSxYMLqY1uyEqGA21A3IQcmHGwSNub5f6CQiO23J109uUsS3RMwT2zgrWIuWhA%3D%3D";
	
			f = DocumentBuilderFactory.newInstance();
			parser = f.newDocumentBuilder();
			xmldoc = parser.parse(url);
			
			root = xmldoc.getDocumentElement();

			
			/*
			 *  pm10
			 */
			
			xmlnode = root.getElementsByTagName("item").item(0);
			
			dataTime = ((Element) xmlnode).getElementsByTagName("dataTime").item(0);
			time[0] = dataTime.getTextContent();

			informCause = ((Element) xmlnode).getElementsByTagName("informCause").item(0);
			pm10_cause = informCause.getTextContent();
				
			informGrade = ((Element) xmlnode).getElementsByTagName("informGrade").item(0);
			pm10_seoul = informGrade.getTextContent().split(",")[0];

			
			/*
			 *  pm2.5
			 */
			xmlnode = root.getElementsByTagName("item").item(3);
			
			dataTime = ((Element) xmlnode).getElementsByTagName("dataTime").item(0);
			time[1] = dataTime.getTextContent();

			informCause = ((Element) xmlnode).getElementsByTagName("informCause").item(0);
			pm25_cause = informCause.getTextContent();
				
			informGrade = ((Element) xmlnode).getElementsByTagName("informGrade").item(0);
			pm25_seoul = informGrade.getTextContent().split(",")[0];
			
			
			System.out.println(time[0]);
			System.out.println(pm10_cause);
			System.out.println(pm10_seoul);
			System.out.println(time[1]);
			System.out.println(pm25_cause);
			System.out.println(pm25_seoul);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public String[] get_date()
	{
		return time;
	}
	
	public String get_pm10_cause()
	{
		return pm10_cause;
	}
	
	public String get_pm10_seoul()
	{
		return pm10_seoul;
	}
	
	public String get_pm25_cause()
	{
		return pm25_cause;
	}
	
	public String get_pm25_seoul()
	{
		return pm25_seoul;
	}
	
	public static void main(String[] args)
	{
		Finedust_Parsing temp = new Finedust_Parsing();
	}

}
