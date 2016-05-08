package fr.aleclerc.rasp.music.ws.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import fr.aleclerc.rasp.music.api.Player;
import fr.aleclerc.rasp.music.api.PlayerState;
import fr.aleclerc.rasp.music.api.pojo.Music;
import fr.aleclerc.rasp.music.ws.util.MapperUtil;
import fr.aleclerc.rasp.music.ws.webSocket.Message;

@Path("/player")
public class PlayerResource {


	
	protected static final Logger logger = LogManager.getLogger(PlayerResource.class);
	@Autowired
	private Player player;

	@POST
	@Path("/play")
	@Produces("application/json")
	public Music play() throws Exception {
		logger.trace("play");

		player.play();
		return player.getCurrent();
	}

	@POST
	@Path("/stop")
	public Response stop() throws Exception {
		logger.trace("stop");
		player.stop();
		return Response.ok().build();
	}

	@POST
	@Path("/pause")
	public Response pause() throws Exception {
		logger.trace("pause");
		player.pause();
		return Response.ok().build();
	}

	@POST
	@Path("/previous")
	public Response previous() throws Exception {
		logger.trace("previous");
		player.previous();
		return Response.ok().build();
	}

	@POST
	@Path("/next")
	public Response next() throws Exception {
		logger.trace("next");
		player.next();
		return Response.ok().build();
	}

	@POST
	@Path("/changeTime")
	@Consumes("application/json")
	public Response changeTime(Long time) throws Exception {
		logger.trace("changeTime " + time);
		if (time != null) {
			player.changeTime(time);
		}
		return Response.ok().build();
	}



	@GET
	@Path("/state")
	@Produces("application/json")
	public Message getState() throws Exception {
		logger.trace("getState");
		PlayerState state = player.getState();
		if (state.equals(PlayerState.PAUSE)) {
			return new Message("PAUSE");
		} else if (state.equals(PlayerState.PLAY)) {
			return new Message("PLAY");
		} else if (state.equals(PlayerState.STOP)) {
			return new Message("STOP");
		}
		return null;
	}
	@GET
	@Path("/playlist")
	public List<Music> getPlaylist() {
		return player.getPlaylist();
	}

	@GET
	@Path("/current")
	public Music getCurrent() throws Exception {
		logger.trace("getCurrent");
		return player.getCurrent();
	}

	@PUT
	@Path("/add")
	@Consumes("application/json")
	public Response addMusic(String musicStr) throws Exception {
		logger.trace("addMusic " + musicStr);
		Music music = MapperUtil.readAsObjectOf(Music.class, musicStr);
		player.getPlaylist().add(music);
		return Response.ok().build();
	}

	@PUT
	@Path("/remove")
	@Consumes("application/json")
	public Response removeMusic(String musicStr) throws Exception {
		logger.trace("addMusic " + musicStr);
		Music music = MapperUtil.readAsObjectOf(Music.class, musicStr);
		player.getPlaylist().remove(music);

		return Response.ok().build();
	}
}
