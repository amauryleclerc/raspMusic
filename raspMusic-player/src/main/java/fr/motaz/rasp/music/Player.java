package fr.motaz.rasp.music;

import fr.motaz.rasp.music.model.Music;

public interface Player {
	public void play() throws Exception;
	public void pause() throws Exception;
	public void stop() throws Exception;
	public void addMusic(Music musique);
	public void next() throws Exception;
	public void previous() throws Exception;
	public void addListener(PlayerListener listener);
	public Music getCurrentMusic() throws Exception;
}
