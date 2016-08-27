package fr.aleclerc.rasp.music.player.factory;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.aleclerc.rasp.music.player.listener.MediaPlayerEventListener;
import uk.co.caprica.vlcj.component.AudioMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.MediaPlayer;

@Component
public class MediaPlayerFactory {

	@Autowired
	private MediaPlayerEventListener playerEventListener;

	private static boolean found = false;

	@PostConstruct
	public void init() {
		if (!found) {
			found = new NativeDiscovery().discover();
		}
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
