package test;

public class processTest {

	public static void main(String args[])
	{
		try
		{
			while(true)
			{
				Process p = Runtime.getRuntime().exec("sudo tvservice -p");
		
				Thread.sleep(1200000);
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
}
