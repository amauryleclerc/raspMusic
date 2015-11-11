package fr.motaz.rasp.music.player.factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.motaz.rasp.music.player.impl.PlayerImpl;
import fr.motaz.rasp.music.player.listener.PlayerEventListener;
import uk.co.caprica.vlcj.component.DirectMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.direct.BufferFormat;
import uk.co.caprica.vlcj.player.direct.BufferFormatCallback;
import uk.co.caprica.vlcj.player.direct.DirectMediaPlayer;
import uk.co.caprica.vlcj.player.direct.format.RV32BufferFormat;


public class MediaPlayerFactory {
	protected static final Logger logger = LogManager.getLogger(PlayerImpl.class);
	private static boolean INIT = false;

	public static  void init() {

		boolean found = new NativeDiscovery().discover();
		logger.debug("discover found : "+ found);
		INIT = true;
	}

	public static DirectMediaPlayer getYTMediaPlayer(String id) {
		if (!INIT) {
			init();
		}
		DirectMediaPlayerComponent mediaPlayerComponent = new DirectMediaPlayerComponent(new BufferFormatCallback() {
			@Override
			public BufferFormat getBufferFormat(int width, int height) {
				return new RV32BufferFormat(width, height);
			}
		});
		mediaPlayerComponent.getMediaPlayer().setPlaySubItems(true);
		mediaPlayerComponent.getMediaPlayer().prepareMedia("https://www.youtube.com/watch?v=" + id);
		return mediaPlayerComponent.getMediaPlayer();
	}
	public static DirectMediaPlayer getLocalMediaPlayer(String path) {
		if (!INIT) {
			init();
		}
		DirectMediaPlayerComponent mediaPlayerComponent = new DirectMediaPlayerComponent(new BufferFormatCallback() {
			@Override
			public BufferFormat getBufferFormat(int width, int height) {
				return new RV32BufferFormat(width, height);
			}
		});
		mediaPlayerComponent.getMediaPlayer().setPlaySubItems(true);
		mediaPlayerComponent.getMediaPlayer().prepareMedia(path);
		mediaPlayerComponent.getMediaPlayer().addMediaPlayerEventListener(new PlayerEventListener());
		return mediaPlayerComponent.getMediaPlayer();
	}
}
