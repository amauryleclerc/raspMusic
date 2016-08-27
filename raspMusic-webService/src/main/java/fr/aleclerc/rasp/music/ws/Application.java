package fr.aleclerc.rasp.music.ws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("fr.aleclerc.rasp.music")
public class Application {

	private final static Logger LOGGER = LoggerFactory.getLogger(Application.class.getClass());

	public static void main(String[] args) throws Exception {
		LOGGER.debug("Start");
		SpringApplication.run(Application.class, args);
	}
}
