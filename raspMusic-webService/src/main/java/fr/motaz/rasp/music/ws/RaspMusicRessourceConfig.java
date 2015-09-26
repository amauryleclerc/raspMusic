package fr.motaz.rasp.music.ws;

import org.glassfish.jersey.server.ResourceConfig;

//@ApplicationPath("resources")
public class RaspMusicRessourceConfig extends ResourceConfig {
	public RaspMusicRessourceConfig() {
        packages("fr.motaz.rasp.music.ws.resources");
    }
}

