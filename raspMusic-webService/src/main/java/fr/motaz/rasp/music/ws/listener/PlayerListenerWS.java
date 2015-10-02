package fr.motaz.rasp.music.ws.listener;

import fr.motaz.rasp.music.model.Music;
import fr.motaz.rasp.music.player.PlayerListener;
import fr.motaz.rasp.music.ws.webSocket.Message;
import fr.motaz.rasp.music.ws.webSocket.WebSocketServer;

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
	public void onAdd(Music music) {
		WebSocketServer.broadcast(new Message("ADD", music));

	}

	@Override
	public void onStop() {
		WebSocketServer.broadcast(new Message("STOP"));

	}

}
