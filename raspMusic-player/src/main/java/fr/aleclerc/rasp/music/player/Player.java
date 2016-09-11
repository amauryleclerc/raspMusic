package fr.aleclerc.rasp.music.player;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.aleclerc.rasp.music.api.AMedia;
import fr.aleclerc.rasp.music.api.EPlayerState;
import fr.aleclerc.rasp.music.api.IPlayer;
import fr.aleclerc.rasp.music.api.exceptions.PlayerException;
import fr.aleclerc.rasp.music.api.pojo.Music;
import fr.aleclerc.rasp.music.api.utils.Tuple;
import fr.aleclerc.rasp.music.player.factory.MediaFactory;
import rx.Observable;
import rx.subjects.BehaviorSubject;

@Service
public class Player implements IPlayer {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	private EPlayerState state;
	private int currentNum = -1;
	private int nbMusic = 0;
	private final Playlist playlist;
	private final BehaviorSubject<EPlayerState> stateSubject;
	private final BehaviorSubject<Tuple<Long, Long>> timeSubject;
	private final BehaviorSubject<Media> currentMediaSubject;
	private final BehaviorSubject<List<AMedia>> playlistSubject;

	@Autowired
	private MediaFactory musicFactory;

	public Player() {
		this.playlist = new Playlist();
		this.state = EPlayerState.STOP;
		this.stateSubject = BehaviorSubject.create();
		this.timeSubject = BehaviorSubject.create();
		this.currentMediaSubject = BehaviorSubject.create();
		this.playlistSubject = BehaviorSubject.create();
	}
	private List<AMedia> playlistToList(){
		return playlist.stream()//
				.map(m -> (AMedia) m)//
				.collect(Collectors.toList());
	}

	@Override
	public void stop() throws PlayerException {
		LOGGER.trace("Stop");
		this.getCurrentMedia().getMediaPlayer().stop();
		this.setState(EPlayerState.STOP);
	}

	@Override
	public void play() throws PlayerException {
		LOGGER.trace("play");
		this.getCurrentMedia().getMediaPlayer().play();
		this.setState(EPlayerState.PLAY);
	}

	@Override
	public void pause() throws PlayerException {
		LOGGER.trace("pause");
		this.getCurrentMedia().getMediaPlayer().pause();
		this.setState(EPlayerState.PAUSE);
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
		playlistSubject.onNext(playlistToList());
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
			currentMediaSubject.onNext(this.getCurrentMedia());
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
		playlistSubject.onNext(playlistToList());
	}

	@Override
	public void updateTime(final long newTime) {
		LOGGER.trace("updateTime");
		try {
			final Media media = this.getCurrentMedia();
			media.getMusic().setCurrentTime(newTime);
			long lengthLocal = media.getMediaPlayer().getLength();
			LOGGER.trace("updade time : {} / {}",newTime, lengthLocal);
			this.timeSubject.onNext(Tuple.tuple(newTime, lengthLocal));
		} catch (Exception e) {
			LOGGER.error("updateTime error : {}", e.getMessage());
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

	private void setState(EPlayerState state) {
		this.state = state;
		LOGGER.debug(this.state.name());
		this.stateSubject.onNext(this.state);
	}

	@Override
	public Observable<EPlayerState> getStateStream() {
		return this.stateSubject.asObservable();
	}

	@Override
	public Observable<Tuple<Long, Long>> getTimeStream() {
		return this.timeSubject.asObservable();
	}

	@Override
	public Observable<AMedia> getCurrentMediaStream() {
		return this.currentMediaSubject.asObservable().map(m -> (AMedia) m );
	}

	@Override
	public Observable<List<AMedia>> getPlaylistStream() {
		return this.playlistSubject.asObservable();
	}
}
