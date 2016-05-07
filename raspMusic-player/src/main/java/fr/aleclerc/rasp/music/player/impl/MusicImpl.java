package fr.aleclerc.rasp.music.player.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fr.aleclerc.rasp.music.model.Music;
import uk.co.caprica.vlcj.player.MediaPlayer;

public class MusicImpl extends Music {
	@JsonIgnore
	private transient MediaPlayer mediaPlayer = null;

	public MusicImpl(MediaPlayer mediaPlayer2){
		this.mediaPlayer = mediaPlayer2;
	}
	@JsonIgnore
	public MediaPlayer getMediaPlayer() {
		return this.mediaPlayer;
	
	}

}
