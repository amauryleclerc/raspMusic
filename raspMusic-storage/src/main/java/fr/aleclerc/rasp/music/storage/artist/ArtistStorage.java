package fr.aleclerc.rasp.music.storage.artist;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import fr.aleclerc.rasp.music.api.pojo.Artist;
import fr.aleclerc.rasp.music.storage.IStorage;

@Component
public class ArtistStorage implements IStorage<Artist>{

	private static List<Artist> artists = null;
	
	public  ArtistStorage() {
		if(artists == null){
			artists = new ArrayList<Artist>();
		}
	}
	@Override
	public List<Artist> getAll() {
		return artists;
	}

	@Override
	public void add(Artist artist) {
		artists.add(artist);
	}
	public Optional<Artist> getArtist(String name){
		return artists.stream().filter(a -> a.getName().equals(name)).findFirst();
			
	}

}
