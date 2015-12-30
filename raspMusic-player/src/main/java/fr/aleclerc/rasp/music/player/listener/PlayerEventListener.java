package fr.aleclerc.rasp.music.player.listener;

import fr.aleclerc.rasp.music.player.Player;
import fr.aleclerc.rasp.music.player.impl.PlayerImpl;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;


public class PlayerEventListener extends MediaPlayerEventAdapter {
	

	private Player player;
	public PlayerEventListener(){
		player = PlayerImpl.getInstance();
	}
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

//		System.out.println(newTime);
		if (player == null) {
			System.out.println("player null");
		}
		System.out.println(player.getPlaylist());
		player.getPlaylist().updateTime(newTime);

		

	}

}
