package fr.aleclerc.rasp.music.api.pojo;

public class Music implements Comparable<Music> {
	private String title;
	private Album album;
	private Artist artist;
	private String cover;
	private String duration;
	private Long currentTime;
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if (title != null) {
			this.title = title;
		} else {
			this.title = "";
		}
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	@Override
	public int compareTo(Music music) {
		return this.title.compareTo(music.getTitle());
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	
	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public Long getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(Long currentTime) {
		this.currentTime = currentTime;
	}

	@Override
	public String toString() {
		return "Music [title=" + title + ", album=" + album + ", artist=" + artist + ", cover=" + cover + ", duration="
				+ duration + ", currentTime=" + currentTime + "]";
	}


}
