package fr.aleclerc.rasp.music.storage.music;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.aleclerc.rasp.music.api.pojo.Artist;
import fr.aleclerc.rasp.music.api.pojo.Music;
import fr.aleclerc.rasp.music.storage.IStorage;
import fr.aleclerc.rasp.music.storage.config.RaspConf;
import fr.aleclerc.rasp.music.storage.exception.StorageException;

@Component
public class MusicStorage implements IStorage<Music> {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MusicFactory musicFactory;
	
	private static List<Music> musics = null;

	public MusicStorage() throws StorageException {
		if (musics == null) {
			musics = new ArrayList<Music>();
		}

	}

	@Override
	public List<Music> getAll() {
		return musics;
	}

	@Override
	public void add(Music music) {
		musics.add(music);
	}
	
	public List<Music> getMusicsFromArtist(Artist artist) {
		
		return this.getAll().stream().filter(music -> music.getArtist().equals(artist)).collect(Collectors.toList());
		
	
	}

	@PostConstruct
	private void searchMusic() throws StorageException{
		LOGGER.trace("Search music");
		try {
			Files.walk(Paths.get(RaspConf.getPropValue("music.folder.path")))//
			.filter(filePath -> Files.isRegularFile(filePath))//
			.peek(filePath -> LOGGER.info("Music find : {} ",filePath))
			.map(filePath -> new File(filePath.toUri()))
			.map(uri ->  musicFactory.getIntance(uri))//
			.filter(music -> music.isPresent())//
			.forEach(music -> this.add(music.get()));
		} catch (IOException e) {
			new StorageException(e);
		}
	}
}
