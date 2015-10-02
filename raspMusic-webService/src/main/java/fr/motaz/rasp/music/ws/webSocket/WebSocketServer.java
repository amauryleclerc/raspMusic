package fr.motaz.rasp.music.ws.webSocket;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.CloseReason;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import fr.motaz.rasp.music.model.Music;
import fr.motaz.rasp.music.player.Player;
import fr.motaz.rasp.music.player.PlayerListener;
import fr.motaz.rasp.music.player.PlaylistListener;

public class WebSocketServer extends Endpoint implements PlayerListener, PlaylistListener {
	private static final Set<Session> sessions = new CopyOnWriteArraySet<Session>();
	private Session session;

    @Autowired
	private Player player;


    @Override
	public void onOpen(Session session, EndpointConfig conf) {
		this.session = session;
		sessions.add(session);
		player.addPlayerListener(this);
		player.getPlaylist().addPlaylistListener(this);
		System.out.println("start");
	}

    @Override
    public void onClose(Session session, CloseReason closeReason) {
		sessions.remove(session);
		System.out.println("end");
	}

 

    @Override
    public void onError(Session session, Throwable t) {
		System.out.println("error");
		System.out.println(t.getMessage());
		t.printStackTrace();
	}


	private void send(Message m) {
		Gson gson = new Gson();
		String json = gson.toJson(m);
		this.session.getAsyncRemote().sendText(json);
	}

	@Override
	public void onAdd(Music music) {
		this.send(new Message("ADD", music));

	}

	@Override
	public void onPlay(Music music) {
		this.send(new Message("PLAY", music));

	}

	@Override
	public void onPause() {
		this.send(new Message("PAUSE"));

	}

	@Override
	public void onStop() {
		this.send(new Message("STOP"));

	}




}
