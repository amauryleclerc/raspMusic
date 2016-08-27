package fr.aleclerc.rasp.music.player.factory;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.aleclerc.rasp.music.player.listener.MediaPlayerEventListener;
import uk.co.caprica.vlcj.component.AudioMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.MediaPlayer;

@Component
public class MediaPlayerFactory {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MediaPlayerEventListener playerEventListener;

	private static boolean found = false;

	@PostConstruct
	public void init() {
		if (!found) {
			LOGGER.debug("Init VLC");
			found = new NativeDiscovery().discover();
		}
	}

	public MediaPlayer getYTMediaPlayer(String id) {
		LOGGER.debug("getYTMediaPlayer : {}",id);
		AudioMediaPlayerComponent mediaPlayerComponent = new AudioMediaPlayerComponent();
		mediaPlayerComponent.getMediaPlayer().setPlaySubItems(true);
		mediaPlayerComponent.getMediaPlayer().prepareMedia("https://www.youtube.com/watch?v=" + id);
		mediaPlayerComponent.getMediaPlayer().addMediaPlayerEventListener(playerEventListener);
		mediaPlayerComponent.videoOutput(mediaPlayerComponent.getMediaPlayer(), 0);
		return mediaPlayerComponent.getMediaPlayer();
	}

	public MediaPlayer getLocalMediaPlayer(String path) {
		LOGGER.debug("getLocalMediaPlayer : {}",path);
		AudioMediaPlayerComponent mediaPlayerComponent = new AudioMediaPlayerComponent();
		mediaPlayerComponent.getMediaPlayer().setPlaySubItems(true);
		mediaPlayerComponent.getMediaPlayer().prepareMedia(path);
		mediaPlayerComponent.getMediaPlayer().addMediaPlayerEventListener(playerEventListener);
		return mediaPlayerComponent.getMediaPlayer();

	}

}
