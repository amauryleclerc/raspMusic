package fr.motaz.rasp.music.storage;

import java.io.File;
import java.io.IOException;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import fr.motaz.rasp.music.model.Album;
import fr.motaz.rasp.music.model.Artist;
import fr.motaz.rasp.music.model.Music;

public class MusicFactory {
	
	
	public static Music getIntance(File file) throws UnsupportedTagException, InvalidDataException, IOException {
		Music music = new Music();
		music.setPath(file.getPath()); 
		Mp3File mp3file = new Mp3File(file);
		
		if (mp3file.hasId3v2Tag() && mp3file.getId3v2Tag().getTitle() != null) {
			ID3v2 id3v2Tag = mp3file.getId3v2Tag();
			music.setTitle(id3v2Tag.getTitle());
			Artist artist = new Artist();
			artist.setName(id3v2Tag.getArtist());
//			Artist albumArtist = ArtistFactory.getIntance(id3v2Tag.getAlbumArtist());
			Album album = new Album();
			album.setName(id3v2Tag.getAlbum());
//			album.setArtist(albumArtist);
			music.setAlbum(album);
			music.setArtist(artist);
			music.setPath(file.toPath().toString());
		}else{
			music.setTitle(file.getName()); 
			music.setPath(file.toPath().toString());
		}
	
		return music;
	}
}
