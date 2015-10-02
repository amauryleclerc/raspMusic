package fr.motaz.rasp.music.player;

import java.util.List;

import fr.motaz.rasp.music.model.Music;

public interface Player {
	public void play() throws Exception;
	public void pause() throws Exception;
	public void stop() throws Exception;
//	public void addMusic(Music music) throws Exception;
	public void next() throws Exception;
	public void previous() throws Exception;
	public void addListener(PlayerListener listener);
	public void removeListener(PlayerListener listener);
//	public Music getCurrentMusic() throws Exception;
	public Playlist getPlaylist();
}