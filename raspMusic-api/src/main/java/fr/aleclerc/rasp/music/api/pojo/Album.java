package fr.aleclerc.rasp.music.api.pojo;

public class Album implements Comparable<Album>{
	private String name;
//	private Artist artiste;

	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		if(name==null){
			this.name = "";
		}else{
			this.name = name;
		}
	
	}
//	public Artist getArtiste() {
//		return artiste;
//	}
//	public void setArtiste(Artist artiste) {
//		this.artiste = artiste;
//	}
//	@JsonIgnore
//	public List<Music> getMusicList() {
//		return musicList;
//	}
//	public void setMusicList(List<Music> musicList) {
//		this.musicList = musicList;
//	}
//	public void addMusic(Music music){
//		if(this.musicList == null){
//			this.musicList = new ArrayList<Music>();
//		}
//		this.musicList.add(music);
//	}
	@Override
	public int compareTo(Album album) {
		return this.name.compareTo(album.getName());
	}
	
}
