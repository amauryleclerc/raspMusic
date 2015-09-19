package fr.motaz.rasp.music.impl;

import java.io.File;
import java.util.Map;

import fr.motaz.rasp.music.Music;
import javafx.scene.media.Media;

public class MusicImpl extends Music {
	
	public void setFile(File file) {
		Map<String, Object> meta = new Media(file.toURI().toString()).getMetadata();
		System.out.println(meta.get("title"));
		super.setFile(file);
	}

}
