package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class presstest {

	
	public static void main(String[] args)
	{
		try {
			FileReader fr = new FileReader(new File("~/output.txt"));
		
			BufferedReader br = new BufferedReader(fr);
	
			String line;
			
			while((line = br.readLine()) != null)
			{
				System.out.println(line);
			}
			
			br.close();
			fr.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
