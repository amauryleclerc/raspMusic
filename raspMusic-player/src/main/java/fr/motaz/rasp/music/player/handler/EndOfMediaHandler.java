package fr.motaz.rasp.music.player.handler;

import fr.motaz.rasp.music.player.Player;
import fr.motaz.rasp.music.player.impl.PlayerImpl;

public class EndOfMediaHandler implements Runnable {

	@Override
	public void run() {
		System.out.println("handler : end of media");
		Player player = PlayerImpl.getInstance();
		try {
			player.next();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}