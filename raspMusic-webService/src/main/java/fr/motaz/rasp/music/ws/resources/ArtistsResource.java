package fr.motaz.rasp.music.ws.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import fr.motaz.rasp.music.model.Artist;
import fr.motaz.rasp.music.storage.StorageService;
	

@Path("/artists")
public class ArtistsResource {
	protected  static final Logger logger = LogManager.getLogger(ArtistsResource.class);

	@Autowired
	private StorageService storageService;

	@GET
	@Produces("application/json")
	public List<Artist> getArtists() throws Exception{
		logger.trace("getArtists");
		return  storageService.getArtistList();
	}
	

}
