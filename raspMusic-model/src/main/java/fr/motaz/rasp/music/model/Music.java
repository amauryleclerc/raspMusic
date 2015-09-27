package fr.motaz.rasp.music.model;

import java.io.File;

public abstract class Music {
	private String title;
	private Album album;
	private Artist artist;

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

	public abstract void setFile(File file);

}
