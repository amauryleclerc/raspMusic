package fr.motaz.rasp.music.player.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import fr.motaz.rasp.music.player.Player;
import fr.motaz.rasp.music.player.PlayerListener;
import fr.motaz.rasp.music.player.PlayerState;
import fr.motaz.rasp.music.player.Playlist;

public class PlayerImpl implements Player {

	protected static final Logger logger = LogManager.getLogger(PlayerImpl.class);

	@Autowired
	private PlaylistImpl playlist;
	private List<PlayerListener> listeners = new ArrayList<PlayerListener>();
	private PlayerState state = PlayerState.STOP;
	private static PlayerImpl instance;

	public static PlayerImpl getInstance() {
		logger.trace("player : getInstance");
		if (instance == null) {
			instance = new PlayerImpl();

		}
		return instance;
	}

	private PlayerImpl() {

	}

	@Override
	public void stop() throws Exception {
		logger.trace("stop");
		((MusicImpl) this.getPlaylist().getCurrent()).getMediaPlayer().stop();
		for (PlayerListener listener : listeners) {
			listener.onStop();
		}
		this.state = PlayerState.STOP;
	}

	@Override
	public void play() throws Exception {
		logger.trace("play");

		((MusicImpl) this.getPlaylist().getCurrent()).getMediaPlayer().play();

		for (PlayerListener listener : listeners) {
			listener.onPlay(this.getPlaylist().getCurrent());
		}
		this.state = PlayerState.PLAY;
	}

	@Override
	public void pause() throws Exception {
		logger.trace("pause");

			((MusicImpl) this.getPlaylist().getCurrent()).getMediaPlayer().pause();
	
		for (PlayerListener listener : listeners) {
			listener.onPause();
		}
		this.state = PlayerState.PAUSE;
	}

	public void init() throws Exception {
		logger.info("Initialisation du player");
		// new JFXPanel();

	}

	public void destroy() throws Exception {
		logger.info("destroy du player");
		// Platform.exit();
	}

	@Override
	public void next() throws Exception {
		logger.trace("next");
		if(this.getPlaylist().getCurrent() != null && ((MusicImpl)this.getPlaylist().getCurrent()).getMediaPlayer().isPlaying()){
			this.stop();
			if (this.playlist.setCurrentNum(this.playlist.getCurrentNum() + 1)) {
				this.play();
			}
		}else{
			this.playlist.setCurrentNum(this.playlist.getCurrentNum() + 1);
		}

	}

	@Override
	public void previous() throws Exception {
		logger.trace("previous");
		if(this.getPlaylist().getCurrent() != null && ((MusicImpl)this.getPlaylist().getCurrent()).getMediaPlayer().isPlaying()){
			this.stop();
			if (this.playlist.setCurrentNum(this.playlist.getCurrentNum() - 1)) {
				this.play();
			}
		}else{
			this.playlist.setCurrentNum(this.playlist.getCurrentNum() - 1);
		}
	
	}

	@Override
	public Playlist getPlaylist() {
		logger.trace("getPlaylist");
		return this.playlist;
	}

	@Override
	public void addPlayerListener(PlayerListener listener) {
		logger.trace("addPlayerListener");
		listeners.add(listener);

	}

	@Override
	public void removePlayerListener(PlayerListener listener) {
		logger.trace("removePlayerListener");
		listeners.remove(listener);
	}

	@Override
	public PlayerState getState() {
		logger.trace("getState");
		return this.state;
	}

}
