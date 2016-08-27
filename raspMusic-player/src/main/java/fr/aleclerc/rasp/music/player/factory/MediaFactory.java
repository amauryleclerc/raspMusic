package fr.aleclerc.rasp.music.player.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.aleclerc.rasp.music.api.pojo.Music;
import fr.aleclerc.rasp.music.api.pojo.MusicLocal;
import fr.aleclerc.rasp.music.api.pojo.MusicYT;
import fr.aleclerc.rasp.music.player.Media;
import uk.co.caprica.vlcj.player.MediaPlayer;

@Component
public class MediaFactory {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MediaPlayerFactory mediaPlayerFactory;


	public Media getInstance(Music music) {
		LOGGER.trace("getInstance");
		if(music instanceof MusicYT){
			return this.getInstance((MusicYT) music);
		}else if(music instanceof MusicLocal){
			return this.getInstance((MusicLocal) music);
		}
		return null;
	}
	public Media getInstance(MusicLocal music){
		LOGGER.trace("getInstance");
		MediaPlayer mediaPlayer = mediaPlayerFactory.getLocalMediaPlayer(music.getPath());
		Media  musicImpl = new Media (mediaPlayer);
		musicImpl.setMusic(music);
		return musicImpl;
		
	}
	public Media getInstance(MusicYT music){
		LOGGER.trace("getInstance");
		MediaPlayer mediaPlayer = mediaPlayerFactory.getYTMediaPlayer(music.getId());
		Media  musicImpl = new Media(mediaPlayer);
		musicImpl.setMusic(music);
		return musicImpl;
		
	}
}
