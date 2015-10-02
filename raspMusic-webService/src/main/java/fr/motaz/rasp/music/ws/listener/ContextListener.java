package fr.motaz.rasp.music.ws.listener;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import fr.motaz.rasp.music.player.Player;

@Configuration
public class ContextListener {

	@Autowired
	private Player player;

	private static PlayerListenerWS listener;

	@PostConstruct
	public void contextInitialized() {
		System.out.println("Context Initialised");
		listener = new PlayerListenerWS();
		player.addListener(listener);
	}

	@PreDestroy
	public void contextdestroyed() {
		System.out.println("Context Destroyed");
		player.removeListener(listener);
		listener = null;
	}

}
