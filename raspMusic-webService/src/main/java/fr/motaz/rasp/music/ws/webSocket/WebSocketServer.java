package fr.motaz.rasp.music.ws.webSocket;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.CloseReason;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.Session;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import fr.motaz.rasp.music.model.Music;
import fr.motaz.rasp.music.player.Player;
import fr.motaz.rasp.music.player.PlayerListener;
import fr.motaz.rasp.music.player.PlaylistListener;

public class WebSocketServer extends Endpoint implements PlayerListener, PlaylistListener {
	protected  static final Logger logger = LogManager.getLogger(WebSocketServer.class);
	private static final Set<Session> sessions = new CopyOnWriteArraySet<Session>();
	private Session session;

	@Autowired
	private Player player;

	@Override
	public void onOpen(Session session, EndpointConfig conf) {
		logger.trace("websocket : open " + session.getRequestURI());
		this.session = session;
		sessions.add(session);
		player.addPlayerListener(this);
		player.getPlaylist().addPlaylistListener(this);
	}

	@Override
	public void onClose(Session session, CloseReason closeReason) {
		logger.trace("websocket : close");
		sessions.remove(session);
		player.removePlayerListener(this);
		player.getPlaylist().removePlaylistListener(this);
	}

	@Override
	public void onError(Session session, Throwable t) {
		logger.error("websocket : "+t.getMessage());
	}

	private void send(Message m) {
		synchronized (this) {
			if (this.session.isOpen()) {
				Gson gson = new Gson();
				String json = gson.toJson(m);
				logger.trace("send : "+json);
				this.session.getAsyncRemote().sendText(json);
			}
		}
	
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
