package fr.aleclerc.rasp.music.player;

import fr.aleclerc.rasp.music.model.Music;

public interface PlayerListener {
	public void onPlay(Music music);
	public void onPause();
	public void onStop();
	
}
