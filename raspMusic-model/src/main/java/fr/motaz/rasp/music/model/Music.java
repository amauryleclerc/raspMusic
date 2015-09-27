package fr.motaz.rasp.music.model;

import java.io.File;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Music {
	private String title;
	private Album album;
	private Artist artist;
	private transient MediaPlayer mediaPlayer;

	public Music(File file) {
		Mp3File mp3file = null;
		try {
			mp3file = new Mp3File(file);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (mp3file.hasId3v2Tag()) {
			ID3v2 id3v2Tag = mp3file.getId3v2Tag();
			this.setTitle(id3v2Tag.getTitle());
			Artist artist = new Artist();
			artist.setName(id3v2Tag.getArtist());
			Album album = new Album();
			album.addMusic(this);
			album.setName(id3v2Tag.getAlbum());
			this.setAlbum(album);
			this.setArtist(artist);
		}
		this.setMediaPlayer(this.createMediaPlayer(file));
	}
	public Music() {
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public void setFile(File file) {
		Mp3File mp3file = null;
		try {
			mp3file = new Mp3File(file);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (mp3file.hasId3v2Tag()) {
			ID3v2 id3v2Tag = mp3file.getId3v2Tag();
			this.setTitle(id3v2Tag.getTitle());
			Artist artist = new Artist();
			artist.setName(id3v2Tag.getArtist());
			Album album = new Album();
			album.addMusic(this);
			album.setName(id3v2Tag.getAlbum());
			this.setAlbum(album);
			this.setArtist(artist);
		}
		this.setMediaPlayer(this.createMediaPlayer(file));
	}

	@JsonIgnore
	public MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}

	private void setMediaPlayer(MediaPlayer mediaPlayer) {
		this.mediaPlayer = mediaPlayer;
	}

	private MediaPlayer createMediaPlayer(File file) {
		MediaPlayer mediaPlayer = new MediaPlayer(new Media(file.toURI().toString()));
		return mediaPlayer;
	}

}
