package fr.motaz.rasp.music.model;

import java.util.List;

public class Album {
	private String nom;
	private Artist artiste;
	private List<Music> musicList;
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Artist getArtiste() {
		return artiste;
	}
	public void setArtiste(Artist artiste) {
		this.artiste = artiste;
	}
	public List<Music> getMusicList() {
		return musicList;
	}
	public void setMusicList(List<Music> musicList) {
		this.musicList = musicList;
	}
	
	
}
