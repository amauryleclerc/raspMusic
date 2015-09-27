package fr.motaz.rasp.music.model;

import java.io.File;

public abstract class Music {
	private String titre;
	private Album album;
	private Artist artiste;
	
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public Album getAlbum() {
		return album;
	}
	public void setAlbum(Album album) {
		this.album = album;
	}
	public Artist getArtist() {
		return artiste;
	}
	public void setArtist(Artist artiste) {
		this.artiste = artiste;
	}
	public abstract void setFile(File file);
	
	
}
