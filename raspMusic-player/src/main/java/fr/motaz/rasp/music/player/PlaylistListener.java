package fr.motaz.rasp.music.player;

import fr.motaz.rasp.music.model.Music;

public interface PlaylistListener {

	public void onAdd(Music music);
	public void onRemove(Music music);
	public void onChangeCurrent(Music music);
}
