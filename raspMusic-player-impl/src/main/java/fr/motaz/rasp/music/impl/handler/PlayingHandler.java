package fr.motaz.rasp.music.impl.handler;

import fr.motaz.rasp.music.Player;
import fr.motaz.rasp.music.impl.factory.PlayerFactory;
import javafx.scene.media.Media;

public class PlayingHandler implements Runnable {

	@Override
	public void run() {
		Player player = PlayerFactory.getInstance();
		Media media = player.getCurrentMusic();
		System.out.println("Lecture de " + media.getMetadata().get("title") + " - " + media.getMetadata().get("artist"));	
	}

}
