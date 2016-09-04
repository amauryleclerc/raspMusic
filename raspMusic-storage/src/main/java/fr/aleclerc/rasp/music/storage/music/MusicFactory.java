package fr.aleclerc.rasp.music.storage.music;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Base64;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import fr.aleclerc.rasp.music.api.pojo.Album;
import fr.aleclerc.rasp.music.api.pojo.Artist;
import fr.aleclerc.rasp.music.api.pojo.Music;
import fr.aleclerc.rasp.music.api.pojo.MusicLocal;
import fr.aleclerc.rasp.music.storage.artist.ArtistFactory;
import fr.aleclerc.rasp.music.storage.exception.StorageException;
import fr.aleclerc.rasp.music.storage.utils.ImageUtils;

@Component
public class MusicFactory {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public ArtistFactory artistFactory;

	public Optional<Music> getIntance(File file) {
		ImageUtils util = new ImageUtils();
		LOGGER.debug("getIntance : {}", file.getName());
		MusicLocal music = new MusicLocal();
		music.setPath(file.getPath());
		Mp3File mp3file = null;
		try {
			mp3file = new Mp3File(file);
		} catch (UnsupportedTagException | InvalidDataException | IOException e) {
			LOGGER.error("Création Mp3 impossible : {}", e.getMessage());
			return Optional.empty();

		}

		if (mp3file.hasId3v2Tag() && mp3file.getId3v2Tag().getTitle() != null) {
			ID3v2 id3v2Tag = mp3file.getId3v2Tag();

			byte[] albumImageData = id3v2Tag.getAlbumImage();

			String cover = null;
			String mimeType = null;
			if (albumImageData != null) {
				try {
					cover = Base64.getEncoder().encodeToString(util.scale(albumImageData, 80, 80));
					mimeType = id3v2Tag.getAlbumImageMimeType();
				
				} catch (StorageException e) {
					LOGGER.error("Création cover Mp3 impossible : {}", e.getMessage());
				}
			} 
			if(cover == null){
					try {
						cover = Base64.getEncoder().encodeToString(util.getNoCover());
						mimeType = "image/jpeg";
					} catch (StorageException e) {
						LOGGER.error("Création NoCover impossible : {}", e.getMessage());
					}
					
			}
			music.setCover("data:" + mimeType + ";base64," + cover);
		
			music.setDuration(LocalTime.MIN.plusSeconds(mp3file.getLengthInSeconds()).toString());

			music.setTitle(id3v2Tag.getTitle());
			Artist artist = artistFactory.getIntance(id3v2Tag.getArtist());
			// TODO Album
			// Artist albumArtist =
			// ArtistFactory.getIntance(id3v2Tag.getAlbumArtist());
			Album album = new Album();
			album.setName(id3v2Tag.getAlbum());
			// album.setArtist(albumArtist);
			music.setAlbum(album);
			music.setArtist(artist);
			music.setPath(file.toPath().toString());
		} else {
			music.setTitle(file.getName());
			music.setPath(file.toPath().toString());
		}
		return Optional.of(music);
	}

}
