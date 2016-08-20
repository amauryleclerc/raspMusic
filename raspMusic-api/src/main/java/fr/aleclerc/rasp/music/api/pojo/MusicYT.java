package fr.aleclerc.rasp.music.api.pojo;

public class MusicYT extends Music {
	private String id;
	private String coverURL;

	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCoverURL() {
		return coverURL;
	}

	public void setCoverURL(String coverURL) {
		this.coverURL = coverURL;
	}
}
