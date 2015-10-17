package fr.motaz.rasp.music.model;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import uk.co.caprica.vlcj.player.direct.DirectMediaPlayer;

public class Music implements Comparable<Music> {
	private String title;
	private Album album;
	private Artist artist;
	private String path;
	private String id;
	private Integer position;
	private transient DirectMediaPlayer mediaPlayer = null;
	public Music(File file) throws UnsupportedTagException, InvalidDataException, IOException {
		this.setFile(file);
	}

	private void setFile(File file) throws UnsupportedTagException, InvalidDataException, IOException {
		this.path = (file.getPath());
		Mp3File mp3file = null;
		mp3file = new Mp3File(file);
		if (mp3file.hasId3v2Tag()) {
			ID3v2 id3v2Tag = mp3file.getId3v2Tag();
			this.setTitle(id3v2Tag.getTitle());
			Artist artist = new Artist();
			artist.setName(id3v2Tag.getArtist());
			Artist albumArtist = new Artist();
			albumArtist.setName(id3v2Tag.getAlbumArtist());
			Album album = new Album();
			album.addMusic(this);
			album.setName(id3v2Tag.getAlbum());
			album.setArtiste(albumArtist);
			this.setAlbum(album);
			this.setArtist(artist);
		}
	}

	public Music() {

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if(title !=null){
			this.title = title;
		}else{
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

	@JsonIgnore
	public DirectMediaPlayer getMediaPlayer() {
		if (this.mediaPlayer == null) {
			if(this.id == null){
				this.mediaPlayer = MediaPlayerFactory.getLocalMediaPlayer(path);
			}else{
				this.mediaPlayer = MediaPlayerFactory.getYTMediaPlayer(id);
			}
		}
		return this.mediaPlayer;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) throws UnsupportedTagException, InvalidDataException, IOException {
		this.setFile(new File(path));
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
