package fr.motaz.rasp.music.listener;

import fr.motaz.rasp.music.PlayerListener;
import fr.motaz.rasp.music.model.Music;
import fr.motaz.rasp.music.webSocket.Message;
import fr.motaz.rasp.music.webSocket.WebSocketServer;

public class PlayerListenerWS implements PlayerListener {

	@Override
	public void onPlay(Music music) {

		WebSocketServer.broadcast(new Message("PLAY", music));

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
