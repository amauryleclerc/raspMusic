package fr.aleclerc.rasp.music.storage.music;

import java.io.File;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Base64;

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

	@Autowired
	public ArtistFactory artistFactory;

	public Music getIntance(File file) throws UnsupportedTagException, InvalidDataException, IOException, StorageException {
		MusicLocal music = new MusicLocal();
		music.setPath(file.getPath());
		Mp3File mp3file = new Mp3File(file);

		if (mp3file.hasId3v2Tag() && mp3file.getId3v2Tag().getTitle() != null) {
			ID3v2 id3v2Tag = mp3file.getId3v2Tag();
	
			byte[] albumImageData = id3v2Tag.getAlbumImage();
			if (albumImageData != null) {
				String cover = Base64.getEncoder().encodeToString(ImageUtils.scale(albumImageData, 80, 80));
				String mimeType = id3v2Tag.getAlbumImageMimeType();
				music.setCover("data:"+mimeType+";base64,"+cover);
			}
			music.setDuration(LocalTime.MIN.plusSeconds(mp3file.getLengthInSeconds()).toString());
			
			music.setTitle(id3v2Tag.getTitle());
			Artist artist = artistFactory.getIntance(id3v2Tag.getArtist());

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
		return music;
	}
}
