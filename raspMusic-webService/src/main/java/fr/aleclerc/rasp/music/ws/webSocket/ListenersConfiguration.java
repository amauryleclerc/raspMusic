package fr.aleclerc.rasp.music.ws.webSocket;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import fr.aleclerc.rasp.music.player.Player;

@Configuration
@Component
public class ListenersConfiguration {

	@Autowired
	WebSocketBroker webSocketBroker;

	@Autowired
	private Player player;

	
	@PostConstruct
	public void init() {
	
		player.addPlayerListener(webSocketBroker);
		player.getPlaylist().addPlaylistListener(webSocketBroker);
	}

}
