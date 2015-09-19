package fr.motaz.rasp.music.impl;

import java.io.File;
import java.io.IOException;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import fr.motaz.rasp.music.Music;
import fr.motaz.rasp.music.impl.handler.PausedHandler;
import fr.motaz.rasp.music.impl.handler.PlayingHandler;
import fr.motaz.rasp.music.impl.handler.StopedHandler;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MusicImpl extends Music {
	private MediaPlayer mediaPlayer;
	
	@Override
	public void setFile(File file) {
		Mp3File mp3file = null;
		try {
			 mp3file = new Mp3File( file);
		} catch (UnsupportedTagException | InvalidDataException | IOException e) {
			e.printStackTrace();
		}
		
		if (mp3file.hasId3v2Tag()) {
			ID3v2 id3v2Tag = mp3file.getId3v2Tag();
			super.setTitre(id3v2Tag.getTitle());
			super.setAlbum(id3v2Tag.getAlbum());
			super.setArtiste(id3v2Tag.getArtist());
		}
		this.setMediaPlayer(this.createMediaPlayer(file));
		super.setFile(file);
	}

	public MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}

	public void setMediaPlayer(MediaPlayer mediaPlayer) {
		this.mediaPlayer = mediaPlayer;
	}
	private MediaPlayer createMediaPlayer(File file) {
		MediaPlayer mediaPlayer = new MediaPlayer(new Media(file.toURI().toString()));
		mediaPlayer.setOnPlaying(new PlayingHandler());
		mediaPlayer.setOnPaused(new PausedHandler());
		mediaPlayer.setOnStopped(new StopedHandler());
		return mediaPlayer;
	}
}
