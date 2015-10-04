package fr.motaz.rasp.music.player.handler;

public class StopedHandler implements Runnable {

	@Override
	public void run() {
		System.out.println("handler : stop");
	}

}
