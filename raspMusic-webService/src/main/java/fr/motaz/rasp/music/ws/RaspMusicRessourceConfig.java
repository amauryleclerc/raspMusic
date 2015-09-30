package fr.motaz.rasp.music.ws;

import org.glassfish.jersey.server.ResourceConfig;

public class RaspMusicRessourceConfig extends ResourceConfig {
	public RaspMusicRessourceConfig() {
        packages("fr.motaz.rasp.music.ws.resources");
        register(new ExeceptionWSMapper());
    }
}

