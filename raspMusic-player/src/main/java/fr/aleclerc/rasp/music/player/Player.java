package fr.aleclerc.rasp.music.player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.aleclerc.rasp.music.api.AMedia;
import fr.aleclerc.rasp.music.api.EPlayerState;
import fr.aleclerc.rasp.music.api.IPlayer;
import fr.aleclerc.rasp.music.api.IPlayerListener;
import fr.aleclerc.rasp.music.api.exceptions.PlayerException;
import fr.aleclerc.rasp.music.api.pojo.Music;
import fr.aleclerc.rasp.music.player.factory.MediaFactory;

@Service
public class Player implements IPlayer {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	private List<IPlayerListener> listeners = new ArrayList<IPlayerListener>();
	private EPlayerState state = EPlayerState.STOP;
	private int currentNum = -1;
	private int nbMusic = 0;
	private long lastPercentage = 0;
	private Playlist playlist;

	@Autowired
	private MediaFactory musicFactory;

	@PostConstruct
	public void init() {
		playlist = new Playlist();
	}

	@Override
	public void stop() throws PlayerException {
		LOGGER.trace("Stop");
		this.getCurrentMedia().getMediaPlayer().stop();
		for (IPlayerListener listener : listeners) {
			listener.onStop();
		}
		this.state = EPlayerState.STOP;
	}

	@Override
	public void play() throws PlayerException {
		LOGGER.trace("play");
		this.getCurrentMedia().getMediaPlayer().play();
		listeners.stream().forEach(l -> l.onPlay(this.getCurrentMedia()));
		this.state = EPlayerState.PLAY;
	}

	@Override
	public void pause() throws PlayerException {
		LOGGER.trace("pause");
		this.getCurrentMedia().getMediaPlayer().pause();

		for (IPlayerListener listener : listeners) {
			listener.onPause();
		}
		this.state = EPlayerState.PAUSE;
	}

	@Override
	public void next() throws PlayerException {
		LOGGER.trace("next");
		if (this.getCurrentMedia() != null && this.getCurrentMedia().getMediaPlayer().isPlaying()) {
			this.stop();
			if (this.setCurrentNum(this.getCurrentNum() + 1)) {
				this.play();
			}
		} else {
			this.setCurrentNum(this.getCurrentNum() + 1);
		}

	}

	@Override
	public void next(boolean forcePlay) throws PlayerException {
		LOGGER.trace("next");
		this.stop();
		if (this.setCurrentNum(this.getCurrentNum() + 1) && forcePlay) {
			this.play();
		}
	}

	@Override
	public void previous() throws PlayerException {
		LOGGER.trace("previous");
		if (this.getCurrent() != null && this.getCurrentMedia().getMediaPlayer().isPlaying()) {
			this.stop();
			if (this.setCurrentNum(this.getCurrentNum() - 1)) {
				this.play();
			}
		} else {
			this.setCurrentNum(this.getCurrentNum() - 1);
		}

	}

	@Override
	public void addPlayerListener(IPlayerListener listener) {
		LOGGER.trace("addPlayerListener");
		listeners.add(listener);

	}

	@Override
	public void removePlayerListener(IPlayerListener listener) {
		LOGGER.trace("removePlayerListener");
		listeners.remove(listener);
	}

	@Override
	public EPlayerState getState() {
		LOGGER.trace("getState");
		return this.state;
	}

	@Override
	public void changeTime(Long time) throws PlayerException {
		LOGGER.trace("changeTime");
		this.getCurrentMedia().getMediaPlayer().setTime(time);

	}

	public boolean add(Music newmusic) {
		LOGGER.trace("add");
		Media media = musicFactory.getInstance(newmusic);
		media.setPosition(nbMusic);
		nbMusic++;
		playlist.add(media);

		if (currentNum == -1) {
			currentNum++;
		}
		listeners.stream().forEach(l -> l.onAdd(media));
		return true;

	}

	public Media getCurrentMedia() throws RuntimeException {
		LOGGER.trace("getCurrentMedia");
		if (currentNum > -1 && currentNum < playlist.size()) {
			return playlist.get(currentNum);
		} else {

			throw new RuntimeException("pas de music");
		}
	}

	private boolean setCurrentNum(Integer num) {
		LOGGER.trace("setCurrentNum");
		if (num >= 0 && num < playlist.size()) {
			this.currentNum = num;
		/*	if (this.currentNum > 4) {
				Media media = playlist.remove(0);
				this.currentNum--;
				listeners.stream().forEach(l -> l.onRemove(media));
			}*/

			listeners.stream().forEach(l -> l.onChangeCurrent(this.getCurrentMedia()));

			return true;
		}
		return false;
	}

	private int getCurrentNum() {
		LOGGER.trace("getCurrentNum");
		return this.currentNum;
	}

	@Override
	public void remove(Music musicRemove) {
		LOGGER.trace("remove");
		/*
		 * for (MusicImpl<? extends Music> music : this) { if
		 * (music.getPosition().equals(musicRemove.getPosition()) &&
		 * music.getPosition() != currentNum) { super.remove(music); for
		 * (PlaylistListener listener : listeners) { listener.onRemove(music); }
		 * break; } }
		 */

	}

	@Override
	public void updateTime(final long newTime) {
		LOGGER.trace("updateTime");
		long percentageLocal = (long) 0;
		long lengthLocal = (long) 0;
		try {
			Media media = this.getCurrentMedia();
			media.getMusic().setCurrentTime(newTime);
			lengthLocal = media.getMediaPlayer().getLength();
			percentageLocal = 100 * newTime / lengthLocal;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (percentageLocal != lastPercentage) {
			lastPercentage = percentageLocal;
			final long percentage = percentageLocal;
			final long length = lengthLocal;
			listeners.stream().forEach(listener -> listener.ontimeChanged(newTime, percentage, length));
		}

	}

	@Override
	public Music getCurrent() throws PlayerException {
		LOGGER.trace("getCurrent");
		return this.getCurrentMedia().getMusic();
	}

	@Override
	public List<Music> getPlaylist() {
		LOGGER.trace("getPlaylist");
		return this.playlist.stream().map(media -> media.getMusic()).collect(Collectors.toList());
	}

	@Override
	public List<AMedia> getMediaPlaylist() {
		LOGGER.trace("getPlaylist");
		return this.playlist.stream().map(media -> (AMedia) media).collect(Collectors.toList());
	}

}
