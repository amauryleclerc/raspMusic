package fr.motaz.rasp.music.ws.resources;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.model.Resource;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.motaz.rasp.music.model.Music;
import fr.motaz.rasp.music.player.Player;
import fr.motaz.rasp.music.player.PlayerState;
import fr.motaz.rasp.music.ws.webSocket.Message;

@Path("/player")
public class PlayerResource {

	@Autowired
	private Player player;

	@POST
	@Path("/play")
	@Produces("application/json")
	public Music play() throws Exception {
		player.play();
		return player.getPlaylist().getCurrent();
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

	@Path("/playlist")
	public Resource getPlaylist() throws Exception {
		return Resource.from(PlaylistResource.class);
	}
	
	@GET
	@Path("/state")
	public Message getState() throws Exception {
		if(player.getState().equals(PlayerState.PAUSE)){
			return new Message("PAUSE");
		}else if(player.getState().equals(PlayerState.PLAY)){
			return new Message("PLAY");
		}else if(player.getState().equals(PlayerState.STOP)){
			return new Message("STOP");
		}
		return null;
	}
}
