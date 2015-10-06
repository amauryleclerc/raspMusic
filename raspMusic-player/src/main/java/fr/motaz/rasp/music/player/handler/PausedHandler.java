package fr.motaz.rasp.music.player.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PausedHandler implements Runnable {
	protected static final Logger logger = LogManager.getLogger(PausedHandler.class);
	@Override
	public void run() {
		logger.info("handler : pause");
	}

}