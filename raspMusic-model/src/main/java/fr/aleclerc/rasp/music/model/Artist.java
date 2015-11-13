package fr.aleclerc.rasp.music.model;

public class Artist implements Comparable<Artist>{
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name !=null){
			this.name = name;
		}else{
			this.name = "";
		}
		
	}

	@Override
	public int compareTo(Artist artist) {
		return this.name.compareTo(artist.getName());
	}


	
	
	
}
