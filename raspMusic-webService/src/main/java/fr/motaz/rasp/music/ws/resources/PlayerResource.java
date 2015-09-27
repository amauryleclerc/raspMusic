package fr.motaz.rasp.music.ws.resources;

import java.io.File;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import fr.motaz.rasp.music.Player;
import fr.motaz.rasp.music.impl.MusicImpl;
import fr.motaz.rasp.music.model.Music;

@Path("/player")
public class PlayerResource {

	@Autowired
	Player player;

	@POST
	@Path("/add")
	@Produces("application/json")
	public Response addMusic() throws Exception{
		Music music = new MusicImpl();
		File file = new File("D:\\musique\\musique_itunes\\Music\\Bonobo\\Black Sands\\1-02 Kiara.mp3");
		music.setFile(file);
		player.addMusic(music);
		return Response.ok().build();
	}
	
	@POST
	@Path("/play")
	@Produces("application/json")
	public Music play() throws Exception{
		player.play();
		return player.getCurrentMusic();
	}
	
	@POST
	@Path("/stop")
	public Response stop() throws Exception{
		player.stop();
		return Response.ok().build();
	}
	@POST
	@Path("/pause")
	public Response pause() throws Exception{
		player.pause();
		return Response.ok().build();
	}
}
