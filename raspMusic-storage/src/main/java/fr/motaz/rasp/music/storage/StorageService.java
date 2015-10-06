package fr.motaz.rasp.music.storage;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.motaz.rasp.music.model.Music;

public class StorageService {
	protected  static final Logger logger = LogManager.getLogger(StorageService.class);
	
	public List<Music> getMusicList() throws Exception {
		logger.trace("getMusicList");
		List<Music> musics = new ArrayList<Music>();
		Files.walk(Paths.get(RaspConf.getPropValue("music.folder.path"))).forEach(filePath -> {
			if (Files.isRegularFile(filePath)) {
				try {
					musics.add(new Music(new File(filePath.toUri())));
					logger.info("add : "+filePath.toUri());
				} catch (Exception e) {
					logger.debug("exception : "+filePath.toUri());
				}
			}
		});
		return musics;

	}

}
