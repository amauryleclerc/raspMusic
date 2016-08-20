package fr.aleclerc.rasp.music.player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.aleclerc.rasp.music.api.Player;
import fr.aleclerc.rasp.music.api.PlayerListener;
import fr.aleclerc.rasp.music.api.PlayerState;
import fr.aleclerc.rasp.music.api.exceptions.PlayerException;
import fr.aleclerc.rasp.music.api.pojo.Music;
import fr.aleclerc.rasp.music.player.factory.MusicImplFactory;

@Service
public class PlayerImpl implements Player {



	private List<PlayerListener> listeners = new ArrayList<PlayerListener>();
	private PlayerState state = PlayerState.STOP;
	private int currentNum = -1;
	private int nbMusic = 0;
	private long lastPercentage = 0;
	private Playlist playlist;
	
	@PostConstruct
	public void init(){
		playlist = new Playlist();
	}
	
	@Override
	public void stop() throws PlayerException {
		this.getCurrentMedia().getMediaPlayer().stop();
		for (PlayerListener listener : listeners) {
			listener.onStop();
		}
		this.state = PlayerState.STOP;
	}

	@Override
	public void play() throws PlayerException {
		this.getCurrentMedia().getMediaPlayer().play();
		for (PlayerListener listener : listeners) {
			listener.onPlay(this.getCurrentMedia().getMusic());
		}
		this.state = PlayerState.PLAY;
	}

	@Override
	public void pause() throws PlayerException  {

		this.getCurrentMedia().getMediaPlayer().pause();

		for (PlayerListener listener : listeners) {
			listener.onPause();
		}
		this.state = PlayerState.PAUSE;
	}

	@Override
	public void next() throws PlayerException  {
		if (this.getCurrentMedia() != null
				&& this.getCurrentMedia().getMediaPlayer().isPlaying()) {
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

		if (this.getCurrentMedia() != null
				&&  this.getCurrentMedia().getMediaPlayer().isPlaying()) {
			this.stop();
		}
		if (this.setCurrentNum(this.getCurrentNum() + 1) && forcePlay) {
			this.play();
		}
	}

	@Override
	public void previous() throws PlayerException {

		if (this.getCurrent() != null
				&&  this.getCurrentMedia().getMediaPlayer().isPlaying()) {
			this.stop();
			if (this.setCurrentNum(this.getCurrentNum() - 1)) {
				this.play();
			}
		} else {
			this.setCurrentNum(this.getCurrentNum() - 1);
		}

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

	@Override
	public void changeTime(Long time) throws PlayerException {
	 this.getCurrentMedia().getMediaPlayer().setTime(time);

	}


	@Autowired
	private MusicImplFactory musicFactory;

	public boolean add(Music newmusic) {
		Media music = musicFactory.getInstance(newmusic);
		music.setPosition(nbMusic);
		nbMusic++;
		playlist.add(music);

		if (currentNum == -1) {
			currentNum++;
		}
		for (PlayerListener listener : listeners) {
			listener.onAdd(music.getMusic());
		}
		return true;

	}

	private Media getCurrentMedia() throws PlayerException {
	
		if (currentNum > -1 && currentNum < playlist.size()) {
			return playlist.get(currentNum);
		} else {
		
			throw new PlayerException("pas de music");
		}
	}

	private boolean setCurrentNum(Integer num) {
	
		if (num >= 0 && num < playlist.size()) {
			this.currentNum = num;
			if (this.currentNum > 4) {
				Media media = playlist.remove(0);
				this.currentNum--;
				for (PlayerListener listener : listeners) {
					listener.onRemove(media.getMusic());
				}
			}

			try {
				for (PlayerListener listener : listeners) {
					listener.onChangeCurrent(this.getCurrentMedia().getMusic());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}

	private int getCurrentNum() {
		return this.currentNum;
	}

	
	@Override
	public void remove(Music musicRemove) {
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
		return this.getCurrentMedia().getMusic();
	}

	@Override
	public List<Music> getPlaylist() {
		return this.playlist.stream().map(media -> media.getMusic()).collect(Collectors.toList());
	}


}
