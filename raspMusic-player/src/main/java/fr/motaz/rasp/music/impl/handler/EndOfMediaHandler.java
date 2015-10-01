package fr.motaz.rasp.music.impl.handler;

import fr.motaz.rasp.music.Player;
import fr.motaz.rasp.music.impl.PlayerImpl;

public class EndOfMediaHandler implements Runnable {

	@Override
	public void run() {
		System.out.println("end of media");
		Player player = PlayerImpl.getInstance();
		try {
			player.next();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}