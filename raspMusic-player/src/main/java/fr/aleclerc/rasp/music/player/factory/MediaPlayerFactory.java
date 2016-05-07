package fr.aleclerc.rasp.music.player.factory;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fr.aleclerc.rasp.music.player.listener.PlayerEventListener;
import uk.co.caprica.vlcj.component.DirectMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.direct.BufferFormat;
import uk.co.caprica.vlcj.player.direct.BufferFormatCallback;
import uk.co.caprica.vlcj.player.direct.DirectMediaPlayer;
import uk.co.caprica.vlcj.player.direct.format.RV32BufferFormat;

@Component
@Scope("singleton")
public class MediaPlayerFactory {
	protected static final Logger logger = LogManager.getLogger(MediaPlayerFactory.class);
	@Autowired
	private PlayerEventListener playerEventListener;
	
	@PostConstruct
	public void init() {

		boolean found = new NativeDiscovery().discover();
		logger.debug("discover found : "+ found);
	}

	public  DirectMediaPlayer getYTMediaPlayer(String id) {
	
		DirectMediaPlayerComponent mediaPlayerComponent = new DirectMediaPlayerComponent(new BufferFormatCallback() {
			@Override
			public BufferFormat getBufferFormat(int width, int height) {
				return new RV32BufferFormat(width, height);
			}
		});
		mediaPlayerComponent.getMediaPlayer().setPlaySubItems(true);
		mediaPlayerComponent.getMediaPlayer().prepareMedia("https://www.youtube.com/watch?v=" + id);
		mediaPlayerComponent.getMediaPlayer().addMediaPlayerEventListener(playerEventListener);
		return mediaPlayerComponent.getMediaPlayer();
	}
	public  DirectMediaPlayer getLocalMediaPlayer(String path) {
		
		DirectMediaPlayerComponent mediaPlayerComponent = new DirectMediaPlayerComponent(new BufferFormatCallback() {
			@Override
			public BufferFormat getBufferFormat(int width, int height) {
				return new RV32BufferFormat(width, height);
			}
		});
		mediaPlayerComponent.getMediaPlayer().setPlaySubItems(true);
		mediaPlayerComponent.getMediaPlayer().prepareMedia(path);
		mediaPlayerComponent.getMediaPlayer().addMediaPlayerEventListener(playerEventListener);
		return mediaPlayerComponent.getMediaPlayer();
	}
}
