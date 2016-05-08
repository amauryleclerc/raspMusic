package fr.aleclerc.rasp.music.player.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.aleclerc.rasp.music.api.pojo.Music;
import fr.aleclerc.rasp.music.api.pojo.MusicLocal;
import fr.aleclerc.rasp.music.api.pojo.MusicYT;
import fr.aleclerc.rasp.music.player.Media;
import uk.co.caprica.vlcj.player.MediaPlayer;

@Component
public class MusicImplFactory {
	@Autowired
	private MediaPlayerFactory mediaPlayerFactory;


	public Media getInstance(Music music) {
		if(music instanceof MusicYT){
			return this.getInstance((MusicYT) music);
		}else if(music instanceof MusicLocal){
			return this.getInstance((MusicLocal) music);
		}
		return null;
	}
	public Media getInstance(MusicLocal music){
		MediaPlayer mediaPlayer = mediaPlayerFactory.getLocalMediaPlayer(music.getPath());
		Media  musicImpl = new Media (mediaPlayer);
		return musicImpl;
		
	}
	public Media getInstance(MusicYT music){
		MediaPlayer mediaPlayer = mediaPlayerFactory.getYTMediaPlayer(music.getYtId());
		Media  musicImpl = new Media(mediaPlayer);
		return musicImpl;
		
	}
}
