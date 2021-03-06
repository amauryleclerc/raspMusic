package fr.aleclerc.rasp.music.storage.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RaspConf {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RaspConf.class.getClass());
	
	private static String propFileName = "storage.properties";
 
	public static String getPropValue(String key) throws IOException {
		LOGGER.debug("getPropValue : {} ",key);
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
			LOGGER.error("getPropValue : {}",e.getMessage());
		} finally {
			inputStream.close();
		}
		return result;
	}
}