package fr.motaz.rasp.music.player.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StopedHandler implements Runnable {
	protected static final Logger logger = LogManager.getLogger(StopedHandler.class);
	@Override
	public void run() {
		logger.info("handler : stop");
	}

}
