package fr.motaz.rasp.music.model;

public class Artist implements Comparable<Artist>{
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(Artist artist) {
		return this.name.compareTo(artist.getName());
	}


	
	
	
}
