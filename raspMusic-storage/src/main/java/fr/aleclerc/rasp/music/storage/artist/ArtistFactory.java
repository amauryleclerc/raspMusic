package fr.aleclerc.rasp.music.storage.artist;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.aleclerc.rasp.music.api.pojo.Artist;

@Component
public class ArtistFactory {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private  ArtistStorage artistStorage;
	
	public  Artist getIntance(String name) {
		LOGGER.debug("search {}", name);
		Artist artist;
		Optional<Artist> a = artistStorage.getArtist(name);
		if(!a.isPresent()){
			artist = new Artist();
			artist.setName(name);
			artistStorage.add(artist);
		}else{
			artist = a.get();
		}
		
		
		return artist;
	}
}
