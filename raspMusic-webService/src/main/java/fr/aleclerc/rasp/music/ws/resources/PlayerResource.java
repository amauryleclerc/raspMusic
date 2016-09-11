package fr.aleclerc.rasp.music.ws.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.async.DeferredResult;

import fr.aleclerc.rasp.music.api.AMedia;
import fr.aleclerc.rasp.music.api.IPlayer;
import fr.aleclerc.rasp.music.api.exceptions.PlayerException;
import fr.aleclerc.rasp.music.api.pojo.Music;
import fr.aleclerc.rasp.music.api.pojo.MusicLocal;
import fr.aleclerc.rasp.music.api.pojo.MusicYT;
import fr.aleclerc.rasp.music.ws.util.MapperUtil;
import fr.aleclerc.rasp.music.ws.webSocket.Message;

@Path("/player")
public class PlayerResource {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IPlayer player;

	@POST
	@Path("/play")
	@Produces(MediaType.APPLICATION_JSON)
	public Music play() {
		LOGGER.debug("Play");
		try {
			player.play();
		} catch (PlayerException e) {
			LOGGER.error(e.getMessage());
		}
		try {
			return player.getCurrent();
		} catch (PlayerException e) {
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@POST
	@Path("/stop")
	public Response stop() throws Exception {
		LOGGER.debug("Stop");
		player.stop();
		return Response.ok().build();
	}

	@POST
	@Path("/pause")
	public Response pause() throws Exception {
		LOGGER.debug("Pause");
		player.pause();
		return Response.ok().build();
	}

	@POST
	@Path("/previous")
	public Response previous() throws Exception {
		LOGGER.debug("Previous");
		player.previous();
		return Response.ok().build();
	}

	@POST
	@Path("/next")
	public Response next() throws Exception {
		LOGGER.debug("Next");
		player.next();
		return Response.ok().build();
	}

	@POST
	@Path("/changeTime")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response changeTime(Long time) throws Exception {
		LOGGER.debug("ChangeTime : {}", time);
		if (time != null) {
			player.changeTime(time);
		}
		return Response.ok().build();
	}

	@GET
	@Path("/state")
	@Produces(MediaType.APPLICATION_JSON)
	public DeferredResult<Message> getState() throws Exception {
	    DeferredResult<Message> deffered = new DeferredResult<Message>();
		this.player.getStateStream()//
		.map(s ->new Message(s))//
		.subscribe(m -> deffered.setResult(m), e -> deffered.setErrorResult(e));
		return deffered;
	}

	@GET
	@Path("/playlist")
	public List<AMedia> getPlaylist() {
		LOGGER.debug("getPlaylist");
		return player.getMediaPlaylist();
	}

	@GET
	@Path("/current")
	public AMedia getCurrent() throws Exception {
		LOGGER.debug("getCurrent");
		return player.getCurrentMedia();
	}

	@PUT
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addMusic(String musicStr) {
		LOGGER.debug("addMusic : {} ",musicStr);
		Music music = null;
		try {
			music = MapperUtil.readAsObjectOf(MusicLocal.class, musicStr);
			LOGGER.info("Music local");
		} catch (Exception e) {
			LOGGER.info("Music non local");
			try {
				music = MapperUtil.readAsObjectOf(MusicYT.class, musicStr);
				LOGGER.info("Music YT");
			} catch (Exception e1) {
				LOGGER.error("Music ni YT ni local");
			}
		}
		try {
			player.add(music);
		} catch (PlayerException e) {
			LOGGER.error("Error ajout music : {} ",e.getMessage());
			return Response.serverError().build();
		}
		
		return Response.ok().build();
	}

	@PUT
	@Path("/remove")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response removeMusic(String musicStr) throws Exception {
		LOGGER.debug("remove music : {} ",musicStr);
		Music music = MapperUtil.readAsObjectOf(Music.class, musicStr);
		player.remove(music);

		return Response.ok().build();
	}
}
