package fr.aleclerc.rasp.music.player;

public interface PlayerListenerRegistry {
	public void addPlayerListener(PlayerListener listener);
	public void removePlayerListener(PlayerListener listener);
	
}
