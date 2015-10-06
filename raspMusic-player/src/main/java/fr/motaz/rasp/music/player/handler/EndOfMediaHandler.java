package fr.motaz.rasp.music.player.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.motaz.rasp.music.player.Player;
import fr.motaz.rasp.music.player.impl.PlayerImpl;

public class EndOfMediaHandler implements Runnable {
	protected static final Logger logger = LogManager.getLogger(EndOfMediaHandler.class);

	@Override
	public void run() {
		logger.info("end of media");
		Player player = PlayerImpl.getInstance();

		try {
			player.next();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}

}