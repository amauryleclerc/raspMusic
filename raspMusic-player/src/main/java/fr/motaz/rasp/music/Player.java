package fr.motaz.rasp.music;

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
