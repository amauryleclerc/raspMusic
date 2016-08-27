package fr.aleclerc.rasp.music;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import fr.aleclerc.rasp.music.api.pojo.Music;
import fr.aleclerc.rasp.music.player.Player;
import fr.aleclerc.rasp.music.storage.exception.StorageException;
import fr.aleclerc.rasp.music.storage.music.MusicStorage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfiguration.class }, loader = AnnotationConfigContextLoader.class)
public class PlayerTest {

	@Autowired
	public Player player;

	@Autowired
	private MusicStorage storageService;

	@Test
	public void test() throws StorageException {
		System.out.println(player.getPlaylist().size());
		

		List<Music> musics = storageService.getAll();
		
		player.add(musics.get(0));
		
	}

}
