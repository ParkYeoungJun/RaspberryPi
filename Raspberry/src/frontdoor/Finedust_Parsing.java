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

	// Parser
	DocumentBuilderFactory f;
	DocumentBuilder parser;
	Document xmldoc = null;
	Element root;
	
	// 발표 시간
	String[] time = new String[2];
	
	// pm10
	String pm10_seoul; // 서울 상태

	// nodes
	Node xmlnode;
	Node dataTime;
	Node informCause;
	Node informGrade;
	
	public Finedust_Parsing()
	{
		try
		{
//			current = new Date();
//			date = format.format(current);
			
			/*
			 *  pm10
			 */
			
			url = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnMesureLIst?itemCode=PM10&dataGubun=HOUR&pageNo=1&numOfRows=10&ServiceKey=9GNgTTrK7LYCwh%2FYFhtBM42mRSxYMLqY1uyEqGA21A3IQcmHGwSNub5f6CQiO23J109uUsS3RMwT2zgrWIuWhA%3D%3D";
			
			f = DocumentBuilderFactory.newInstance();
			parser = f.newDocumentBuilder();
			xmldoc = parser.parse(url);
			
			root = xmldoc.getDocumentElement();

			
			xmlnode = root.getElementsByTagName("item").item(0);
			
			dataTime = ((Element) xmlnode).getElementsByTagName("dataTime").item(0);
			time[0] = dataTime.getTextContent();
				
			informGrade = ((Element) xmlnode).getElementsByTagName("seoul").item(0);
			pm10_seoul = informGrade.getTextContent();
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
	
	public String get_pm10_seoul()
	{
		return pm10_seoul;
	}
}
