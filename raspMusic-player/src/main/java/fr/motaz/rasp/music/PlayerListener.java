package fr.motaz.rasp.music;

import fr.motaz.rasp.music.model.Music;

public interface PlayerListener {
	public void onPlay(Music music);
	public void onPause();
	public void onAdd(Music music);
	public void onStop();
	
}
