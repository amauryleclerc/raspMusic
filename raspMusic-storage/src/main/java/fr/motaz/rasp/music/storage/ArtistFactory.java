package fr.motaz.rasp.music.storage;

import fr.motaz.rasp.music.model.Artist;

public class ArtistFactory {

	public static Artist getIntance(String name) {
		Artist artist = new Artist();
		artist.setName(name);
		return artist;
	}
}
