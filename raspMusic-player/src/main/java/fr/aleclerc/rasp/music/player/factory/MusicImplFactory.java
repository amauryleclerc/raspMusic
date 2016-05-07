package fr.aleclerc.rasp.music.player.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.aleclerc.rasp.music.model.Music;
import fr.aleclerc.rasp.music.player.impl.MusicImpl;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.direct.DirectMediaPlayer;

@Component
public class MusicImplFactory {
	@Autowired
	private MediaPlayerFactory mediaPlayerFactory;

	public Music getInstance() {
		return new MusicImpl(null);
	}

	public Music getInstance(Music music) {
		MediaPlayer mediaPlayer = null;
		if (music.getId() == null) {
			mediaPlayer = mediaPlayerFactory.getLocalMediaPlayer(music.getPath());
		} else {
			mediaPlayer = mediaPlayerFactory.getYTMediaPlayer(music.getId());
		}
		Music musicImpl = new MusicImpl(mediaPlayer);
		musicImpl.setAlbum(music.getAlbum());
		musicImpl.setArtist(music.getArtist());
		musicImpl.setId(music.getId());
		musicImpl.setPath(music.getPath());
		musicImpl.setPosition(music.getPosition());
		musicImpl.setTitle(music.getTitle());
		musicImpl.setDuration(music.getDuration());
		if(mediaPlayer.getLength() != -1){
			musicImpl.setLength(mediaPlayer.getLength());
		}else{
			musicImpl.setLength(music.getLength());
		}
		musicImpl.setCover(music.getCover());
		musicImpl.setCurrentTime((long) 0);
		return musicImpl;
	}
}
