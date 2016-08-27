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

import org.springframework.beans.factory.annotation.Autowired;

import fr.aleclerc.rasp.music.api.EPlayerState;
import fr.aleclerc.rasp.music.api.IPlayer;
import fr.aleclerc.rasp.music.api.exceptions.PlayerException;
import fr.aleclerc.rasp.music.api.pojo.Music;
import fr.aleclerc.rasp.music.api.pojo.MusicLocal;
import fr.aleclerc.rasp.music.api.pojo.MusicYT;
import fr.aleclerc.rasp.music.ws.util.MapperUtil;
import fr.aleclerc.rasp.music.ws.webSocket.Message;

@Path("/player")
public class PlayerResource {

	@Autowired
	private IPlayer player;

	@POST
	@Path("/play")
	@Produces(MediaType.APPLICATION_JSON)
	public Music play() {

		try {
			player.play();
		} catch (PlayerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			return player.getCurrent();
		} catch (PlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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

	@POST
	@Path("/changeTime")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response changeTime(Long time) throws Exception {

		if (time != null) {
			player.changeTime(time);
		}
		return Response.ok().build();
	}

	@GET
	@Path("/state")
	@Produces(MediaType.APPLICATION_JSON)
	public Message getState() throws Exception {

		EPlayerState state = player.getState();
		if (state.equals(EPlayerState.PAUSE)) {
			return new Message("PAUSE");
		} else if (state.equals(EPlayerState.PLAY)) {
			return new Message("PLAY");
		} else if (state.equals(EPlayerState.STOP)) {
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

		return player.getCurrent();
	}

	@PUT
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addMusic(String musicStr) {
		System.out.println(musicStr);
		Music music = null;
		try {
			music = MapperUtil.readAsObjectOf(MusicLocal.class, musicStr);
		} catch (Exception e) {
			
			e.printStackTrace();
			
			try {
				music = MapperUtil.readAsObjectOf(MusicYT.class, musicStr);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		System.out.println(music);
		try {
			player.add(music);
		} catch (PlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Response.ok().build();
	}

	@PUT
	@Path("/remove")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response removeMusic(String musicStr) throws Exception {

		Music music = MapperUtil.readAsObjectOf(Music.class, musicStr);
		player.remove(music);

		return Response.ok().build();
	}
}
