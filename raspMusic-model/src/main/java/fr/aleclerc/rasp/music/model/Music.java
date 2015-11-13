package fr.aleclerc.rasp.music.model;

public class Music implements Comparable<Music> {
	private String title;
	private Album album;
	private Artist artist;
	private String path;
	private String id;
	private Integer position;

	public Music() {

	}

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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public int compareTo(Music music) {
		return this.title.compareTo(music.getTitle());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

}
