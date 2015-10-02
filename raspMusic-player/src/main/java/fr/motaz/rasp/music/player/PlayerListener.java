package fr.motaz.rasp.music.player;

import fr.motaz.rasp.music.model.Music;

public interface PlayerListener {
	public void onPlay(Music music);
	public void onPause();
	public void onStop();
	
}
