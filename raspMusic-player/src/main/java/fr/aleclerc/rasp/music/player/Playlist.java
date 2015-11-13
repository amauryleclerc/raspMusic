package fr.aleclerc.rasp.music.player;

import fr.aleclerc.rasp.music.model.Music;

public interface Playlist extends PlaylistListenerRegistry {
	public boolean add(Music music);
	public Music getCurrent() throws Exception;
	public int getCurrentNum();
	public void remove(Music music);
}
