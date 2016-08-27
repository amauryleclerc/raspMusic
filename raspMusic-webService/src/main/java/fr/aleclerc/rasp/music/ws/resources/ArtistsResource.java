package fr.aleclerc.rasp.music.ws.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import fr.aleclerc.rasp.music.api.pojo.Artist;
import fr.aleclerc.rasp.music.storage.artist.ArtistStorage;
	

@Path("/artists")
public class ArtistsResource {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ArtistStorage artistStorage;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Artist> getArtists() throws Exception{
		LOGGER.debug("Get all artistes");
		return  artistStorage.getAll();
	}
	

}
