package fr.aleclerc.rasp.music.ws.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

import fr.aleclerc.rasp.music.api.pojo.Music;
import fr.aleclerc.rasp.music.storage.StorageService;
	

@Path("/musics")
public class MusicsResource {
	

	@Autowired
	private StorageService storageService;

	@GET
	@Produces("application/json")
	public List<Music> getMusics() throws Exception{
		
		return  storageService.getMusicList();
	}
	

}
