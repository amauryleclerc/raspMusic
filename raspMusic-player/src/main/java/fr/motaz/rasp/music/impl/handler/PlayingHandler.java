package fr.motaz.rasp.music.impl.handler;

import fr.motaz.rasp.music.Music;
import fr.motaz.rasp.music.Player;
import fr.motaz.rasp.music.impl.factory.PlayerFactory;

public class PlayingHandler implements Runnable {

	@Override
	public void run() {
		Player player = PlayerFactory.getInstance();
		Music music = null;
		try {
			music = player.getCurrentMusic();
			System.out.println("Lecture de " + music.getTitre()+ " - " + music.getArtiste());	
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
