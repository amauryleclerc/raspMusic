package fr.aleclerc.rasp.music.player.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.aleclerc.rasp.music.player.Player;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;
@Component
public class PlayerEventListener extends MediaPlayerEventAdapter {

	@Autowired
	private Player player;

	@Override
	public void finished(MediaPlayer mediaPlayer) {
		try {
			player.next(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void timeChanged(MediaPlayer mediaPlayer, long newTime) {
		player.getPlaylist().updateTime(newTime);

	}

}