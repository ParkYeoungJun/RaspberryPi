package test;

public class processTest {

	public static void main(String args[])
	{
		try
		{
				Process p = Runtime.getRuntime().exec("xset dpms force off");
		
				Thread.sleep(60000);
				
				Process s = Runtime.getRuntime().exec("xset dpms force on");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
}
