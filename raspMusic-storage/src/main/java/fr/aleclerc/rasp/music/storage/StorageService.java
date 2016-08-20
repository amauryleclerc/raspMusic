package fr.aleclerc.rasp.music.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.aleclerc.rasp.music.api.pojo.Artist;
import fr.aleclerc.rasp.music.api.pojo.Music;
import fr.aleclerc.rasp.music.storage.artist.ArtistStorage;
import fr.aleclerc.rasp.music.storage.config.RaspConf;
import fr.aleclerc.rasp.music.storage.exception.StorageException;
import fr.aleclerc.rasp.music.storage.music.MusicFactory;
import fr.aleclerc.rasp.music.storage.music.MusicStorage;

@Service
public class StorageService {
	
	@Autowired
	private ArtistStorage artistStorage;
	@Autowired
	private MusicStorage musicStorage;
	@Autowired
	private MusicFactory musicFactory;
	

	
	@Deprecated
	public List<Music> getMusicList() throws StorageException {
	
		return musicStorage.getAll();
	}
	@Deprecated
	public List<Artist> getArtistList() throws StorageException {
		
		return artistStorage.getAll();
	}
	
	@PostConstruct
	private void searchMusic() throws StorageException{
	
		try {
			Files.walk(Paths.get(RaspConf.getPropValue("music.folder.path"))).forEach(filePath -> {
				if (Files.isRegularFile(filePath)) {
					try {
						Music music = musicFactory.getIntance(new File(filePath.toUri()));
//						new Music(new File(filePath.toUri()));
						musicStorage.add(music);
						
					} catch (Exception e) {
					
						e.printStackTrace();
					}
				}
			});
		} catch (IOException e) {
			new StorageException(e);
		}
//		Collections.sort(artists);
//		Collections.sort(musics);
	}

	@Deprecated
	public Artist getArtist(String name) {
		for(Artist artist : artistStorage.getAll()){
			if(artist.getName().equals(name)){
				return artist;
			}
		}
		return null;
	}
	
	@Deprecated
	public List<Music> getMusicsFromArtist(Artist artist) {
		List<Music> resultat = new ArrayList<Music>();
		for(Music music : musicStorage.getAll()){
			if(music.getArtist().equals(artist)){
				resultat.add(music);
			}
		}
		return resultat;
	}

	
}
