package fr.aleclerc.rasp.music.ws;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@ApplicationPath("/api")
public class WebServiceConfig extends ResourceConfig {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	public WebServiceConfig() {
		LOGGER.debug("Init WebService config");
        packages("fr.aleclerc.rasp.music.ws.resources");
        register(new ExceptionWSMapper());
    }
}
