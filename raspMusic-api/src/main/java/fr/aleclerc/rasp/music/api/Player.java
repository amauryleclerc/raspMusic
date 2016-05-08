package fr.aleclerc.rasp.music.api;

import java.util.List;

import fr.aleclerc.rasp.music.api.exceptions.PlayerException;
import fr.aleclerc.rasp.music.api.pojo.Music;

public interface Player extends PlayerListenerRegistry{
	public void play() throws PlayerException;
	public void pause() throws PlayerException;
	public void stop() throws PlayerException;
	public void next() throws PlayerException;
	public void previous() throws PlayerException;
	public PlayerState getState()  throws PlayerException;
	public void changeTime(Long time) throws PlayerException;
	public void next(boolean b) throws PlayerException;
	public boolean add(Music music)  throws PlayerException;
	public Music getCurrent() throws PlayerException;
	public void remove(Music music)  throws PlayerException;
	public void updateTime(long newTime)  throws PlayerException;
	public List<Music> getPlaylist();
}
