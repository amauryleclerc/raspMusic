package fr.aleclerc.rasp.music.ws.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MapperUtil {
	static com.fasterxml.jackson.databind.ObjectMapper MAPPER = new ObjectMapper();

	 public static <T> T readAsObjectOf(Class<T> clazz, String value)
	          throws Exception {

	      return MAPPER.readValue(value, clazz);
	      
	}
}
