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
	public Music play() {
		Music result = null;
		try {
			player.play();
			result =player.getCurrentMusic();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@POST
	@Path("/stop")
	public Response stop()  {
		try {
			player.stop();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.ok().build();
	}

	@POST
	@Path("/pause")
	public Response pause()  {
		try {
			player.pause();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.ok().build();
	}
	@POST
	@Path("/previous")
	public Response previous()  {
		try {
			player.previous();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.ok().build();
	}
	@POST
	@Path("/next")
	public Response next()  {
		try {
			player.next();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok().build();
	}
	@GET
	@Path("/current")
	public Music getCurrent() {
		try {
			return player.getCurrentMusic();
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
	}
}
