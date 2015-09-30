package fr.motaz.rasp.music.ws.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

import fr.motaz.rasp.music.model.Music;
import fr.motaz.rasp.music.storage.StorageService;

@Path("/musics")
public class MusicsResource {

	@Autowired
	private StorageService storageService;

	@GET
	@Produces("application/json")
	public List<Music> getMusics() throws Exception{
		return storageService.getMusicList();
	}
	

}
