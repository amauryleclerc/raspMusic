package fr.motaz.rasp.music.player.impl;

import java.util.ArrayList;
import java.util.List;

import fr.motaz.rasp.music.model.Music;
import fr.motaz.rasp.music.player.Player;
import fr.motaz.rasp.music.player.PlayerListener;
import fr.motaz.rasp.music.player.Playlist;
import fr.motaz.rasp.music.player.PlaylistListener;
import fr.motaz.rasp.music.player.handler.EndOfMediaHandler;

public class PlaylistImpl extends ArrayList<Music>implements Playlist {

	/**
	 * 
	 */
	private  int currentNum = -1;
	private static final long serialVersionUID = 6951635866659303171L;
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
		System.out.println("playlist : add "+ music.getArtist().getName()+" - "+music.getTitle());
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
		if (currentNum > -1 && currentNum < super.size()) {
			return super.get(currentNum);
		} else {
			throw new Exception("pas de music");
		}
	}

	protected boolean setCurrentNum(Integer num) {
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
