package fr.motaz.rasp.music.player.handler;

import fr.motaz.rasp.music.model.Music;
import fr.motaz.rasp.music.player.Player;
import fr.motaz.rasp.music.player.impl.PlayerImpl;

public class PlayingHandler implements Runnable {

	@Override
	public void run() {
		Player player = PlayerImpl.getInstance();
		Music music = null;
		try {
			music = player.getPlaylist().getCurrent();
			System.out.println("Lecture de " + music.getTitle()+ " - " + music.getArtist().getName());	
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
