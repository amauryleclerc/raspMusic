package fr.aleclerc.rasp.music.ws.webSocket;

import fr.aleclerc.rasp.music.api.EPlayerState;
import fr.aleclerc.rasp.music.ws.EAction;

public class Message {
	private EAction action;
	private long currentTime = 0;
	private long length = 0;
	
	public Message(EAction action) {
		super();
		this.action = action;
	}
	public Message(EPlayerState state) {
		super();
		this.action = EAction.fromPlayerState(state);
	}

	public Message(EAction action, Long currentTime, Long length) {
		super();
		this.action = action;
		this.currentTime = currentTime;
		this.length = length;
	}

	public EAction getAction() {
		return action;
	}

	public void setAction(EAction action) {
		this.action = action;
	}

	@Override
	public String toString() {
		return "Message [action=" + action + "]";
	}

	public Long getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(long currentTime) {
		this.currentTime = currentTime;
	}

	public long getLength() {
		return length;
	}

	public void setLength(long length) {
		this.length = length;
	}

}
