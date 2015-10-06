package fr.motaz.rasp.music.player.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.motaz.rasp.music.model.Music;
import fr.motaz.rasp.music.player.Player;
import fr.motaz.rasp.music.player.impl.PlayerImpl;

public class PlayingHandler implements Runnable {
	protected static final Logger logger = LogManager.getLogger(PlayingHandler.class);
	@Override
	public void run() {
		Player player = PlayerImpl.getInstance();
		Music music = null;
		try {
			music = player.getPlaylist().getCurrent();
			logger.info("handler : Lecture de " + music.getTitle()+ " - " + music.getArtist().getName());	
		} catch (Exception e) {
			logger.error("handler : Lecture inconnu");
		}

	}

}
