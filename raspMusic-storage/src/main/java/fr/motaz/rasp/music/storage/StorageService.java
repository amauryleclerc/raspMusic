package fr.motaz.rasp.music.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.motaz.rasp.music.model.Artist;
import fr.motaz.rasp.music.model.Music;

public class StorageService {
	protected  static final Logger logger = LogManager.getLogger(StorageService.class);
	private List<Music> musics; 
	private List<Artist> artists;
	
	public StorageService() throws StorageException{
		musics = new ArrayList<Music>();
		artists =   new ArrayList<Artist>();
		this.searchMusic();
	}
	
	public List<Music> getMusicList() throws StorageException {
		logger.trace("getMusicList");
		return musics;
	}
	public List<Artist> getArtistList() throws StorageException {
		logger.trace("getMusicList");
		return artists;
	}
	private void searchMusic() throws StorageException{
		logger.trace("searchMusic");
		try {
			Files.walk(Paths.get(RaspConf.getPropValue("music.folder.path"))).forEach(filePath -> {
				if (Files.isRegularFile(filePath)) {
					try {
						Music music = new Music(new File(filePath.toUri()));
						musics.add(music);
						artists.add(music.getArtist());
						logger.info("add : "+filePath.toUri());
					} catch (Exception e) {
						logger.debug("exception : "+filePath.toUri());
						
					}
				}
			});
		} catch (IOException e) {
			new StorageException(e);
		}
//		Collections.sort(artists);
//		Collections.sort(musics);
	}

	public Artist getArtist(String name) {
		for(Artist artist : artists){
			if(artist.getName().equals(name)){
				return artist;
			}
		}
		return null;
	}
	public List<Music> getMusicsFromArtist(Artist artist) {
		List<Music> resultat = new ArrayList<Music>();
		for(Music music : musics){
			if(music.getArtist().equals(artist)){
				resultat.add(music);
			}
		}
		return resultat;
	}

	
}
