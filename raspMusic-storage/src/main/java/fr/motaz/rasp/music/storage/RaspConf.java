package fr.motaz.rasp.music.storage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class RaspConf {

	private static String propFileName = "storage.properties";
 
	public static String getPropValue(String key) throws IOException {
		String result = null;
		InputStream inputStream = null;
		try {
			Properties prop = new Properties();
		
 
			inputStream = new RaspConf().getClass().getClassLoader().getResourceAsStream(propFileName);
 
			
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			result = prop.getProperty(key);
		

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return result;
	}
}