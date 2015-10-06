package fr.motaz.rasp.music.player.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.motaz.rasp.music.model.Music;
import fr.motaz.rasp.music.player.Playlist;
import fr.motaz.rasp.music.player.PlaylistListener;
import fr.motaz.rasp.music.player.exception.PlayerException;
import fr.motaz.rasp.music.player.handler.EndOfMediaHandler;

public class PlaylistImpl extends ArrayList<Music>implements Playlist {
	private static final long serialVersionUID = 6951635866659303171L;
	protected static final Logger logger = LogManager.getLogger(PlaylistImpl.class);
	private  int currentNum = -1;
	private  List<PlaylistListener> listeners = new ArrayList<PlaylistListener>();
	private transient static PlaylistImpl instance;

	public static PlaylistImpl getInstance() {
		if (instance == null) {
			instance = new PlaylistImpl();
		}
		return instance;
	}

	private PlaylistImpl() {

	}

	public boolean add(Music music) {
		logger.trace("playlist : add "+ music.getArtist().getName()+" - "+music.getTitle());
		music.getMediaPlayer().setOnEndOfMedia(new EndOfMediaHandler());
		super.add(music);
		if (currentNum == -1) {
			currentNum++;
		}
		for (PlaylistListener listener : listeners) {
			listener.onAdd(music);
		}
		return true;

	}

	public Music getCurrent() throws Exception {
		logger.trace("getCurrent");
		if (currentNum > -1 && currentNum < super.size()) {
			return super.get(currentNum);
		} else {
			logger.error("getCurrent : pas de music");
			throw new PlayerException("pas de music");
		}
	}

	protected boolean setCurrentNum(Integer num) {
		logger.trace("setCurrentNum");
		if (num >= 0 && num < super.size()) {
			this.currentNum = num;
			return true;
		}
		return false;
	}

	public int getCurrentNum() {
		return this.currentNum;
	}


	@Override
	public void addPlaylistListener(PlaylistListener listener) {
		this.listeners.add(listener);
		
	}

	@Override
	public void removePlaylistListener(PlaylistListener listener) {
		this.listeners.remove(listener);
		
	}

}
