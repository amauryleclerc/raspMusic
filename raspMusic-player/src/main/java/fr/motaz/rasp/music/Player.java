package fr.motaz.rasp.music;

import javafx.scene.media.Media;

public interface Player {
	public void play();
	public void pause();
	public void stop();
	public void addMusic(Music musique);
	public void next();
	public void previous();
	public void addListener(PlayerListener listener);
	public Media getCurrentMusic();
}
