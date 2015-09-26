package fr.motaz.rasp.music.impl;

import java.util.ArrayList;
import java.util.List;

import fr.motaz.rasp.music.Music;
import fr.motaz.rasp.music.Player;
import fr.motaz.rasp.music.PlayerListener;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;

public class PlayerImpl implements Player {

	private static List<Music> musicList = new ArrayList<Music>();
	private static Integer currentMusicNum = -1;
	private static List<PlayerListener> listeners = new ArrayList<PlayerListener>();

	  private static PlayerImpl instance;

	  public static PlayerImpl getInstance(){
	    if(instance==null){
	 
	          instance = new PlayerImpl();
	        
	    }
	    return instance;
	  }

	@Override
	public void stop() throws Exception {
		((MusicImpl)getCurrentMusic()).getMediaPlayer().stop();
	}

	@Override
	public void play() throws Exception {
		((MusicImpl)getCurrentMusic()).getMediaPlayer().play();
	}

	@Override
	public void pause() throws Exception {
		((MusicImpl)getCurrentMusic()).getMediaPlayer().pause();
	}

	@Override
	public void addMusic(Music musique) {
		System.out.println("addMusic");
		musicList.add(musique);
		if (currentMusicNum == -1) {
			currentMusicNum++;
		}
	}

	@Override
	public Music getCurrentMusic() throws Exception {
		if (currentMusicNum > -1) {
			return musicList.get(currentMusicNum);
		} else {
			throw new Exception("pas de music");
			
		}
	}

	

	public void init() throws Exception {
		System.out.println("Init");
		new JFXPanel();

	}

	public void destroy() throws Exception {
		System.out.println("clean up");
		Platform.exit();
	}

	@Override
	public void next() throws Exception {
		this.stop();
		currentMusicNum++;
		this.play();
	}

	@Override
	public void previous() throws Exception {
		this.stop();
		currentMusicNum--;
		this.play();
	}

	@Override
	public void addListener(PlayerListener listener) {
		listeners.add(listener);
	}
}
