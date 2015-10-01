package fr.motaz.rasp.music.impl;

import java.util.ArrayList;

import fr.motaz.rasp.music.Player;
import fr.motaz.rasp.music.PlayerListener;
import fr.motaz.rasp.music.Playlist;
import fr.motaz.rasp.music.impl.handler.EndOfMediaHandler;
import fr.motaz.rasp.music.model.Music;

public class PlaylistImpl extends ArrayList<Music>implements Playlist {

	/**
	 * 
	 */
	private  int currentMusicNum = -1;
	private static final long serialVersionUID = 6951635866659303171L;

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
		System.out.println("addMusic");
		music.getMediaPlayer().setOnEndOfMedia(new EndOfMediaHandler());
		super.add(music);
		if (currentMusicNum == -1) {
			currentMusicNum++;
		}
		PlayerImpl player = PlayerImpl.getInstance();
		for (PlayerListener listener : player.getListeners()) {
			try {
				listener.onAdd(this.getCurrent());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;

	}

	public Music getCurrent() throws Exception {
		if (currentMusicNum > -1 && currentMusicNum < super.size()) {
			return super.get(currentMusicNum);
		} else {
			throw new Exception("pas de music");
		}
	}

	protected boolean setCurrentNum(Integer num) {
		if (num >= 0 && num < super.size()) {
			this.currentMusicNum = num;
			return true;
		}
		return false;
	}

	protected int getCurrentNum() {
		return this.currentMusicNum;
	}

}
