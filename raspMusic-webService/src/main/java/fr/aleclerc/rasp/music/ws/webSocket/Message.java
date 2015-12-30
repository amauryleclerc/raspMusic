package fr.aleclerc.rasp.music.ws.webSocket;

import fr.aleclerc.rasp.music.model.Music;

public class Message {
	private String action;
	private Music music;
	private long currentTime = 0;

	public Message(String action, Music music) {
		super();
		this.action = action;
		this.music = music;
	}

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

	public Music getMusic() {
		return music;
	}

	public void setMusic(Music music) {
		this.music = music;
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
