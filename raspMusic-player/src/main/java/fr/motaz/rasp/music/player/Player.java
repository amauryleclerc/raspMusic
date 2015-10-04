package fr.motaz.rasp.music.player;

public interface Player extends PlayerListenerRegistry{
	public void play() throws Exception;
	public void pause() throws Exception;
	public void stop() throws Exception;
	public void next() throws Exception;
	public void previous() throws Exception;
	public Playlist getPlaylist();
	public PlayerState getState();
}
