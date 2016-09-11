package fr.aleclerc.rasp.music.api;

import java.util.List;
import java.util.UUID;

import fr.aleclerc.rasp.music.api.exceptions.PlayerException;
import fr.aleclerc.rasp.music.api.pojo.Music;
import fr.aleclerc.rasp.music.api.utils.Tuple;
import rx.Observable;

public interface IPlayer {
	
	public void play() throws PlayerException;
	
	public void pause() throws PlayerException;
	
	public void stop() throws PlayerException;
	
	public void next() throws PlayerException;
	
	public void previous() throws PlayerException;
	
	public EPlayerState getState()  throws PlayerException;
	
	public void changeTime(Long time) throws PlayerException;
	
	public void next(boolean b) throws PlayerException;
	
	public boolean add(Music music)  throws PlayerException;
	
	//public Music getCurrent() throws PlayerException;
	
	public AMedia getCurrent()  throws RuntimeException;
	
	public void updateTime(long newTime)  throws PlayerException;
	
	public List<Music> getPlaylist();
	
	public List<AMedia> getMediaPlaylist();
	
	public Observable<EPlayerState> getStateStream();
	
	public Observable<Tuple<Long,Long>> getTimeStream();
	
	public Observable<AMedia> getCurrentMediaStream();
	
	public Observable<List<AMedia>> getPlaylistStream();

	public void remove(UUID id);

}
