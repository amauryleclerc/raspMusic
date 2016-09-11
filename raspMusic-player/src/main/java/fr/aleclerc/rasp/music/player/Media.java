package fr.aleclerc.rasp.music.player;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fr.aleclerc.rasp.music.api.AMedia;
import uk.co.caprica.vlcj.player.MediaPlayer;

public class Media extends AMedia {
	
	

	@JsonIgnore
	private final transient MediaPlayer mediaPlayer;

	public Media(MediaPlayer mediaPlayer){
		super();
		this.mediaPlayer = mediaPlayer;
	}
	@JsonIgnore
	public MediaPlayer getMediaPlayer() {
		return this.mediaPlayer;
	
	}

}
