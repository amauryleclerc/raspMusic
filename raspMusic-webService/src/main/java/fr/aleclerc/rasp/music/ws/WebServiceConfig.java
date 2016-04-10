package fr.aleclerc.rasp.music.ws;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
@ApplicationPath("/api")
public class WebServiceConfig extends ResourceConfig {
	
	public WebServiceConfig() {
        packages("fr.aleclerc.rasp.music.ws.resources");
        register(new ExceptionWSMapper());
    }
}
