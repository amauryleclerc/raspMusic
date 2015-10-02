package fr.motaz.rasp.music.ws.webSocket;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import fr.motaz.rasp.music.model.Music;
import fr.motaz.rasp.music.player.Player;
import fr.motaz.rasp.music.player.PlayerListener;
import fr.motaz.rasp.music.player.PlaylistListener;

@ServerEndpoint(value = "/websocket")
public class WebSocketServer implements PlayerListener, PlaylistListener {
	private static final Set<Session> sessions = new CopyOnWriteArraySet<Session>();
	private Session session;

	@Autowired
	private Player player;

	@OnOpen
	public void start(Session session) {
		this.session = session;
		sessions.add(session);
		player.addPlayerListener(this);
		player.getPlaylist().addPlaylistListener(this);
		System.out.println("start");
	}

	@OnClose
	public void end() {
		sessions.remove(session);
		System.out.println("end");
	}

	@OnMessage
	public void incoming(String json) {

	}

	@OnError
	public void onError(Throwable t) throws Throwable {
		System.out.println("error");
		System.out.println(t.getMessage());
		t.printStackTrace();
	}

//	private static void broadcast(Message m) {
//		Gson gson = new Gson();
//		String json = gson.toJson(m);
//		System.out.println("broadcast :" + m.toString());
//		for (Session uneSession : sessions) {
//			uneSession.getAsyncRemote().sendText(json);
//		}
//	}

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
