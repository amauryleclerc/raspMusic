package fr.aleclerc.rasp.music.storage.artist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.aleclerc.rasp.music.api.pojo.Artist;

@Component
public class ArtistFactory {
	@Autowired
	private  ArtistStorage artistStorage;
	
	public  Artist getIntance(String name) {

		Artist artist = artistStorage.getArtist(name);
		if(artist == null){
			artist = new Artist();
			artist.setName(name);
			artistStorage.add(artist);
		}
		
		return artist;
	}
}
