package fr.motaz.rasp.music.player.factory;

import java.io.IOException;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import fr.motaz.rasp.music.model.Music;
import fr.motaz.rasp.music.player.impl.MusicImpl;

public class MusicFactory {
	public static Music getInstance(){
		return new MusicImpl();		
	}
	public static Music getInstance(Music music){
		Music musicImpl = new MusicImpl();
		musicImpl.setAlbum(music.getAlbum());
		musicImpl.setArtist(music.getArtist());
		musicImpl.setId(music.getId());
		try {
			musicImpl.setPath(music.getPath());
		} catch (UnsupportedTagException | InvalidDataException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		musicImpl.setPosition(music.getPosition());
		musicImpl.setTitle(music.getTitle());
		return musicImpl;		
	}
}
