package fr.aleclerc.rasp.music.ws.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import fr.aleclerc.rasp.music.api.pojo.Music;
import fr.aleclerc.rasp.music.storage.music.MusicStorage;
	

@Path("/musics")
public class MusicsResource {
	

	@Autowired
	private MusicStorage musicStorage;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Music> getMusics() throws Exception{
		
		return  musicStorage.getAll();
	}
	

}
