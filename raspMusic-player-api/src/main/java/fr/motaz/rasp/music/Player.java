package fr.motaz.rasp.music;

import javafx.scene.media.Media;

public interface Player {
	public void play();
	public void pause();
	public void stop();
	public void addMusic(Musique musique);
	public Media getCurrentMusic();
}
