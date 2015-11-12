package fr.motaz.rasp.music.storage.music;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fr.motaz.rasp.music.model.Music;
import fr.motaz.rasp.music.storage.Storage;

@Component
@Scope("singleton")
public class MusicStorage implements Storage<Music>{
	

	private List<Music> musics =null;
	

	public MusicStorage(){
		musics = new ArrayList<Music>();
	}
	
	@Override
	public List<Music> getAll() {
		return musics;
	}

	@Override
	public void add(Music music) {
		musics.add(music);
	}

}
