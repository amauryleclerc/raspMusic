package fr.motaz.rasp.music.webSocket;

public class Message {
	private String action;

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

	@Override
	public String toString() {
		return "Message [action=" + action + "]";
	}
	
	

}
