package fr.aleclerc.rasp.music.ws.webSocket;

public class Message {
	private String action;
	private long currentTime = 0;

	public Message(String action) {
		super();
		this.action = action;
	}

	public Message(String action, Long currentTime) {
		super();
		this.action = action;
		this.currentTime = currentTime;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
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

}
