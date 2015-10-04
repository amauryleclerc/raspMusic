package fr.motaz.rasp.music;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.motaz.rasp.music.model.Music;
import fr.motaz.rasp.music.storage.StorageService;

public class LauncherStorage {
	private static StorageService storageService;
	static final Logger logger = LogManager.getLogger(StorageService.class);
	 
	public static void main(String[] args) throws Exception {
	
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "classpath:applicationContext-player.xml", "classpath:applicationContext-storage.xml" })) {
			storageService = (StorageService) context.getBean("storageService");
			List<Music> musics = storageService.getMusicList();
			for(Music music : musics){
				System.out.println(music.getTitle()+" - "+music.getArtist().getName());
			}
	
		}

	}

}
