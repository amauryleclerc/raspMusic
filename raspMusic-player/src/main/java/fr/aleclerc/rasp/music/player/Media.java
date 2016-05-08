package fr.aleclerc.rasp.music.player;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fr.aleclerc.rasp.music.api.pojo.Music;
import uk.co.caprica.vlcj.player.MediaPlayer;

public class Media  {
	
	private Music music;
	private Integer position;
	@JsonIgnore
	private transient MediaPlayer mediaPlayer = null;

	public Media(MediaPlayer mediaPlayer){
		this.mediaPlayer = mediaPlayer;
	}
	@JsonIgnore
	public MediaPlayer getMediaPlayer() {
		return this.mediaPlayer;
	
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
