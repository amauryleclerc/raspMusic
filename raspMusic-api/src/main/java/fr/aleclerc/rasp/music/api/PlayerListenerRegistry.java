package fr.aleclerc.rasp.music.api;

public interface PlayerListenerRegistry {
	public void addPlayerListener(PlayerListener listener);
	public void removePlayerListener(PlayerListener listener);
	
}
