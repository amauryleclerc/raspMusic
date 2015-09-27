package fr.motaz.rasp.music.webSocket;

import fr.motaz.rasp.music.model.Music;

public class Message {
	private String action;
	private Music music;

	public Message(String action, Music music) {
		super();
		this.action = action;
		this.music = music;
	}
	public Message(String action) {
		super();
		this.action = action;
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
	
	

}
