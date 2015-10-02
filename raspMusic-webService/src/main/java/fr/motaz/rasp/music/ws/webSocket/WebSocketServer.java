package fr.motaz.rasp.music.ws.webSocket;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;

@ServerEndpoint(value = "/websocket")
public class WebSocketServer {
	private static final Set<Session> sessions = new CopyOnWriteArraySet<Session>();
	private Session session;

	@OnOpen
	public void start(Session session) {
		this.session = session;
		sessions.add(session);
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

	public static void broadcast(Message m) {
		Gson gson = new Gson();
		String json = gson.toJson(m);
		System.out.println("broadcast :"+m.toString());
		for (Session uneSession : sessions) {
			uneSession.getAsyncRemote().sendText(json);
		}
	}

}
