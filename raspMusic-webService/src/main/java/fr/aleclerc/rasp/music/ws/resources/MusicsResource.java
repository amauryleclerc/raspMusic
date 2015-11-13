package fr.aleclerc.rasp.music.ws.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import fr.aleclerc.rasp.music.model.Music;
import fr.aleclerc.rasp.music.storage.StorageService;
	

@Path("/musics")
public class MusicsResource {
	protected  static final Logger logger = LogManager.getLogger(MusicsResource.class);

	@Autowired
	private StorageService storageService;

	@GET
	@Produces("application/json")
	public List<Music> getMusics() throws Exception{
		logger.trace("getMusics");
		return  storageService.getMusicList();
	}
	

}
