package fr.aleclerc.rasp.music.player.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.aleclerc.rasp.music.api.IPlayer;
import fr.aleclerc.rasp.music.api.exceptions.PlayerException;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;
@Component
public class MediaPlayerEventListener extends MediaPlayerEventAdapter {

	private final Logger LOGGER = LoggerFactory.getLogger(MediaPlayerEventListener.class);
	
	
	@Autowired
	private IPlayer player;

	@Override
	public void finished(MediaPlayer mediaPlayer) {
		LOGGER.debug("finished");
		try {
			player.next(true);
		} catch (Exception e) {
			LOGGER.error("finished error : {}",e.getMessage() );
		}
	}

	@Override
	public void timeChanged(MediaPlayer mediaPlayer, long newTime) {
		LOGGER.trace("timeChanged");
		try {
			player.updateTime(newTime);
		} catch (PlayerException e) {
			LOGGER.error("timeChanged error : {}",e.getMessage() );
		}

	}	
	@Override
	public void error(MediaPlayer mediaPlayer) {
		LOGGER.error("Erreur de lecture");
		try {
			player.next(true);
		} catch (Exception e) {
			LOGGER.error("error : {}",e.getMessage() );
		}
	}


}
