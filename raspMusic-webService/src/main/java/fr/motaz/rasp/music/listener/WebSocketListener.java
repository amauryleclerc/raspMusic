package fr.motaz.rasp.music.listener;

import fr.motaz.rasp.music.PlayerListener;
import fr.motaz.rasp.music.webSocket.Message;
import fr.motaz.rasp.music.webSocket.WebSocketServer;

public class WebSocketListener implements PlayerListener {

	@Override
	public void onPlay() {

		WebSocketServer.broadcast(new Message("PLAY"));

	}

	@Override
	public void onPause() {
		WebSocketServer.broadcast(new Message("PAUSE"));

	}

	@Override
	public void onAdd() {
		WebSocketServer.broadcast(new Message("ADD"));

	}

	@Override
	public void onStop() {
		WebSocketServer.broadcast(new Message("STOP"));

	}

}
