package fr.aleclerc.rasp.music.storage.artist;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fr.aleclerc.rasp.music.model.Artist;
import fr.aleclerc.rasp.music.storage.Storage;

@Component
@Scope("singleton")
public class ArtistStorage implements Storage<Artist>{

	private List<Artist> artists = null;
	
	public  ArtistStorage() {
		artists = new ArrayList<Artist>();
	}
	@Override
	public List<Artist> getAll() {
		return artists;
	}

	@Override
	public void add(Artist artist) {
		artists.add(artist);
	}
	public Artist getArtist(String name){
		for(Artist artist : artists){
			if(artist.getName().equals(name)){
				return artist;
			}
		}
		return null;
		
	}

}
