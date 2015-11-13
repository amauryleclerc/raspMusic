package fr.aleclerc.rasp.music.ws;

import org.glassfish.jersey.server.ResourceConfig;

public class RaspMusicRessourceConfig extends ResourceConfig {
	
	public RaspMusicRessourceConfig() {
        packages("fr.aleclerc.rasp.music.ws.resources");
        register(new ExceptionWSMapper());
    }
}
