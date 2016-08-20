package fr.aleclerc.rasp.music.ws.resources;

import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import fr.aleclerc.rasp.music.api.pojo.Artist;
import fr.aleclerc.rasp.music.api.pojo.Music;
import fr.aleclerc.rasp.music.storage.StorageService;
	

@Path("/artist")
public class ArtistResource {


	@Autowired
	private StorageService storageService;

	@GET
	@Produces("application/json")
	@Consumes("application/json")
	public Artist getArtist(@PathParam("artist") String artist) throws Exception{

		return  storageService.getArtist(artist);
	}
	@GET
	@Path("{artist}/musics/")
	@Produces("application/json")
	public List<Music> getMusics(@PathParam("artist") String artist) throws Exception{
		
		return  storageService.getMusicsFromArtist(storageService.getArtist(artist));
	}
	

}
