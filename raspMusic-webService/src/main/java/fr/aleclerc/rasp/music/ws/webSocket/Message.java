package fr.aleclerc.rasp.music.ws.webSocket;

import fr.aleclerc.rasp.music.api.EPlayerState;

public class Message {
	private String action;
	private long currentTime = 0;
	private long percentage = 0;
	private long length = 0;
	
	public Message(String action) {
		super();
		this.action = action;
	}
	public Message(EPlayerState state) {
		super();
		this.action = state.name();
	}

	public Message(String action, Long currentTime, Long percentage, Long length) {
		super();
		this.action = action;
		this.currentTime = currentTime;
		this.percentage = percentage;
		this.setLength(length);
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

	public long getPercentage() {
		return percentage;
	}

	public void setPercentage(long percentage) {
		this.percentage = percentage;
	}

	public long getLength() {
		return length;
	}

	public void setLength(long length) {
		this.length = length;
	}

}
