package fr.aleclerc.rasp.music.player;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
	private Optional<UUID> current = Optional.empty();
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

	private List<AMedia> playlistToList() {
		return playlist.stream()//
				.map(m -> (AMedia) m)//
				.collect(Collectors.toList());
	}

	@Override
	public void stop() throws PlayerException {
		LOGGER.trace("Stop");
		if (this.getCurrentMedia().isPresent()) {
			this.getCurrentMedia().get().getMediaPlayer().stop();
		}
		this.setState(EPlayerState.STOP);
	}

	@Override
	public void play() throws PlayerException {
		LOGGER.trace("play");
		if (this.getCurrentMedia().isPresent()) {
			this.getCurrentMedia().get().getMediaPlayer().play();
		}
		this.setState(EPlayerState.PLAY);
	}

	@Override
	public void pause() throws PlayerException {
		LOGGER.trace("pause");
		this.getCurrentMedia().get().getMediaPlayer().pause();
		this.setState(EPlayerState.PAUSE);
	}

	@Override
	public void next() throws PlayerException {
		LOGGER.trace("next");
		if (this.getCurrentMedia().isPresent()) {
			int num = playlist.indexOf(this.getCurrentMedia().get()) + 1;
			if (this.getCurrentMedia().get().getMediaPlayer().isPlaying()) {
				this.stop();
				if (setCurrent(num)) {
					this.play();
				}
			} else {
				setCurrent(num);
			}
		}

	}

	@Override
	public void next(boolean forcePlay) throws PlayerException {
		LOGGER.trace("next");
		this.stop();
		int num = playlist.indexOf(this.getCurrentMedia().get()) + 1;
		setCurrent(num);
		if (this.getCurrentMedia().isPresent() && forcePlay) {
			this.play();
		}
	}

	@Override
	public void previous() throws PlayerException {
		LOGGER.trace("previous");
		if (this.getCurrentMedia().isPresent()) {
			int num = playlist.indexOf(this.getCurrentMedia().get()) - 1;
			if (this.getCurrentMedia().get().getMediaPlayer().isPlaying()) {
				this.stop();
				if (setCurrent(num)) {
					this.play();
				}
			} else {
				setCurrent(num);
			}
		}

	}

	private boolean setCurrent(int num) {
		if (playlist.size() > num && num > -1) {
			current = Optional.of(this.playlist.get(num).getId());
			currentMediaSubject.onNext(getCurrentMedia().get());
			return true;
		}
		return false;
	}

	@Override
	public EPlayerState getState() {
		LOGGER.trace("getState");
		return this.state;
	}

	@Override
	public void changeTime(Long time) throws PlayerException {
		LOGGER.trace("changeTime");
		if (this.getCurrentMedia().isPresent()) {
			this.getCurrentMedia().get().getMediaPlayer().setTime(time);
		}
		this.getCurrentMedia().get().getMediaPlayer().setTime(time);

	}

	public boolean add(Music newmusic) {
		LOGGER.trace("add");
		Media media = musicFactory.getInstance(newmusic);
		media.setPosition(nbMusic);
		nbMusic++;
		playlist.add(media);
		if (!current.isPresent()) {
			current = Optional.of(media.getId());
			currentMediaSubject.onNext(this.getCurrentMedia().get());
		}
		playlistSubject.onNext(playlistToList());
		return true;

	}

	private Optional<Media> getCurrentMedia() {
		LOGGER.trace("getCurrentMedia");

		if (current.isPresent()) {
			return this.playlist.stream()//
					.filter(m -> m.getId().equals(current.get()))//
					.findFirst();
		} else {
			return Optional.empty();
		}
	}

	@Override
	public void updateTime(final long newTime) {
		LOGGER.trace("updateTime");
		try {
			final Media media = (Media) this.getCurrentMedia().get();
			media.getMusic().setCurrentTime(newTime);
			long lengthLocal = media.getMediaPlayer().getLength();
			LOGGER.trace("updade time : {} / {}", newTime, lengthLocal);
			this.timeSubject.onNext(Tuple.tuple(newTime, lengthLocal));
		} catch (Exception e) {
			LOGGER.error("updateTime error : {}", e.getMessage());
		}

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
		return this.currentMediaSubject.asObservable().map(m -> (AMedia) m);
	}

	@Override
	public Observable<List<AMedia>> getPlaylistStream() {
		return this.playlistSubject.asObservable();
	}

	@Override
	public void remove(UUID id) {
		List<Media> medias = playlist.stream()//
				.filter(m -> m.getId().equals(id))//
				.collect(Collectors.toList());
		LOGGER.debug("remove {}", medias);
		this.playlist.removeAll(medias);
		playlistSubject.onNext(this.playlistToList());
	}

	@Override
	public AMedia getCurrent() throws RuntimeException {
		return getCurrentMedia().get();
	}
}
