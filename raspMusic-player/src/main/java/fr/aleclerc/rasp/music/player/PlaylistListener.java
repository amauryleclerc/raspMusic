package fr.aleclerc.rasp.music.player;

import fr.aleclerc.rasp.music.model.Music;

public interface PlaylistListener {

	public void onAdd(Music music);
	public void onRemove(Music music);
	public void onChangeCurrent(Music music);
	public void ontimeChanged(Long currentTime);
}
