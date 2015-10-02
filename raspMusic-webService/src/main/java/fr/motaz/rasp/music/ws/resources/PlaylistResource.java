package fr.motaz.rasp.music.ws.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import fr.motaz.rasp.music.model.Music;
import fr.motaz.rasp.music.player.Player;
import fr.motaz.rasp.music.player.Playlist;
import fr.motaz.rasp.music.ws.util.MapperUtil;

public class PlaylistResource {
	
	@Autowired
	private Player player;

	@GET
	public Playlist getPlaylist(){
		return player.getPlaylist();
	}
	@GET
	@Path("/current")
	public Music getCurrent() throws Exception {
		return player.getPlaylist().getCurrent();
	}
	
	@PUT
	@Path("/add")
	@Consumes("application/json")
	public Response addMusic(String musicStr) throws Exception {
		Music music = MapperUtil.readAsObjectOf(Music.class, musicStr);
		player.getPlaylist().add(music);
		return Response.ok().build();
	}
}
