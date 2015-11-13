package fr.aleclerc.rasp.music.player.factory;

import fr.aleclerc.rasp.music.model.Music;
import fr.aleclerc.rasp.music.player.impl.MusicImpl;

public class MusicFactory {
	public static Music getInstance() {
		return new MusicImpl();
	}

	public static Music getInstance(Music music) {
		Music musicImpl = new MusicImpl();
		musicImpl.setAlbum(music.getAlbum());
		musicImpl.setArtist(music.getArtist());
		musicImpl.setId(music.getId());
		musicImpl.setPath(music.getPath());
		musicImpl.setPosition(music.getPosition());
		musicImpl.setTitle(music.getTitle());
		return musicImpl;
	}
}
