package test;

public class processTest {

	public static void main(String args[])
	{
		try
		{
				Process p = Runtime.getRuntime().exec("sudo tvservice -o");
		
				Thread.sleep(60000);
				
				Process s = Runtime.getRuntime().exec("sudo tvservice -p");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
}
