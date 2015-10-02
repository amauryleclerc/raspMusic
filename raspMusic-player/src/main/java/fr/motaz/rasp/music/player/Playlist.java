package fr.motaz.rasp.music.player;

import fr.motaz.rasp.music.model.Music;

public interface Playlist {
	public boolean add(Music music);
	public Music getCurrent() throws Exception;
}
