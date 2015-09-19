package fr.motaz.rasp.music.impl.factory;

import java.io.File;

import fr.motaz.rasp.music.Musique;
import fr.motaz.rasp.music.impl.bean.MusiqueImpl;

public class MusiqueFactory {
public static Musique getInstance (String artiste, String titre, String album, File file){
	Musique musique = new MusiqueImpl();
	musique.setArtiste(artiste);
	musique.setAlbum(album);
	musique.setTitre(titre);
	musique.setFile(file);
	return musique;
}
public static Musique getInstance ( File file){
	Musique musique = new MusiqueImpl();
	musique.setFile(file);
	return musique;
}
}