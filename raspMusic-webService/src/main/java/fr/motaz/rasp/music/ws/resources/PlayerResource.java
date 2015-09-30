package fr.motaz.rasp.music.ws.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import fr.motaz.rasp.music.Player;
import fr.motaz.rasp.music.model.Music;
import fr.motaz.rasp.music.ws.util.MapperUtil;

@Path("/player")
public class PlayerResource {

	@Autowired
	private Player player;

	@PUT
	@Path("/add")
	@Consumes("application/json")
	public Response addMusic(String musicStr) throws Exception {
		Music music = MapperUtil.readAsObjectOf(Music.class, musicStr);
		player.addMusic(music);
		return Response.ok().build();
	}

	@POST
	@Path("/play")
	@Produces("application/json")
	public Music play() throws Exception {
		player.play();
		return player.getCurrentMusic();
	}

	@POST
	@Path("/stop")
	public Response stop() throws Exception {

		player.stop();

		return Response.ok().build();
	}

	@POST
	@Path("/pause")
	public Response pause() throws Exception {

		player.pause();

		return Response.ok().build();
	}

	@POST
	@Path("/previous")
	public Response previous() throws Exception {

		player.previous();

		return Response.ok().build();
	}

	@POST
	@Path("/next")
	public Response next() throws Exception {

		player.next();

		return Response.ok().build();
	}

	@GET
	@Path("/current")
	public Music getCurrent() throws Exception {
		return player.getCurrentMusic();
	}
}
