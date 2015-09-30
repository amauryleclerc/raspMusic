package fr.motaz.rasp.music.storage;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import fr.motaz.rasp.music.model.Music;

public class StorageService {
	public List<Music> getMusicList() throws Exception{
		List<Music> musics = new ArrayList<Music>();
		Files.walk(Paths.get(RaspConf.getPropValue("music.folder.path"))).forEach(filePath -> {
		    if (Files.isRegularFile(filePath)) {
		    	System.out.println(filePath.toUri());
		    	try {
					musics.add(new Music(new File(filePath.toUri())));
					System.out.println("add");
				} catch (Exception e) {
					System.out.println("exception");
				}
		    }
		});
		return musics;
		
	}
	
	
}
