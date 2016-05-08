package fr.aleclerc.rasp.music.api.pojo;

public class MusicYT extends Music {
	private String ytId;
	private String coverURL;
	
	public String getYtId() {
		return ytId;
	}

	public void setYtId(String ytId) {
		this.ytId = ytId;
	}

	public String getCoverURL() {
		return coverURL;
	}

	public void setCoverURL(String coverURL) {
		this.coverURL = coverURL;
	}
}

