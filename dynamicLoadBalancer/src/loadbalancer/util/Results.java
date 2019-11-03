package loadbalancer.util;

import java.util.ArrayList;
import java.io.FileWriter;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {
	
	public void writeToFile(String s,String results) {

		/**
		 * https://stackoverflow.com
		 */
		try {
			
			FileWriter writer = new FileWriter(s); 
			writer.write(results);
			writer.close();

		}
		catch (Exception e) {
			System.out.println(e);
		}
		finally{}
	}
	
	public void writeToStdout(String results) {
		
		System.out.println(results);
	}	
}

