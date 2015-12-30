package fr.aleclerc.rasp.music.player.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.aleclerc.rasp.music.model.Music;
import fr.aleclerc.rasp.music.player.Playlist;
import fr.aleclerc.rasp.music.player.PlaylistListener;
import fr.aleclerc.rasp.music.player.exception.PlayerException;
import fr.aleclerc.rasp.music.player.factory.MusicFactory;

public class PlaylistImpl extends ArrayList<Music> implements Playlist {
	private static final long serialVersionUID = 6951635866659303171L;
	protected static final Logger logger = LogManager.getLogger(PlaylistImpl.class);
	private int currentNum = -1;
	private int nbMusic = 0;
	private List<PlaylistListener> listeners = new ArrayList<PlaylistListener>();
	private transient static PlaylistImpl instance;

	public static PlaylistImpl getInstance() {
		logger.trace("playlist : getInstance ");
		if (instance == null) {
			instance = new PlaylistImpl();
		}
		return instance;
	}

	private PlaylistImpl() {

	}

	

	public boolean add(Music music) {
		music = MusicFactory.getInstance(music);
		logger.trace("playlist : add " + music.getArtist().getName() + " - " + music.getTitle());
		music.setPosition(nbMusic);
		nbMusic++;
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
			if (this.currentNum > 4) {
				Music music = super.remove(0);
				this.currentNum--;
				for (PlaylistListener listener : listeners) {
					listener.onRemove(music);
				}
			}

			try {
				for (PlaylistListener listener : listeners) {
					listener.onChangeCurrent(this.getCurrent());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
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

	@Override
	public void remove(Music musicRemove) {
		for (Music music : this) {
			if (music.getPosition().equals(musicRemove.getPosition()) && music.getPosition() != currentNum) {
				super.remove(music);
				for (PlaylistListener listener : listeners) {
					listener.onRemove(music);
				}
				break;
			}
		}

	}

	@Override
	public void updateTime(long newTime) {
		logger.trace("updateTime "+ newTime);
		for (PlaylistListener listener : listeners) {
			listener.ontimeChanged(newTime);
		}
	}

}
