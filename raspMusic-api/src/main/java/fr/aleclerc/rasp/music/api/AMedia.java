package fr.aleclerc.rasp.music.api;

import java.util.UUID;

import fr.aleclerc.rasp.music.api.pojo.Music;

public abstract class AMedia {
	private final UUID id;
	private Music music;
	private Integer position;

	public AMedia(){
		this.id = UUID.randomUUID();
	}
	
	public UUID getId() {
		return id;
	}

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
