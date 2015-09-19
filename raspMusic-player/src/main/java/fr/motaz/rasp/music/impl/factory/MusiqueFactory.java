package fr.motaz.rasp.music.impl.factory;

import java.io.File;

import fr.motaz.rasp.music.Music;
import fr.motaz.rasp.music.impl.MusicImpl;

public class MusiqueFactory {
	public static Music getInstance(String artiste, String titre, String album, File file) {
		Music musique = new MusicImpl();
		musique.setArtiste(artiste);
		musique.setAlbum(album);
		musique.setTitre(titre);
		musique.setFile(file);
		return musique;
	}

	public static Music getInstance(File file) {
		Music musique = new MusicImpl();
		musique.setFile(file);
		return musique;
	}
}