package fr.motaz.rasp.music.player.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fr.motaz.rasp.music.model.Music;
import fr.motaz.rasp.music.player.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.direct.DirectMediaPlayer;

public class MusicImpl extends Music{
	@JsonIgnore
	private transient DirectMediaPlayer mediaPlayer = null;

	@JsonIgnore
	public DirectMediaPlayer getMediaPlayer() {
		if (this.mediaPlayer == null) {
			if(super.getId()== null){
				this.mediaPlayer = MediaPlayerFactory.getLocalMediaPlayer(super.getPath());
			}else{
				this.mediaPlayer = MediaPlayerFactory.getYTMediaPlayer(super.getId());
			}
		}
		return this.mediaPlayer;
	}
	

}
