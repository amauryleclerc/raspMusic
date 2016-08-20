package fr.aleclerc.rasp.music.ws.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

import fr.aleclerc.rasp.music.api.pojo.Artist;
import fr.aleclerc.rasp.music.storage.StorageService;
	

@Path("/artists")
public class ArtistsResource {


	@Autowired
	private StorageService storageService;

	@GET
	@Produces("application/json")
	public List<Artist> getArtists() throws Exception{

		return  storageService.getArtistList();
	}
	

}
