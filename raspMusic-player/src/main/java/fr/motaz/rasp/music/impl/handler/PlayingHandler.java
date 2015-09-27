package fr.motaz.rasp.music.impl.handler;

import fr.motaz.rasp.music.Player;
import fr.motaz.rasp.music.impl.PlayerImpl;
import fr.motaz.rasp.music.model.Music;

public class PlayingHandler implements Runnable {

	@Override
	public void run() {
		Player player = PlayerImpl.getInstance();
		Music music = null;
		try {
			music = player.getCurrentMusic();
			System.out.println("Lecture de " + music.getTitle()+ " - " + music.getArtist().getName());	
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
