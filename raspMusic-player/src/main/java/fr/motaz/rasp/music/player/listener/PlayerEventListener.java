package fr.motaz.rasp.music.player.listener;

import org.springframework.beans.factory.annotation.Autowired;

import fr.motaz.rasp.music.player.Player;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;

public class PlayerEventListener extends MediaPlayerEventAdapter {
	@Autowired
	Player player;
	
	
	@Override
	public void finished(MediaPlayer mediaPlayer){
		try {
			player.next();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
