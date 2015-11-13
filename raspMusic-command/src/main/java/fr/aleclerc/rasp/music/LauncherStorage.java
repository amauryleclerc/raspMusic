package fr.aleclerc.rasp.music;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.aleclerc.rasp.music.model.Music;
import fr.aleclerc.rasp.music.storage.StorageService;

public class LauncherStorage {
	private static StorageService storageService;
	 
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
