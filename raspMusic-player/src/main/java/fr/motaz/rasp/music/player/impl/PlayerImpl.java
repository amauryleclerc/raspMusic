package fr.motaz.rasp.music.player.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.motaz.rasp.music.player.Player;
import fr.motaz.rasp.music.player.PlayerListener;
import fr.motaz.rasp.music.player.PlayerState;
import fr.motaz.rasp.music.player.Playlist;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;

public class PlayerImpl implements Player {


	private  List<PlayerListener> listeners = new ArrayList<PlayerListener>();
	private PlaylistImpl playlist;
	private PlayerState state =  PlayerState.STOP;
	static final Logger logger = LogManager.getLogger(PlayerImpl.class);
	private static PlayerImpl instance;

	public static PlayerImpl getInstance() {
		if (instance == null) {
			instance = new PlayerImpl();
			
		}
		return instance;
	}
	private PlayerImpl(){
		this.playlist = PlaylistImpl.getInstance();
	}
	
	@Override
	public void stop() throws Exception {
		this.getPlaylist().getCurrent().getMediaPlayer().stop();

		for (PlayerListener listener : listeners) {
			listener.onStop();
		}
		this.state = PlayerState.STOP;
	}

	@Override
	public void play() throws Exception {
		logger.trace("play");
		this.getPlaylist().getCurrent().getMediaPlayer().play();
		for (PlayerListener listener : listeners) {
			listener.onPlay(this.getPlaylist().getCurrent());
		}
		this.state = PlayerState.PLAY;
	}

	@Override
	public void pause() throws Exception {
		logger.trace("pause");
		this.getPlaylist().getCurrent().getMediaPlayer().pause();
		for (PlayerListener listener : listeners) {
			listener.onPause();
		}
		this.state = PlayerState.PAUSE;
	}


	public void init() throws Exception {
		System.out.println("Initialisation du player");
		new JFXPanel();

	}

	public void destroy() throws Exception {
		System.out.println("destroy du player");
		Platform.exit();
	}

	@Override
	public void next() throws Exception {
		this.stop();
		if(this.playlist.setCurrentNum(this.playlist.getCurrentNum()+1)){
			this.play();
		}
	
	}

	@Override
	public void previous() throws Exception {
		this.stop();
		if(this.playlist.setCurrentNum(this.playlist.getCurrentNum()-1)){
			this.play();
		}
	}

	

	@Override
	public Playlist getPlaylist() {
		return this.playlist;
	}

	@Override
	public void addPlayerListener(PlayerListener listener) {
		listeners.add(listener);
		
	}
	@Override
	public void removePlayerListener(PlayerListener listener) {
		listeners.remove(listener);
	}
	@Override
	public PlayerState getState() {
		return this.state;
	}
	
	

}
