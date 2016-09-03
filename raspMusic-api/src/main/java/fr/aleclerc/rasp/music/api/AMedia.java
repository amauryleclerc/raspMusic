package fr.aleclerc.rasp.music.api;

import fr.aleclerc.rasp.music.api.pojo.Music;

public abstract class AMedia {
	private Music music;
	private Integer position;
	
	public Music getMusic() {
		return music;
	}
	public void setMusic(Music music) {
		this.music = music;
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}

}
