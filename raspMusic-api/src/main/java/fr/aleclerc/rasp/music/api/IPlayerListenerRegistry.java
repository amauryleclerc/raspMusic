package fr.aleclerc.rasp.music.api;

public interface IPlayerListenerRegistry {
	public void addPlayerListener(IPlayerListener listener);
	public void removePlayerListener(IPlayerListener listener);
	
}
