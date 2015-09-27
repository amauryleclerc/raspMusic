package fr.motaz.rasp.music.listener;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import fr.motaz.rasp.music.Player;

@Configuration
public class ContextListener {

	@Autowired
	private Player player;

	private static WebSocketListener listener;

	@PostConstruct
	public void contextInitialized() {
		System.out.println("Context Initialised");
		listener = new WebSocketListener();
		player.addListener(listener);
	}

	@PreDestroy
	public void contextdestroyed() {
		System.out.println("Context Destroyed");
		player.removeListener(listener);
		listener = null;
	}

}
