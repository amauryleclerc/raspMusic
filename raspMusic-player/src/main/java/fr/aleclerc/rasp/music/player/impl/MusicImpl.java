package fr.aleclerc.rasp.music.player.impl;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fr.aleclerc.rasp.music.model.Music;
import uk.co.caprica.vlcj.player.direct.DirectMediaPlayer;

public class MusicImpl extends Music {
	@JsonIgnore
	private transient DirectMediaPlayer mediaPlayer = null;

	public MusicImpl(DirectMediaPlayer mediaPlayer){
		this.mediaPlayer = mediaPlayer;
	}
	@JsonIgnore
	public DirectMediaPlayer getMediaPlayer() {
		return this.mediaPlayer;
	
	}

}
