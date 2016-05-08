package fr.aleclerc.rasp.music.player.factory;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fr.aleclerc.rasp.music.player.listener.PlayerEventListener;
import uk.co.caprica.vlcj.component.AudioMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.MediaPlayer;

@Component
@Scope("singleton")
public class MediaPlayerFactory {
	protected static final Logger logger = LogManager.getLogger(MediaPlayerFactory.class);
	@Autowired
	private PlayerEventListener playerEventListener;

	@PostConstruct
	public void init() {

		boolean found = new NativeDiscovery().discover();
		logger.debug("discover found : " + found);
	}

	public MediaPlayer getYTMediaPlayer(String id) {

		AudioMediaPlayerComponent mediaPlayerComponent = new AudioMediaPlayerComponent();
		mediaPlayerComponent.getMediaPlayer().setPlaySubItems(true);
		mediaPlayerComponent.getMediaPlayer().prepareMedia("https://www.youtube.com/watch?v=" + id);
		mediaPlayerComponent.getMediaPlayer().addMediaPlayerEventListener(playerEventListener);
		mediaPlayerComponent.videoOutput(mediaPlayerComponent.getMediaPlayer(), 0);
		return mediaPlayerComponent.getMediaPlayer();
	}

	public MediaPlayer getLocalMediaPlayer(String path) {

		AudioMediaPlayerComponent mediaPlayerComponent = new AudioMediaPlayerComponent();
		mediaPlayerComponent.getMediaPlayer().setPlaySubItems(true);
		mediaPlayerComponent.getMediaPlayer().prepareMedia(path);
		mediaPlayerComponent.getMediaPlayer().addMediaPlayerEventListener(playerEventListener);
		return mediaPlayerComponent.getMediaPlayer();

	}

}
