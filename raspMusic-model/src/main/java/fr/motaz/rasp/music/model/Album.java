package fr.motaz.rasp.music.model;

import java.util.ArrayList;
import java.util.List;

public class Album {
	private String nom;
	private Artist artiste;
	private List<Music> musicList= null ;
	
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
	public void addMusic(Music music){
		if(this.musicList == null){
			this.musicList = new ArrayList<Music>();
		}
		this.musicList.add(music);
	}
	
}
