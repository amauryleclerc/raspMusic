package fr.motaz.rasp.music.ws.resources;

import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

import fr.motaz.rasp.music.Music;
import fr.motaz.rasp.music.Player;
import fr.motaz.rasp.music.impl.MusicImpl;

@Path("/player")
public class PlayerResource {

	@Autowired
	Player player;

	@GET
	@Path("/add")
	@Produces("application/json")
	public Music addMusic() throws Exception{
		Music music = new MusicImpl();
		File file = new File("D:\\musique\\musique_itunes\\Music\\Bonobo\\Black Sands\\1-02 Kiara.mp3");
		music.setFile(file);
		player.addMusic(music);
		return player.getCurrentMusic();
	}
	
	@GET
	@Path("/play")
	@Produces("application/json")
	public Music play() throws Exception{
		player.play();
		return player.getCurrentMusic();
	}
	
	@GET
	@Path("/stop")
	public void stop() throws Exception{
		player.stop();
	}
	@GET
	@Path("/pause")
	public void pause() throws Exception{
		player.pause();
	}
}
